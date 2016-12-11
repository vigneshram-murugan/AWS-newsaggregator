package com.news.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class NyTimes  extends AbstractEntity {
	
	@JsonProperty("status")
	private String status;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JsonProperty("response")
	private NyResponse response;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public NyResponse getResponse() {
		return response;
	}
	public void setResponse(NyResponse response) {
		this.response = response;
	}
	
	

}
