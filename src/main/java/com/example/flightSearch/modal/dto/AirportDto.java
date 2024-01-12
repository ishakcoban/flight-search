package com.example.flightSearch.modal.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirportDto {
    private long id;
    private String city;
}
