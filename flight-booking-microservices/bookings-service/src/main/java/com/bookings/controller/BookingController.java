package com.bookings.controller;

import com.bookings.dto.BookingRequest;
import com.bookings.model.Booking;
import com.bookings.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;
    
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest request) {
        Booking booking = bookingService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }
    
    @GetMapping("/{pnr}")
    public ResponseEntity<Booking> getBookingByPnr(@PathVariable String pnr) {
        Booking booking = bookingService.getBookingByPnr(pnr);
        return ResponseEntity.ok(booking);
    }
    
    @DeleteMapping("/{pnr}")
    public ResponseEntity<Booking> cancelBooking(@PathVariable String pnr) {
        Booking booking = bookingService.cancelBooking(pnr);
        return ResponseEntity.ok(booking);
    }
}
