package com.example.flightSearch.service;

import com.example.flightSearch.entity.Airport;
import com.example.flightSearch.mapper.AirportMapper;
import com.example.flightSearch.modal.dto.AirportDto;
import com.example.flightSearch.modal.request.CreateAirportRequest;
import com.example.flightSearch.modal.request.UpdateAirportRequest;
import com.example.flightSearch.repository.AirportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AirportServiceTest {

    @Mock
    private AirportMapper airportMapper;

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private AirportService airportService;

    @Test
    public void it_should_get_by_id() {

        long id = 1L;
        Airport airport = Airport.builder()
                .id(id)
                .build();

        when(airportRepository.findById(id)).thenReturn(Optional.of(airport));

        Airport expectedAirport = airportService.getById(id);

        verify(airportRepository).findById(id);
        assertEquals(expectedAirport, airport);

    }

    @Test
    public void it_should_delete() {

        long id = 1L;
        Airport airport = Airport.builder()
                .id(id)
                .build();

        when(airportRepository.findById(id)).thenReturn(Optional.of(airport));

        airportService.delete(id);

        verify(airportRepository).delete(airport);
    }

    @Test
    public void it_should_update() {

        Long id = 1L;
        UpdateAirportRequest request = UpdateAirportRequest.builder()
                .city("Ankara")
                .build();
        Airport updatedAirport = Airport.builder()
                .id(id)
                .city("Adana")
                .build();


        when(airportMapper.update(id, request)).thenReturn(updatedAirport);
        when(airportRepository.save(updatedAirport)).thenReturn(updatedAirport);

        airportService.update(id, request);

        verify(airportMapper).update(id, request);
        verify(airportRepository).save(updatedAirport);

    }

    @Test
    public void it_should_create() {

        CreateAirportRequest request = CreateAirportRequest.builder()
                .city("Ankara")
                .build();
        long id = 1L;
        Airport airport = Airport.builder()
                .id(id)
                .city("Ankara")
                .build();

        when(airportMapper.create(request)).thenReturn(airport);
        when(airportRepository.save(airport)).thenReturn(airport);

        airportService.create(request);

        verify(airportMapper).create(request);
        verify(airportRepository).save(airport);

    }

    @Test
    void it_should_get_all() {

        Long first_airport_id = 1L;
        Long second_airport_id = 2L;
        List<Airport> airports = List.of(
                Airport.builder()
                        .id(first_airport_id)
                        .city("Ankara")
                        .build(),
                Airport.builder()
                        .id(second_airport_id)
                        .city("Izmir")
                        .build()
        );
        List<AirportDto> expectedDtos = List.of(
                AirportDto.builder()
                        .id(first_airport_id)
                        .city("Ankara")
                        .build(),
                AirportDto.builder()
                        .id(first_airport_id)
                        .city("Izmir")
                        .build()
        );

        when(airportRepository.findAll()).thenReturn(airports);

        when(airportMapper.toDtoList(airports)).thenReturn(expectedDtos);

        List<AirportDto> actualDtos = airportService.getAll();

        verify(airportRepository).findAll();

        verify(airportMapper).toDtoList(airports);

        assertEquals(expectedDtos, actualDtos);
    }
}
