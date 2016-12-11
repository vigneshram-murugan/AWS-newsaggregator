package com.news.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Articles extends AbstractEntity {
	
	private String author;
	
	@OrderBy("id DESC")
	private String title;
	
	//data type @Lob for storing more characters in String feild
	@Lob
	private String description;
	private String url;
	private String urlToImage;
	private String publishedAt;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrlToImage() {
		return urlToImage;
	}
	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}
	public String getPublishedAt() {
		return publishedAt;
	}
	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}
	
	
	
}
