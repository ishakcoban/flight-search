package com.example.flightSearch.utils;


import com.example.flightSearch.entity.Flight;
import com.example.flightSearch.modal.request.FilterFlightRequest;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class FlightSpecifications {

    public static Specification<Flight> getFilterQuery(FilterFlightRequest filterFlightRequest) {

        return (root, query, cb) -> {
            Predicate conjunction = cb.conjunction();

            if (StringUtils.isNotBlank(filterFlightRequest.getDepartureAirport())) {
                conjunction = cb.and(conjunction,cb.equal(root.get("departureAirport"),filterFlightRequest.getDepartureAirport()));
            }
            if (StringUtils.isNotBlank(filterFlightRequest.getArrivalAirport())) {
                conjunction = cb.and(conjunction,cb.equal(root.get("arrivalAirport"),filterFlightRequest.getArrivalAirport()));
            }
            if (StringUtils.isNotBlank(filterFlightRequest.getDepartureDate().toString())) {
                conjunction = cb.and(conjunction,cb.equal(root.get("departureDate"),filterFlightRequest.getDepartureAirport()));
            }
            if (StringUtils.isNotBlank(filterFlightRequest.getDepartureDate().toString())) {
                conjunction = cb.and(conjunction,cb.equal(root.get("returnDate"),filterFlightRequest.getReturnDate()));
            }
            if (filterFlightRequest.getPrice() > 0) {
                conjunction = cb.and(conjunction,cb.equal(root.get("price"),filterFlightRequest.getPrice()));
            }


            return conjunction;
        };
    }

}
