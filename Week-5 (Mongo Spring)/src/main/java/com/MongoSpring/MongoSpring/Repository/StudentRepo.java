package com.MongoSpring.MongoSpring.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MongoSpring.MongoSpring.Model.Student;

public interface StudentRepo extends MongoRepository<Student, Integer> {
	
	

}
