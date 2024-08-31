package com.example.Management.Service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
public class OrderNumberGenerator {

    public String generateOrderNumber( Long eventId) {
        // Get the current date and time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());

        // Increment the counter

        // Generate a random 4-digit number
        Random random = new Random();
        int randomNumber = 1000 + random.nextInt(9000);

        // Combine timestamp, counter, and random number to create the order number
        return "ORD-" + timestamp + "-" + eventId + "-" + randomNumber;
    }

}
