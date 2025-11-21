package com.example.webflux.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webflux.model.Tutorial;
import com.example.webflux.repository.TutorialRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TutorialService {
	
	@Autowired
	private TutorialRepository tutorialrepository;
	
	public Mono<Tutorial> findById(int id){
		
		return tutorialrepository.findById(id);
		
	}
	
	public Flux<Tutorial> findAll(){
		
		return tutorialrepository.findAll();
	}
	
	public Flux<Tutorial> findByTitleContaining(String title){
		
		return tutorialrepository.findByTitleContaining(title);
	}
	
	public Flux<Tutorial> findByPublished(boolean published){
		
		return tutorialrepository.findByPublished(published);
	}
	
	public Mono<Tutorial> save(Tutorial tutorial){
		return tutorialrepository.save(tutorial);
	}
	
	public Mono<Tutorial> update(int id, Tutorial tutorial) {
        return tutorialrepository.findById(id)
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .flatMap(optionalTutorial -> {
                    if (optionalTutorial.isPresent()) {
                        Tutorial existingTutorial = optionalTutorial.get();
                        existingTutorial.setTitle(tutorial.getTitle());
                        existingTutorial.setDescription(tutorial.getDescription());
                        existingTutorial.setPublished(tutorial.isPublished());
                        return tutorialrepository.save(existingTutorial);
                    }
                    return Mono.empty();
                });
    }
	
	
	public Mono<Void> deleteById(int id){
		return tutorialrepository.deleteById(id);
	}
	
	public Mono<Void> deleteAll(){
		return tutorialrepository.deleteAll();
	}

}
