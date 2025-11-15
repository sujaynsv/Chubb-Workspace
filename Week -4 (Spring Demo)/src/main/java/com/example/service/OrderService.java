package com.example.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//
import com.example.controller.*;
import com.example.repository.OrderRepository;

//import com.sun.tools.javac.util.Log;
//
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public void insertOrder(Order order) {
		
		orderRepository.save(order);
		
//	.debug(order.toString());
	}
}
