package com.example.flightSearch.service;

import com.example.flightSearch.entity.Airport;
import com.example.flightSearch.entity.Flight;
import com.example.flightSearch.mapper.AirportMapper;
import com.example.flightSearch.mapper.FlightMapper;
import com.example.flightSearch.modal.request.UpdateAirportRequest;
import com.example.flightSearch.modal.request.UpdateFlightRequest;
import com.example.flightSearch.repository.AirportRepository;
import com.example.flightSearch.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

    @Mock
    private FlightMapper flightMapper;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private AirportService airportService;

    @InjectMocks
    private FlightService flightService;

    @Test
    public void it_should_get_by_id() {

        long id = 1L;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        Airport departureAirport = Airport.builder()
                .id(id)
                .city("Ankara")
                .build();

        Airport arrivalAirport = Airport.builder()
                .id(id)
                .city("Izmir")
                .build();

        Flight flight = Flight.builder()
                .id(id)
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .departureDate(LocalDateTime.parse("2024-03-13 17:09:42.411", formatter))
                .returnDate(LocalDateTime.parse("2024-03-13 20:09:42.411", formatter))
                .price(1200)
                .build();

        when(flightRepository.findById(id)).thenReturn(Optional.of(flight));

        Flight expectedFlight = flightService.getById(id);

        verify(flightRepository).findById(id);
        assertEquals(expectedFlight, flight);

    }

    @Test
    public void it_should_delete() {

        long id = 1L;
        Flight flight = Flight.builder()
                .id(id)
                .build();

        when(flightRepository.findById(id)).thenReturn(Optional.of(flight));

        flightService.delete(id);

        verify(flightRepository).delete(flight);
    }

    @Test
    public void it_should_update() {

        long flight_id = 1L;
        long first_airport_id = 2L;
        long second_airport_id = 3L;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        Airport departureAirport = Airport.builder()
                .id(first_airport_id)
                .city("Ankara")
                .build();

        Airport arrivalAirport = Airport.builder()
                .id(second_airport_id)
                .city("Izmir")
                .build();

        UpdateFlightRequest request = UpdateFlightRequest.builder()
                .departureAirport(departureAirport.getId())
                .arrivalAirport(arrivalAirport.getId())
                .departureDate(LocalDateTime.parse("2024-03-13 17:09:42.411", formatter))
                .returnDate(LocalDateTime.parse("2024-03-13 20:09:42.411", formatter))
                .price(1250)
                .build();

        Flight updatedFlight = Flight.builder()
                .id(flight_id)
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .departureDate(LocalDateTime.parse("2024-03-13 17:09:42.411", formatter))
                .returnDate(LocalDateTime.parse("2024-03-13 20:09:42.411", formatter))
                .price(1250)
                .build();

        when(flightMapper.update(flight_id, departureAirport, arrivalAirport, request)).thenReturn(updatedFlight);
        when(flightRepository.save(updatedFlight)).thenReturn(updatedFlight);

        flightService.update(flight_id, request);

        verify(flightMapper).update(flight_id, departureAirport, arrivalAirport, request);
        verify(flightRepository).save(updatedFlight);

    }
}
