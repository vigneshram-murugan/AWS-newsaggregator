package com.news.controllers;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.news.domain.BuzzFeed;
import com.news.domain.NewsApi;
import com.news.domain.NyTimes;
import com.news.repository.BuzzFeedRepository;
import com.news.repository.NewYorkTimesRepository;
import com.news.repository.NewsFeedRepository;




@RestController
@EnableScheduling
public class FeedsController {
	
    private static final Logger logger = LoggerFactory.getLogger(FeedsController.class);
	
	private BuzzFeedRepository buzzFeedRepository;
	private NewsFeedRepository newsFeedRepository;
	private NewYorkTimesRepository newYorkTimesRepository; 
	
	@Value("${api.url.buzzfeed}")
	private String buzzFeedUrl;
	
	@Value("${api.url.newsapi}")
	private String newsApiUrl;
	
	@Value("${api.url.nytimes}")
	private String nytimes;
	
	@Value("${api.nytimes.key}")
	private String nyTimesKey;
	
	@Autowired
	public FeedsController(BuzzFeedRepository buzzFeedRepository,
						   NewsFeedRepository newsFeedRepository,
						   NewYorkTimesRepository newYorkTimesRepository) {
		this.buzzFeedRepository = buzzFeedRepository;
		this.newsFeedRepository = newsFeedRepository;
		this.newYorkTimesRepository = newYorkTimesRepository;
		
	}
	
	@RequestMapping(value="/get-buzz-feed", method=RequestMethod.GET)
	public BuzzFeed getNewsFromBuzzFeed() throws Exception {
		RestTemplate buzzFeedTemplate = new RestTemplate();
		BuzzFeed buzzFeed = buzzFeedTemplate.getForObject(buzzFeedUrl, BuzzFeed.class);
		saveBuzzFeedData(buzzFeed);
		return buzzFeed;
	}
	
	public void saveBuzzFeedData(BuzzFeed buzzFeed) throws Exception {
		buzzFeedRepository.save(buzzFeed);
	}
	
	@RequestMapping(value="/get-newsapi-feed", method=RequestMethod.GET)
	public NewsApi getNewsFromNewsApi() throws Exception {
		RestTemplate newApiTemplate = new RestTemplate();
		NewsApi newsFeed = newApiTemplate.getForObject(newsApiUrl, NewsApi.class);
		saveNewsApiData(newsFeed);
		return newsFeed;
		
	}
	
	public void saveNewsApiData(NewsApi newsFeed) {
		newsFeedRepository.save(newsFeed);
	}
	
	@RequestMapping(value="/get-nytimes-feed", method=RequestMethod.GET)
	public NyTimes getNewsFromNyTimes() {
		URI targetUrl= UriComponentsBuilder.fromUriString(nytimes)
			    .queryParam("api-key", nyTimesKey)
			    .build()
			    .toUri();
						
			RestTemplate newApiTemplate = new RestTemplate();
			NyTimes newsFeed = newApiTemplate.getForObject(targetUrl, NyTimes.class);
			saveNewsFromNyTimes(newsFeed);
			return newsFeed;
	}
	
	public void saveNewsFromNyTimes(NyTimes newsFeed) {
		newYorkTimesRepository.save(newsFeed);
	}
	
	/**
	 * Get news feed from New york times every  2 hours  
	 */
	
	@Scheduled(cron = "0 0 0/2 * * ?")
	public void pullNewsFeedFromNyTimes() throws Exception {
		NyTimes nyTimes = getNewsFromNyTimes();
		logger.info("Completed fetching news from Ny Times at :" + new java.util.Date());
	}
	
	/**
	 * Get news feed from Buzz Feed times every 2 hours 
	 */
	
	@Scheduled(cron = "0 0 0/2 * * ?")
	public void pullNewsFeedFromBuzzFeed() throws Exception {
		BuzzFeed buzzFeed = getNewsFromBuzzFeed();
		logger.info("Completed fetching news from Buzz Feed at :" + new java.util.Date());
	}
	
	/**
	 * Get news feed from News Api times every 2 hours 
	 */
	
	@Scheduled(cron = "0 0 0/2 * * ?")
	public void pullNewsFeedFromNewsApi() throws Exception {
		NewsApi newsApi = getNewsFromNewsApi();
		logger.info("Completed fetching news from News Api at :" + new java.util.Date());
	}

	@Cacheable("nytimes")
	@RequestMapping(value="/get-nytimes-news", method=RequestMethod.GET)
	public List<NyTimes> getNyTimes() {
		return newYorkTimesRepository.findAll();
	}
	
	@RequestMapping(value="/get-buzzfeed-news", method=RequestMethod.GET)
	public List<BuzzFeed> getBuzzFeeds() {
		return buzzFeedRepository.findAll();
	}
	
	@RequestMapping(value="/get-newsapi-news", method=RequestMethod.GET)
	public List<NewsApi> getNewsApi() {
		return newsFeedRepository.findAll();
	}
	
	@RequestMapping(value="/get-nytimes-by-id/{id}", method=RequestMethod.GET)
	public NyTimes getById(@PathVariable(value="id") Integer id) {
		return newYorkTimesRepository.findById(id);
	}
	
	@CachePut(cacheNames = "nytimes")
	@RequestMapping(value="/update-nytimes-by-id", method=RequestMethod.POST)
	public NyTimes updateById(@RequestBody NyTimes nyTimes){
		return newYorkTimesRepository.save(nyTimes);
	}

}
