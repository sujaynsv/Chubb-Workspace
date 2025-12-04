package com.bookings.message;

import com.bookings.model.Booking;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    private static final String EXCHANGE_NAME = "booking-exchange";
    private static final String ROUTING_KEY = "booking.confirmation";
    
    public void sendBookingConfirmation(Booking booking) {
        String message = String.format(
            "Booking Confirmed! PNR: %s, Passenger: %s, Email: %s, Seats: %d",
            booking.getPnr(),
            booking.getPassengerName(),
            booking.getPassengerEmail(),
            booking.getNumberOfSeats()
        );
        
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message);
        System.out.println("Message sent to RabbitMQ: " + message);
    }
}
