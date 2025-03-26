package com.pukidevelopment.transport_app.service;

import com.pukidevelopment.transport_app.dto.booking.AddBookingRequest;
import com.pukidevelopment.transport_app.dto.booking.DeleteBookingRequest;
import com.pukidevelopment.transport_app.enums.BookingStatus;
import com.pukidevelopment.transport_app.model.Booking;
import com.pukidevelopment.transport_app.model.Route;
import com.pukidevelopment.transport_app.model.User;
import com.pukidevelopment.transport_app.repo.BookingRepo;
import com.pukidevelopment.transport_app.repo.RoutesRepo;
import com.pukidevelopment.transport_app.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final RoutesRepo routesRepo;
    private final BookingRepo bookingRepo;
    private final UserRepo userRepo;
    private final UserService userService;
    public Booking save(Booking booking){
        return bookingRepo.save(booking);
    }

    @Transactional
    public Booking addBooking(AddBookingRequest request){
        if(request.getStatus().toString().equals("BOOKING_CANCELED")){
            throw new RuntimeException("Бронирование уже отменено!");
        }

        Route route = request.getRouteId();
        if(route != null){
            route.setAvailableSeats(route.getAvailableSeats() - 1);
            routesRepo.save(route);
        }

        Booking booking = Booking.builder()
                .user(request.getUserId())
                .route(route)
                .status(request.getStatus())
                .build();

        return save(booking);
    }

    @Transactional
    public boolean cancelBooking(DeleteBookingRequest request) throws AccessDeniedException {

        Booking currentBooking = bookingRepo.findById(request.getBookingId())
                .orElseThrow(() -> new RuntimeException("Маршрут не найден"));
        if(currentBooking.getStatus() == BookingStatus.BOOKING_CANCELED){
            return false;
        }
        User user = userRepo.getUserByTelegramId(request.getTelegramId());

        if (currentBooking.getUser().getId() != user.getId()) {
            throw new AccessDeniedException("У вас нет прав для отмены бронирования");
        }

        Route route = currentBooking.getRoute();
        if(route != null){
            route.setAvailableSeats(route.getAvailableSeats() + 1);
            routesRepo.save(route);
        }

        currentBooking.setStatus(BookingStatus.BOOKING_CANCELED);
        currentBooking.setUser(user);
        currentBooking.setRoute(route);

        return true;
    }
}
