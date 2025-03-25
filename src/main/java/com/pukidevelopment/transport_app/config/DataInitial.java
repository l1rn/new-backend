package com.pukidevelopment.transport_app.config;

import com.pukidevelopment.transport_app.model.Route;
import com.pukidevelopment.transport_app.model.User;
import com.pukidevelopment.transport_app.repo.BookingRepo;
import com.pukidevelopment.transport_app.repo.RoutesRepo;
import com.pukidevelopment.transport_app.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class DataInitial {
    private final UserRepo userRepo;
    private final BookingRepo bookingRepo;
    private final RoutesRepo routesRepo;
    public void createUserIfNotExists(String phone_number){
        if(!userRepo.existsByPhoneNumber(phone_number)){
            User user = User.builder()
                    .phoneNumber(phone_number)
                    .build();
            userRepo.save(user);
        }
    }
    public void createRoutesIfNotExists(String routeFrom, String routeTo,
                                        LocalDateTime arrivalTime, LocalDateTime departureTime,
                                        String transport,
                                        int availableSeats, double price){
        if(!routesRepo.existsByRouteFromAndRouteToAndArrivalTime(routeFrom, routeTo, arrivalTime)){
            Route route = Route.builder()
                    .routeFrom(routeFrom)
                    .routeTo(routeTo)
                    .arrivalTime(arrivalTime)
                    .departureTime(departureTime)
                    .transport(transport)
                    .availableSeats(availableSeats)
                    .price(price)
                    .build();
            routesRepo.save(route);
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initFirstRoutes(){
        createRoutesIfNotExists(
                "Новосибирск",
                "Красноярск",
                LocalDateTime.of(2026, 8, 1, 7, 45, 0),
                LocalDateTime.of(2026, 8, 1, 10, 15, 0),
                "Авиа",
                40,
                4200
        );
        createRoutesIfNotExists(
                "Воронеж",
                "Екатеринбург",
                LocalDateTime.of(2025, 4, 30, 8, 30, 0),
                LocalDateTime.of(2025, 4, 30, 11, 30, 0),
                "Авиа",
                89,
                4350
        );
        createRoutesIfNotExists(
                "Липецк",
                "Москва",
                LocalDateTime.of(2025, 5, 2, 6, 30, 0),
                LocalDateTime.of(2025, 5, 2, 10, 30, 0),
                "Липецк",
                50,
                2000
        );
    }
    @EventListener(ApplicationReadyEvent.class)
    public void initFirstUsers(){
        createUserIfNotExists("7999111222");
        createUserIfNotExists("4444444444");
    }
}
