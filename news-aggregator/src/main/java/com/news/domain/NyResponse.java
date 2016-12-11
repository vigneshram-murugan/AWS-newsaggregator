package com.news.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class NyResponse extends AbstractEntity {
	
	@OrderBy("id DESC")
	@OneToMany(cascade=CascadeType.ALL)
	@JsonProperty("docs")
	private List<NytimesFeeds> docs;

	public List<NytimesFeeds> getDocs() {
		return docs;
	}

	public void setDocs(List<NytimesFeeds> docs) {
		this.docs = docs;
	}	
}
