package com.example.flightSearch.mapper;

import com.example.flightSearch.entity.Airport;
import com.example.flightSearch.entity.Flight;
import com.example.flightSearch.modal.dto.AirportDto;
import com.example.flightSearch.modal.dto.FlightDto;
import com.example.flightSearch.modal.request.CreateFlightRequest;
import com.example.flightSearch.modal.request.UpdateFlightRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FlightMapper {

    public FlightDto toDto(Flight flight) {

        return FlightDto.builder()
                .id(flight.getId())
                .departureAirport(flight.getDepartureAirport())
                .arrivalAirport(flight.getArrivalAirport())
                .departureDate(flight.getDepartureDate())
                .returnDate(flight.getReturnDate())
                .price(flight.getPrice())
                .build();
    }

    public Flight create(CreateFlightRequest createFlightRequest, Airport departureAirport, Airport arrivalAirport) {

        return Flight.builder()
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .departureDate(createFlightRequest.getDepartureDate())
                .returnDate(createFlightRequest.getReturnDate())
                .price(createFlightRequest.getPrice())
                .build();
    }

    public Flight update(Long id, Airport departureAirport, Airport arrivalAirport, UpdateFlightRequest updateFlightRequest) {
        return Flight.builder()
                .id(id)
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .departureDate(updateFlightRequest.getDepartureDate())
                .returnDate(updateFlightRequest.getReturnDate())
                .price(updateFlightRequest.getPrice())
                .build();
    }

    public List<FlightDto> toDtoList(List<Flight> adverts) {
        return adverts.stream().map(this::toDto).collect(Collectors.toList());
    }
}
