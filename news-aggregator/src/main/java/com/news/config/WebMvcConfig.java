package com.news.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig {
	
	@Bean
	public WebConfig initateConfig() {
		return new WebConfig();
	}
	
	protected class WebConfig extends WebMvcConfigurerAdapter {
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			// to fire index.html page when the url is /home
			registry.addViewController("/home").setViewName("home/index");
		}
	}
}
