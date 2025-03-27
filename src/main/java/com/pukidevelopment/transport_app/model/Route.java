package com.pukidevelopment.transport_app.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Document(collation = "routes")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    @Id
    private String id;

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
