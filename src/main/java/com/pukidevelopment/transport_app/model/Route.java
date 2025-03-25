package com.pukidevelopment.transport_app.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private int id;

    private String routeFrom;

    private String routeTo;

    private String transport;

    private LocalDateTime arrivalTime;

    private LocalDateTime departureTime;

    private int availableSeats;

    private double price;

    private static boolean isValid(LocalDateTime arrival_time){
        LocalDateTime current_time = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
        return arrival_time.isAfter(current_time);
    }
}
