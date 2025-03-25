package com.pukidevelopment.transport_app.controlles;

import com.pukidevelopment.transport_app.model.Route;
import com.pukidevelopment.transport_app.service.RoutesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {
    @Autowired
    private RoutesService routesService;

    @GetMapping("/all")
    public ResponseEntity<List<Route>> getAllRoutes(){
        List<Route> routes = routesService.getAllRoutes();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }
}
