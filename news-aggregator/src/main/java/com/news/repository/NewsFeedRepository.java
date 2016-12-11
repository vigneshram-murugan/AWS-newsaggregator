package com.news.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.news.domain.NewsApi;

public interface NewsFeedRepository extends JpaRepository<NewsApi, Long> {
	
	List<NewsApi> findAll();

}
