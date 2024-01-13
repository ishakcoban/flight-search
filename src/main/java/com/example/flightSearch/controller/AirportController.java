package com.example.flightSearch.controller;

import com.example.flightSearch.modal.dto.AirportDto;
import com.example.flightSearch.modal.dto.FlightDto;
import com.example.flightSearch.modal.request.CreateAirportRequest;
import com.example.flightSearch.modal.request.UpdateAirportRequest;
import com.example.flightSearch.service.AirportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/airports")
@RequiredArgsConstructor
@SecurityRequirement(name="bearerAuth")
public class AirportController {

    private final AirportService airportService;

    @Operation(description = "Get an airport information by id.")
    @GetMapping("/{id}")
    public AirportDto getOne(@PathVariable Long id) {
        return airportService.getOne(id);
    }

    @Operation(description = "Get all airports information.")
    @GetMapping
    public List<AirportDto> getAll() {
        return airportService.getAll();
    }

    @Operation(description = "Create an airport with received city information.")
    @PostMapping
    public void create(@RequestBody CreateAirportRequest createAirportRequest) {
        airportService.create(createAirportRequest);
    }

    @Operation(description = "Update an airport by provided information.")
    @PutMapping("/{id}")
    public void update(@PathVariable Long id,@RequestBody UpdateAirportRequest updateAirportRequest) {
        airportService.update(id,updateAirportRequest);
    }

    @Operation(description = "Delete an airport by id.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        airportService.delete(id);
    }

}
