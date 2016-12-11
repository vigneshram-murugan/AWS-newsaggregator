package com.news.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.news.domain.NyTimes;

public interface NewYorkTimesRepository extends JpaRepository<NyTimes, Long> {
	
	List<NyTimes> findAll();
	NyTimes findById(int id);
}
