package com.pukidevelopment.transport_app.repo;

import com.pukidevelopment.transport_app.model.Route;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface RoutesRepo extends MongoRepository<Route, String> {
    boolean existsByRouteFromAndRouteToAndArrivalTime(String routeFrom, String routeTo, LocalDateTime arrivalDate);
}
