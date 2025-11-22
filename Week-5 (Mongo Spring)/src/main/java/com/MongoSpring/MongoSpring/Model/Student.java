package com.MongoSpring.MongoSpring.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

@Document
//@NoArgsConstructor
//@AllArgsConstructor
public class Student {
	@Id
	private Integer rno;	
	
	public Student(Integer rno, String name, String address) {
		super();
		this.rno = rno;
		this.name = name;
		this.address = address;
	}

	private String name;
	
	private String address;
	
	public Integer getRno() {
		return rno;
	}

	public void setRno(Integer rno) {
		this.rno = rno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddreess() {
		return address;
	}

	public void setAddreess(String addreess) {
		this.address = addreess;
	}
}
