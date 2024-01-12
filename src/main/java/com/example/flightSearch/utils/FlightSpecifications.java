package com.example.flightSearch.utils;


import com.example.flightSearch.entity.Flight;
import com.example.flightSearch.modal.request.SearchFlightRequest;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class FlightSpecifications {

    public static Specification<Flight> getFilterQuery(SearchFlightRequest filterFlightRequest) {

        return (root, query, cb) -> {
            Predicate conjunction = cb.conjunction();

            if (StringUtils.isNotBlank(filterFlightRequest.getDepartureAirport())) {
                conjunction = cb.and(conjunction,cb.equal(root.get("departureAirport").get("city"),filterFlightRequest.getDepartureAirport()));
            }
            if (StringUtils.isNotBlank(filterFlightRequest.getArrivalAirport())) {
                conjunction = cb.and(conjunction,cb.equal(root.get("arrivalAirport").get("city"),filterFlightRequest.getArrivalAirport()));
            }
            if (filterFlightRequest.getDepartureDate() != null) {
                conjunction = cb.and(conjunction,cb.equal(root.get("departureDate"),filterFlightRequest.getDepartureDate()));
            }
            if (filterFlightRequest.getReturnDate() != null) {
                conjunction = cb.and(conjunction,cb.equal(root.get("returnDate"),filterFlightRequest.getReturnDate()));
            }
            if (filterFlightRequest.getPrice() > 0) {
                conjunction = cb.and(conjunction,cb.equal(root.get("price"),filterFlightRequest.getPrice()));
            }

            return conjunction;
        };
    }

}
