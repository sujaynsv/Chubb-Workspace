package com.chubb.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.chubb.controller", "com.chubb.service"})
@EntityScan("com.chubb.model")
@EnableJpaRepositories("com.chubb.repository")
public class OneToManyProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneToManyProjectApplication.class, args);
	}

}
