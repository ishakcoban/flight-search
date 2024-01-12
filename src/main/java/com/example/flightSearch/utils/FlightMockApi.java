package com.example.flightSearch.utils;

import com.example.flightSearch.modal.request.CreateFlightRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightMockApi {

    public static List<CreateFlightRequest> getFlights() {
        List<CreateFlightRequest> flights = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            CreateFlightRequest flight = CreateFlightRequest.builder()
                    .departureAirport(getRandomAirport())
                    .arrivalAirport(getRandomAirport())
                    .departureDate(generateRandomDateTime())
                    .returnDate(generateRandomDateTime())
                    .price(1000 + (int) (Math.random() * 2000))
                    .build();
            flights.add(flight);
        }
        return flights;
    }

    private static long getRandomAirport() {
        long[] airports = { 2, 3, 4, 5, 6,7};
        return airports[(int) (Math.random() * airports.length)];
    }

    public static LocalDateTime generateRandomDateTime() {

        LocalDateTime now = LocalDateTime.now();
        long randomDays = ThreadLocalRandom.current().nextLong(1, 31);

        return now.plus(randomDays, ChronoUnit.DAYS);
    }
}