package com.flights.controller;

import com.flights.model.Flight;
import com.flights.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    
    @Autowired
    private FlightService flightService;
    
    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
        @RequestParam String from,
        @RequestParam String to
    ) {
        List<Flight> flights = flightService.searchFlights(from, to);
        return ResponseEntity.ok(flights);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Flight flight = flightService.getFlightById(id);
        return ResponseEntity.ok(flight);
    }
    
    @PutMapping("/{id}/seats")
    public ResponseEntity<Flight> updateSeats(
        @PathVariable Long id,
        @RequestParam Integer count
    ) {
        Flight flight = flightService.updateAvailableSeats(id, count);
        return ResponseEntity.ok(flight);
    }
    
    @PostMapping
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        Flight savedFlight = flightService.addFlight(flight);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFlight);
    }
    
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }
}
