package com.example.flightSearch.repository;

import com.example.flightSearch.entity.Flight;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long>, JpaSpecificationExecutor<Flight> {
    List<Flight> findAll(Specification<Flight> spec);
}
