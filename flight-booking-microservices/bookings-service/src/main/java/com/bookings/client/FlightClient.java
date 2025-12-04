package com.bookings.client;

import com.bookings.dto.Flight;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "FLIGHTS-SERVICE")
public interface FlightClient {
    
    @GetMapping("/flights/{id}")
    Flight getFlightById(@PathVariable("id") Long id);
    
    @PutMapping("/flights/{id}/seats")
    Flight updateSeats(@PathVariable("id") Long id, @RequestParam("count") Integer count);
}
