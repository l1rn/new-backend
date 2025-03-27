package com.pukidevelopment.transport_app.service;

import com.pukidevelopment.transport_app.model.Route;
import com.pukidevelopment.transport_app.repo.RoutesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutesService {
    @Autowired
    private RoutesRepo routesRepo;

    public List<Route> getAllRoutes(){
        return routesRepo.findAll();
    }

    public Route findById(String routeId){
        return routesRepo.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Маршрут не найден"));
    }
}
