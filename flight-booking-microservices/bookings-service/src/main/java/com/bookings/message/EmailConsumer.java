package com.bookings.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    
    @RabbitListener(queues = "booking-queue")
    public void receiveMessage(String message) {
        System.out.println("Email Consumer received: " + message);
        // TODO: Integrate with JavaMailSender to send actual emails
        sendEmail(message);
    }
    
    private void sendEmail(String message) {
        System.out.println("Sending email: " + message);
        // In production, use JavaMailSender here
    }
}
