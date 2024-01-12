package com.example.flightSearch.modal.dto;

import com.example.flightSearch.entity.Airport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartureDto {

    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDateTime departureDate;
    private double price;
}
