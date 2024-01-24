package com.example.flightSearch.service;

import com.example.flightSearch.entity.Airport;
import com.example.flightSearch.entity.Flight;
import com.example.flightSearch.mapper.FlightMapper;
import com.example.flightSearch.modal.request.CreateFlightRequest;
import com.example.flightSearch.modal.request.UpdateFlightRequest;
import com.example.flightSearch.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
        // Given
        Airport departureAirport = Airport.builder()
                .id(1L)
                .city("airport 1")
                .build();

        Airport arrivalAirport = Airport.builder()
                .id(2L)
                .city("airport 2")
                .build();

        Flight flight = Flight.builder()
                .id(3L)
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .departureDate(LocalDateTime.now().plusDays(1))
                .returnDate(LocalDateTime.now().plusDays(2))
                .price(1200)
                .build();

        when(flightRepository.findById(flight.getId())).thenReturn(Optional.of(flight));

        // When
        Flight expectedFlight = flightService.getById(flight.getId());

        // Then
        verify(flightRepository).findById(flight.getId());
        assertEquals(expectedFlight, flight);

    }

    @Test
    public void it_should_create_when_valid_request() {
        // Given
        CreateFlightRequest request = CreateFlightRequest.builder()
                .departureAirport(1L)
                .arrivalAirport(2L)
                .departureDate(LocalDateTime.now().plusDays(1))
                .returnDate(LocalDateTime.now().plusDays(2))
                .price(100.0)
                .build();

        Airport departureAirport = Airport.builder()
                .id(1L)
                .city("airport 1")
                .build();

        Airport arrivalAirport = Airport.builder()
                .id(2L)
                .city("airport 2")
                .build();

        Flight flight = Flight.builder()
                .id(1L)
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .departureDate(request.getDepartureDate())
                .returnDate(request.getReturnDate())
                .build();
        when(airportService.getById(anyLong())).thenReturn(departureAirport, arrivalAirport);
        when(flightMapper.create(request, departureAirport, arrivalAirport)).thenReturn(flight);

        // When
        flightService.create(request);

        // Then
        verify(flightRepository).save(flight);
    }

    @Test
    public void it_should_throw_bad_request_when_invalid_request_includes_same_departure_and_arrival_airports() {
        // Given
        CreateFlightRequest request = CreateFlightRequest.builder()
                .departureAirport(1L)
                .arrivalAirport(1L)
                .departureDate(LocalDateTime.now().plusDays(1))
                .returnDate(LocalDateTime.now().plusDays(2))
                .price(100.0)
                .build();

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            flightService.create(request);
        });

        // Asserting the expected HttpStatus
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Airports that are provided cannot be same!", exception.getReason());
    }

    @Test
    public void it_should_throw_bad_request_when_departure_date_in_invalid_request_is_in_past() {
        // Given
        CreateFlightRequest request = CreateFlightRequest.builder()
                .departureAirport(1L)
                .arrivalAirport(2L)
                .departureDate(LocalDateTime.now().minusDays(1))
                .returnDate(LocalDateTime.now().plusDays(1))
                .price(100)
                .build();

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            flightService.create(request);
        });

        // Asserting the expected HttpStatus
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("The departure date must be after the now!", exception.getReason());
    }

    @Test
    public void it_should_throw_bad_request_when_return_date_in_invalid_request_is_before_departure_date() {
        // Given
        CreateFlightRequest request = CreateFlightRequest.builder()
                .departureAirport(1L)
                .arrivalAirport(2L)
                .departureDate(LocalDateTime.now().plusDays(2))
                .returnDate(LocalDateTime.now().plusDays(1))
                .price(100)
                .build();

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            flightService.create(request);
        });

        // Asserting the expected HttpStatus
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("The arrival date must be after the departure date!", exception.getReason());
    }

    @Test
    public void it_should_throw_bad_request_when_price_in_invalid_request_is_less_than_zero() {
        // Given
        CreateFlightRequest request = CreateFlightRequest.builder()
                .departureAirport(1L)
                .arrivalAirport(2L)
                .departureDate(LocalDateTime.now().plusDays(1))
                .returnDate(LocalDateTime.now().plusDays(2))
                .price(-100)
                .build();

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            flightService.create(request);
        });

        // Asserting the expected HttpStatus
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Price must not be smaller than zero!", exception.getReason());
    }

    @Test
    public void it_should_update() {
        // Given
        UpdateFlightRequest request = UpdateFlightRequest.builder()
                .departureAirport(1L)
                .arrivalAirport(2L)
                .departureDate(LocalDateTime.now().plusDays(1))
                .returnDate(LocalDateTime.now().plusDays(2))
                .price(-100)
                .build();

        Flight flight = Flight.builder()
                .id(1L)
                .build();

        Airport departureAirport = Airport.builder()
                .id(1L)
                .city("airport 1")
                .build();

        Airport arrivalAirport = Airport.builder()
                .id(2L)
                .city("airport 2")
                .build();

        when(flightService.getById(flight.getId())).thenReturn(flight);
        when(airportService.getById(request.getDepartureAirport())).thenReturn(departureAirport);
        when(airportService.getById(request.getArrivalAirport())).thenReturn(arrivalAirport);
        when(flightMapper.update(flight.getId(),departureAirport,arrivalAirport,request)).thenReturn(flight);

        // When
        flightService.update(1L,request);

        // Then
        verify(flightRepository).save(flight);
    }

    @Test
    public void it_should_delete() {

        // Given
        Flight flight = Flight.builder()
                .id(1L)
                .build();

        when(flightService.getById(flight.getId())).thenReturn(flight);

        // When
        flightService.delete(flight.getId());

        // Then
        verify(flightRepository).delete(flight);
    }

}
