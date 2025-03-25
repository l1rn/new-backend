package com.pukidevelopment.transport_app.repo;

import com.pukidevelopment.transport_app.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface RoutesRepo extends JpaRepository<Route, Integer> {

    Route findById(int id);
    boolean existsByRouteFromAndRouteToAndArrivalTime(String routeFrom, String routeTo, LocalDateTime arrivalDate);
}
