package com.example.flightSearch.controller;

import com.example.flightSearch.modal.dto.FlightDto;
import com.example.flightSearch.modal.dto.FlightForFilterDto;
import com.example.flightSearch.modal.request.CreateFlightRequest;
import com.example.flightSearch.modal.request.SearchFlightRequest;
import com.example.flightSearch.modal.request.UpdateFlightRequest;
import com.example.flightSearch.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping("/{id}")
    public FlightDto getOne(@PathVariable Long id) {
        return flightService.getOne(id);
    }
    @GetMapping
    public List<FlightDto> getAll() {
        return flightService.getAll();
    }

    @PostMapping("/filter")
    public List<FlightForFilterDto> search(@RequestBody SearchFlightRequest filterFlightRequest) throws Exception {
        return flightService.search(filterFlightRequest);
    }

    @PostMapping
    public void create(@RequestBody CreateFlightRequest createFlightRequest) {
        flightService.create(createFlightRequest);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,@RequestBody UpdateFlightRequest updateFlightRequest) throws Exception {
        flightService.update(id,updateFlightRequest);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws Exception {
        flightService.delete(id);
    }
}
