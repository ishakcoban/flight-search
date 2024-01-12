package com.example.flightSearch.modal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightForFilterDto {
    private long id;
    private DepartureDto departureFlight;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ReturnDto returnFlight;

}
