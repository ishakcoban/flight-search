package com.example.flightSearch.controller;

import com.example.flightSearch.modal.dto.FlightDto;
import com.example.flightSearch.modal.dto.FlightForFilterDto;
import com.example.flightSearch.modal.request.CreateFlightRequest;
import com.example.flightSearch.modal.request.SearchFlightRequest;
import com.example.flightSearch.modal.request.UpdateFlightRequest;
import com.example.flightSearch.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/flights")
@RequiredArgsConstructor

public class FlightController {

    private final FlightService flightService;

    @Operation(description = "Get a flight information by id.")
    @SecurityRequirement(name="bearerAuth")
    @GetMapping("/{id}")
    public FlightDto getOne(@PathVariable Long id) {
        return flightService.getOne(id);
    }

    @Operation(description = "Get all flights information.")
    @GetMapping
    public List<FlightDto> getAll() {
        return flightService.getAll();
    }

    @Operation(description = "Search flights(filter mechanism) by provided information.")
    @PostMapping("/filter")
    public List<FlightForFilterDto> search(@RequestBody SearchFlightRequest filterFlightRequest) throws Exception {
        return flightService.search(filterFlightRequest);
    }
    @Operation(description = "Create a flight by provided information.")
    @SecurityRequirement(name="bearerAuth")
    @PostMapping
    public void create(@RequestBody CreateFlightRequest createFlightRequest) {
        flightService.create(createFlightRequest);
    }

    @Operation(description = "Save flights(mock data) for specific time.")
    @PostMapping("/all")
    public void saveAll(@RequestBody List<CreateFlightRequest> createFlightRequests) {
        flightService.saveAll(createFlightRequests);
    }

    @Operation(description = "Update a flight by provided information.")
    @SecurityRequirement(name="bearerAuth")
    @PutMapping("/{id}")
    public void update(@PathVariable Long id,@RequestBody UpdateFlightRequest updateFlightRequest) throws Exception {
        flightService.update(id,updateFlightRequest);
    }

    @Operation(description = "Delete a flight by id.")
    @SecurityRequirement(name="bearerAuth")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws Exception {
        flightService.delete(id);
    }
}
