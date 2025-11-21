package com.example.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.webflux.model.Tutorial;
import com.example.webflux.service.TutorialService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping("/tutorials")
public class TutorialController {

	@Autowired
	TutorialService tutorialservice;
	
	@GetMapping
	public Flux<Tutorial> getAllTutorials(@RequestParam(required=false) String title){
		
		if(title==null||title.isEmpty()) {
			return tutorialservice.findAll();
		}
		else {
			return tutorialservice.findByTitleContaining(title);
		}
		
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<Tutorial>> getTutorialById(@PathVariable("id") int id){
		
		return tutorialservice.findById(id)
				.map(tutorial -> ResponseEntity.ok(tutorial))
				.defaultIfEmpty(ResponseEntity.notFound().build());
		
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Integer> createTutorial(@RequestBody Tutorial tutorial) {
	    return tutorialservice.save(
	            new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false)
	    ).map(savedTutorial -> savedTutorial.getId());
	}


    
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Tutorial>> updateTutorial(
    		@PathVariable("id") int id,
    		@RequestBody Tutorial tutorial)	{
    	
    		return tutorialservice.update(id,tutorial)
    				.map(updateTutorial->ResponseEntity.ok(updateTutorial))
    				.defaultIfEmpty(ResponseEntity.notFound().build());
    			
    		}
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteTutorial(@PathVariable("id") int id) {
        return tutorialservice.deleteById(id);
    }
    
    /**
     * DELETE /api/tutorials - Delete all tutorials
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAllTutorials() {
        return tutorialservice.deleteAll();
    }
    
    /**
     * GET /api/tutorials/published - Get all published tutorials
     */
    @GetMapping("/published")
    public Flux<Tutorial> findByPublished() {
        return tutorialservice.findByPublished(true);
    }
}
