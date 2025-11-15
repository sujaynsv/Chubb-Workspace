package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.controller.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
	
	
}
