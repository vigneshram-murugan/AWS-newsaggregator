package com.news.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class NytimesFeeds extends AbstractEntity {
	
	private String web_url;
	
	@Lob
	private String snippet;
	
	@Lob
	private String lead_paragraph;
	private String source;
	
	@JsonProperty("headline")
	@OneToOne(cascade=CascadeType.ALL)
	private NytimeHeadLine headline;
	
	private String pub_date;

	public String getWeb_url() {
		return web_url;
	}

	public void setWeb_url(String web_url) {
		this.web_url = web_url;
	}

	public String getSnippet() {
		return snippet;
	}

	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}

	public String getLead_paragraph() {
		return lead_paragraph;
	}

	public void setLead_paragraph(String lead_paragraph) {
		this.lead_paragraph = lead_paragraph;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public NytimeHeadLine getHeadline() {
		return headline;
	}

	public void setHeadline(NytimeHeadLine headline) {
		this.headline = headline;
	}

	public String getPub_date() {
		return pub_date;
	}

	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}
	
	

}
