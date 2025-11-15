package com.example.controller;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;  // ADD THIS IMPORT
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "orders")  // ADD THIS LINE
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  // Changed from AUTO to IDENTITY
	private int id;
	@NotBlank
	private String item;
	@Min(value=34, message=" Greater than 34.")
	private float price;
	@Min(value=1)
	private int quantity;
	@Embedded
	private Address address;

	public void setAddress(Address address) {
		this.address=address;
	}
	public Address getAddress() {
		return address;
	}

	public float totalPrice() {
		return quantity*price;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public float getPrice() {
		return totalPrice();
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity=quantity;
	}
	
	public int getId() {
		return id;
	}

	public String display() {
		return "Address: "+address.getCity()+address.getPin()+address.getState();
	}

}