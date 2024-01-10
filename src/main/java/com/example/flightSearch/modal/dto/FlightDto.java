package com.example.flightSearch.modal.dto;

import com.example.flightSearch.entity.Airport;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {

    private Long id;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDateTime departureDate;
    private LocalDateTime returnDate;
    private double price;
}
