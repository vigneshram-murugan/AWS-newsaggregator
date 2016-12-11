package com.news.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.news.domain.BuzzFeed;

public interface BuzzFeedRepository extends JpaRepository<BuzzFeed, Long> {
	
	List<BuzzFeed> findAll();

}
