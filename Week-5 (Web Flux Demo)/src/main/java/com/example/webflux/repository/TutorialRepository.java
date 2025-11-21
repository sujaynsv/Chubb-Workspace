package com.example.webflux.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.example.webflux.model.Tutorial;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface TutorialRepository extends R2dbcRepository<Tutorial, Integer>{
	
	Flux<Tutorial> findByPublished(boolean published);
	
	@Query("SELECT * FROM tutorial WHERE LOWER(title) LIKE LOWER(CONCAT('%', :title, '%'))")
	Flux<Tutorial> findByTitleContaining(String title);
	
	@Query("Delete FROM tutorial")
	Mono deleteAll();


	
	
}
