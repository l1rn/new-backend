package com.pukidevelopment.transport_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Routes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String route_from;

    private String route_to;

    private String transport;

    private LocalDateTime arrival_time;

    private LocalDateTime departure_time;

    private int available_seats;

    private double price;

    private static boolean isValid(LocalDateTime arrival_time){
        LocalDateTime current_time = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
        return arrival_time.isAfter(current_time);
    }
}
