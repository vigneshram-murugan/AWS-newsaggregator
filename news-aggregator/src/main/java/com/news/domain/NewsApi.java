package com.news.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class NewsApi extends AbstractEntity{
	
	private String status;
	private String source;
	private String sortBy;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Articles> articles;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public List<Articles> getArticles() {
		return articles;
	}

	public void setArticles(List<Articles> articles) {
		this.articles = articles;
	}
	
}
