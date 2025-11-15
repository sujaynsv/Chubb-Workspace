package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.OrderService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class OrderController {
	
	@Autowired 	
	OrderService service;
	
	@GetMapping("/")
	String order() {
		return "new one";
	}
	
	@PostMapping("/order")
	Order saveOrder(@RequestBody @Valid Order order) {
		//log.debug("logger added");
		service.insertOrder(order);
		return order;
	}
	@GetMapping("/order")
	String getOrder() {
		return "hello";
	}
	
	

}

