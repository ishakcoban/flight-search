package com.example.flightSearch.service;

import com.example.flightSearch.entity.Airport;
import com.example.flightSearch.entity.Flight;
import com.example.flightSearch.mapper.FlightMapper;
import com.example.flightSearch.modal.dto.FlightDto;
import com.example.flightSearch.modal.request.CreateFlightRequest;
import com.example.flightSearch.modal.request.FilterFlightRequest;
import com.example.flightSearch.modal.request.UpdateFlightRequest;
import com.example.flightSearch.repository.FlightRepository;
import com.example.flightSearch.utils.FlightSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportService airportService ;
    private final FlightMapper flightMapper;

    public Flight getById(Long id) {
        return flightRepository.findById(id).orElseThrow();
    }

    public FlightDto getOne(Long id)  {
        return flightMapper.toDto(flightRepository.findById(id).orElseThrow());
    }

    public List<FlightDto> filter(FilterFlightRequest filterFlightRequest) {
        Specification<Flight> spec = Specification.where(null);
        spec = spec.and(FlightSpecifications.getFilterQuery(filterFlightRequest));
        List<Flight> flights = flightRepository.findAll(spec);
        return flightMapper.toDtoList(flights);
    }

    public List<FlightDto> getAll()  {
        return flightMapper.toDtoList(flightRepository.findAll());
    }
    public void create(CreateFlightRequest createFlightRequest) {
        Airport departureAirport = airportService.getById(createFlightRequest.getDepartureAirport());
        Airport arrivalAirport = airportService.getById(createFlightRequest.getArrivalAirport());
        Flight flight = flightMapper.create(createFlightRequest,departureAirport,arrivalAirport);
        flightRepository.save(flight);
    }

    public void update(Long id, UpdateFlightRequest updateFlightRequest) {
        Airport departureAirport = airportService.getById(updateFlightRequest.getDepartureAirport());
        Airport arrivalAirport = airportService.getById(updateFlightRequest.getArrivalAirport());
        Flight flight = flightMapper.update(id, departureAirport,arrivalAirport, updateFlightRequest);
        flightRepository.save(flight);
    }

    public void delete(Long id) {
        Flight flight = flightRepository.findById(id).orElseThrow();
        flightRepository.delete(flight);
    }


}
