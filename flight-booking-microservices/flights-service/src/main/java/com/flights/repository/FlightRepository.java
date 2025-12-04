package com.flights.repository;

import com.flights.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByFromPlaceAndToPlace(String fromPlace, String toPlace);
    Optional<Flight> findByFlightNumber(String flightNumber);
}
