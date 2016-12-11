package com.news.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class NytimeHeadLine extends AbstractEntity {
	
	private String main;
	
	@Lob
	private String print_headline;
	
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}
	public String getPrint_headline() {
		return print_headline;
	}
	public void setPrint_headline(String print_headline) {
		this.print_headline = print_headline;
	}
	
	

}
