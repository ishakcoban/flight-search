package com.example.flightSearch.modal.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateFlightRequest {

    private Long departureAirport;
    private Long arrivalAirport;
    private LocalDateTime departureDate;
    private LocalDateTime returnDate;
    private double price;

}
