package com.flights.service;

import com.flights.model.Flight;
import com.flights.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FlightService {
    
    @Autowired
    private FlightRepository flightRepository;
    
    public List<Flight> searchFlights(String fromPlace, String toPlace) {
        return flightRepository.findByFromPlaceAndToPlace(fromPlace, toPlace);
    }
    
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Flight not found with ID: " + id));
    }
    
    public Flight updateAvailableSeats(Long flightId, Integer seatsToBook) {
        Flight flight = getFlightById(flightId);
        
        if (flight.getAvailableSeats() < seatsToBook) {
            throw new RuntimeException("Insufficient seats available");
        }
        
        flight.setAvailableSeats(flight.getAvailableSeats() - seatsToBook);
        return flightRepository.save(flight);
    }
    
    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }
    
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
}
