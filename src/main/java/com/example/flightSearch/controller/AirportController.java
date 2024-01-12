package com.example.flightSearch.controller;

import com.example.flightSearch.modal.dto.AirportDto;
import com.example.flightSearch.modal.dto.FlightDto;
import com.example.flightSearch.modal.request.CreateAirportRequest;
import com.example.flightSearch.modal.request.UpdateAirportRequest;
import com.example.flightSearch.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @GetMapping("/{id}")
    public AirportDto getOne(@PathVariable Long id) {
        return airportService.getOne(id);
    }
    @GetMapping
    public List<AirportDto> getAll() {
        return airportService.getAll();
    }
    @PostMapping
    public void create(@RequestBody CreateAirportRequest createAirportRequest) {
        airportService.create(createAirportRequest);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,@RequestBody UpdateAirportRequest updateAirportRequest) {
        airportService.update(id,updateAirportRequest);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        airportService.delete(id);
    }

}
