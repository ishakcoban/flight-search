package com.example.flightSearch.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "departureAirport_id", referencedColumnName = "id")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrivalAirport_id", referencedColumnName = "id")
    private Airport arrivalAirport;

    @Column(nullable = false)
    private LocalDateTime departureDate;

    @Column(nullable = true)
    private LocalDateTime returnDate;

    @Column(nullable = false)
    private double price;

}
