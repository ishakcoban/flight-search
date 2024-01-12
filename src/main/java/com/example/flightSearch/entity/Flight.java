package com.example.flightSearch.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "departureAirport_id")
    private Airport departureAirport;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "arrivalAirport_id")
    private Airport arrivalAirport;

    @Column(nullable = false)
    private LocalDateTime departureDate;

    @Column(nullable = true)
    private LocalDateTime returnDate;

    @Column(nullable = false)
    private double price;

}
