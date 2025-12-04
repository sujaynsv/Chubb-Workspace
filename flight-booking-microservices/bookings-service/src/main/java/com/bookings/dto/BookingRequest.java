package com.bookings.dto;


public class BookingRequest {
    public Long getFlightId() {
		return flightId;
	}
	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public String getPassengerEmail() {
		return passengerEmail;
	}
	public void setPassengerEmail(String passengerEmail) {
		this.passengerEmail = passengerEmail;
	}
	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	public BookingRequest() {
		
	}
	
	public BookingRequest(Long flightId, String passengerName, String passengerEmail, Integer numberOfSeats) {
		super();
		this.flightId = flightId;
		this.passengerName = passengerName;
		this.passengerEmail = passengerEmail;
		this.numberOfSeats = numberOfSeats;
	}
	private Long flightId;
    private String passengerName;
    private String passengerEmail;
    private Integer numberOfSeats;
}
