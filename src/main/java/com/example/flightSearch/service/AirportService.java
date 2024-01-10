package com.example.flightSearch.service;

import com.example.flightSearch.entity.Airport;
import com.example.flightSearch.mapper.AirportMapper;
import com.example.flightSearch.modal.dto.AirportDto;
import com.example.flightSearch.modal.request.CreateAirportRequest;
import com.example.flightSearch.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    public Airport getById(Long id)  {
        return airportRepository.findById(id).orElseThrow();
    }
    public AirportDto getOne(Long id)  {
        return airportMapper.toDto(airportRepository.findById(id).orElseThrow());
    }

    public List<AirportDto> getAll()  {
        return airportMapper.toDtoList(airportRepository.findAll());
    }

    public void create(CreateAirportRequest createAirportRequest) {
        Airport airport = airportMapper.create(createAirportRequest);
        airportRepository.save(airport);
    }
    public void update(Long id,String city) {
        Airport airport = airportMapper.update(id,city);
        airportRepository.save(airport);
    }
    public void delete(Long id) {
        Airport airport = airportRepository.findById(id).orElseThrow();
        airportRepository.delete(airport);
    }
}
