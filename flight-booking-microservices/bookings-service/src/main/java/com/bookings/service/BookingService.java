package com.bookings.service;

import com.bookings.client.FlightClient;
import com.bookings.dto.BookingRequest;
import com.bookings.dto.Flight;
import com.bookings.message.MessageProducer;
import com.bookings.model.Booking;
import com.bookings.repository.BookingRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class BookingService {
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private FlightClient flightClient;
    
    @Autowired
    private MessageProducer messageProducer;
    
    @CircuitBreaker(
        name = "flightServiceCB",
        fallbackMethod = "createBookingWithFallback"
    )
    public Booking createBooking(BookingRequest request) {
        // Call Flights Service via Feign
        Flight flight = flightClient.getFlightById(request.getFlightId());
        
        // Check seat availability
        if (flight.getAvailableSeats() < request.getNumberOfSeats()) {
            throw new RuntimeException("Insufficient seats available for flight");
        }
        
        // Update seats in Flights Service
        flightClient.updateSeats(request.getFlightId(), request.getNumberOfSeats());
        
        // Create booking
        Booking booking = new Booking();
        booking.setPnr(generatePNR());
        booking.setFlightId(request.getFlightId());
        booking.setPassengerName(request.getPassengerName());
        booking.setPassengerEmail(request.getPassengerEmail());
        booking.setNumberOfSeats(request.getNumberOfSeats());
        booking.setStatus("CONFIRMED");
        booking.setBookingDate(LocalDateTime.now());
        
        Booking savedBooking = bookingRepository.save(booking);
        
        // Send confirmation message
        messageProducer.sendBookingConfirmation(savedBooking);
        
        System.out.println("Booking created with PNR: " + savedBooking.getPnr());
        return savedBooking;
    }
    
    // Fallback method when Flights Service is down
    public Booking createBookingWithFallback(BookingRequest request, Exception ex) {
        System.out.println("CIRCUIT BREAKER TRIGGERED! Flights Service unavailable.");
        throw new RuntimeException("Flights Service is currently unavailable. Please try again later.");
    }
    
    public Booking getBookingByPnr(String pnr) {
        return bookingRepository.findByPnr(pnr)
            .orElseThrow(() -> new RuntimeException("Booking not found with PNR: " + pnr));
    }
    
    public Booking cancelBooking(String pnr) {
        Booking booking = getBookingByPnr(pnr);
        booking.setStatus("CANCELLED");
        return bookingRepository.save(booking);
    }
    
    private String generatePNR() {
        return "PNR" + System.currentTimeMillis();
    }
}
