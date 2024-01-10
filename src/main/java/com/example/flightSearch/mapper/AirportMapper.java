package com.example.flightSearch.mapper;

import com.example.flightSearch.entity.Airport;
import com.example.flightSearch.modal.dto.AirportDto;
import com.example.flightSearch.modal.request.CreateAirportRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AirportMapper {
    public AirportDto toDto(Airport airport){

        return AirportDto.builder()
                .id(airport.getId())
                .city(airport.getCity())
                .build();
    }

    public Airport create(CreateAirportRequest createAirportRequest) {

        return Airport.builder()
                .city(createAirportRequest.getCity())
                .build();
    }


    public Airport update(Long id,String city) {

        return Airport.builder()
                .id(id)
                .city(city)
                .build();
    }
    public List<AirportDto> toDtoList(List<Airport> adverts) {
        return adverts.stream().map(this::toDto).collect(Collectors.toList());
    }
}
