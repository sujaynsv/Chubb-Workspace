package com.bookings.dto;

import java.time.LocalDateTime;

public class Flight {
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getFromPlace() {
		return fromPlace;
	}
	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}
	public String getToPlace() {
		return toPlace;
	}
	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}
	public LocalDateTime getDepartureDateTime() {
		return departureDateTime;
	}
	public void setDepartureDateTime(LocalDateTime departureDateTime) {
		this.departureDateTime = departureDateTime;
	}
	public LocalDateTime getArrivalDateTime() {
		return arrivalDateTime;
	}
	public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}
	public Integer getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}
	public Integer getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Flight() {
		
	}
	
	public Flight(Long id, String flightNumber, String fromPlace, String toPlace, LocalDateTime departureDateTime,
			LocalDateTime arrivalDateTime, Integer totalSeats, Integer availableSeats, Double price, String status) {
		super();
		this.id = id;
		this.flightNumber = flightNumber;
		this.fromPlace = fromPlace;
		this.toPlace = toPlace;
		this.departureDateTime = departureDateTime;
		this.arrivalDateTime = arrivalDateTime;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		this.price = price;
		this.status = status;
	}
	private Long id;
    private String flightNumber;
    private String fromPlace;
    private String toPlace;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private Integer totalSeats;
    private Integer availableSeats;
    private Double price;
    private String status;
}
