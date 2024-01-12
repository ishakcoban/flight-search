package com.example.flightSearch.mapper;

import com.example.flightSearch.entity.Airport;
import com.example.flightSearch.entity.Flight;
import com.example.flightSearch.modal.dto.*;
import com.example.flightSearch.modal.request.CreateFlightRequest;
import com.example.flightSearch.modal.request.UpdateFlightRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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

    public FlightForFilterDto toDtoForFilter(Flight flight, LocalDateTime returnDate) {

        DepartureDto departureDto = DepartureDto.builder()
                .departureAirport(flight.getDepartureAirport())
                .arrivalAirport(flight.getArrivalAirport())
                .departureDate(flight.getDepartureDate())
                .price(flight.getPrice())
                .build();

        ReturnDto returnDto;
        if (flight.getReturnDate() != null && returnDate != null) {
            returnDto = ReturnDto.builder()
                    .departureAirport(flight.getArrivalAirport())
                    .arrivalAirport(flight.getDepartureAirport())
                    .departureDate(flight.getReturnDate())
                    .price(flight.getPrice())
                    .build();

            return FlightForFilterDto.builder()
                    .id(flight.getId())
                    .departureFlight(departureDto)
                    .returnFlight(returnDto)
                    .build();
        }

        return FlightForFilterDto.builder()
                .id(flight.getId())
                .departureFlight(departureDto)
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

    public List<FlightDto> toDtoList(List<Flight> flights) {
        return flights.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<FlightForFilterDto> toDtoListForFilter(List<Flight> flights, LocalDateTime returnDate) {
        return flights.stream().map(flight -> toDtoForFilter(flight, returnDate)).collect(Collectors.toList());
    }
}
