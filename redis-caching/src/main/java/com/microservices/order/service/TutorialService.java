package com.microservices.order.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.microservices.order.model.Tutorial;
import com.microservices.order.repository.TutorialRepository;

import java.util.List;
import java.util.Optional;

@Service
@EnableCaching
public class TutorialService {

    @Autowired
    private TutorialRepository tutorialRepository;

    @Cacheable(value = "tutorials", key = "'all'")
    public List<Tutorial> findAll() {
        simulateSlowService();
        return tutorialRepository.findAll();
    }

    @Cacheable(value = "tutorials", key = "#title")
    public List<Tutorial> findByTitleContaining(String title) {
        simulateSlowService();
        return tutorialRepository.findByTitleContaining(title);
    }

    @Cacheable(value = "tutorial", key = "#id")
    public Optional<Tutorial> findById(String id) {
        simulateSlowService();
        return tutorialRepository.findById(id);
    }

    @Cacheable(value = "published_tutorials", key = "#isPublished")
    public List<Tutorial> findByPublished(boolean isPublished) {
        simulateSlowService();
        return tutorialRepository.findByPublished(isPublished);
    }

    public Tutorial save(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    @CachePut(value = "tutorial", key = "#tutorial.id")
    public Tutorial update(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    @CacheEvict(value = {"tutorial", "tutorials", "published_tutorials"}, allEntries = true)
    public void deleteById(String id) {
        tutorialRepository.deleteById(id);
    }

    @CacheEvict(value = {"tutorial", "tutorials", "published_tutorials"}, allEntries = true)
    public void deleteAll() {
        tutorialRepository.deleteAll();
    }

    private void simulateSlowService() {
        try {
            Thread.sleep(3000L); // 3 second delay to simulate DB query
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Service interrupted", e);
        }
    }
}
