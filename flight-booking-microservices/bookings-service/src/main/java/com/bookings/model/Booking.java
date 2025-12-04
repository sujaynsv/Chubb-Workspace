package com.bookings.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	public Booking() {
		
	}
	public Booking(Long id, String pnr, Long flightId, String passengerName, String passengerEmail,
			Integer numberOfSeats, String status, LocalDateTime bookingDate) {
		super();
		this.id = id;
		this.pnr = pnr;
		this.flightId = flightId;
		this.passengerName = passengerName;
		this.passengerEmail = passengerEmail;
		this.numberOfSeats = numberOfSeats;
		this.status = status;
		this.bookingDate = bookingDate;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String pnr;
    
    @Column(nullable = false)
    private Long flightId;
    
    @Column(nullable = false)
    private String passengerName;
    
    @Column(nullable = false)
    private String passengerEmail;
    
    @Column(nullable = false)
    private Integer numberOfSeats;
    
    @Column(nullable = false)
    private String status; // CONFIRMED, CANCELLED
    
    @Column(nullable = false)
    private LocalDateTime bookingDate;
}
