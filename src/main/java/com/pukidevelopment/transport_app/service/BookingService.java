package com.pukidevelopment.transport_app.service;

import com.pukidevelopment.transport_app.dto.booking.AddBookingRequest;
import com.pukidevelopment.transport_app.dto.booking.ConfirmBookingRequest;
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
    public Booking addBooking(int userId, AddBookingRequest request){
        Route route = routesRepo.findById(request.getRouteId());
        if(route != null){
            route.setAvailableSeats(route.getAvailableSeats() - 1);
            routesRepo.save(route);
        }

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Booking booking = Booking.builder()
                .user(user)
                .route(route)
                .status(BookingStatus.PENDING)
                .build();

        return save(booking);
    }

    @Transactional
    public boolean cancelBooking(int bookingId, DeleteBookingRequest request) throws AccessDeniedException {

        Booking currentBooking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Маршрут не найден"));

        if(currentBooking.getStatus() == BookingStatus.CANCELED){
            return false;
        }
        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        if (currentBooking.getUser().getId() != request.getUserId()) {
            throw new AccessDeniedException("У вас нет прав для отмены бронирования");
        }

        Route route = currentBooking.getRoute();
        if(route != null){
            route.setAvailableSeats(route.getAvailableSeats() + 1);
            routesRepo.save(route);
        }

        currentBooking.setStatus(BookingStatus.CANCELED);
        currentBooking.setUser(user);
        currentBooking.setRoute(route);

        return true;
    }

    @Transactional
    public Booking confirmBooking(int bookingId, ConfirmBookingRequest request){

        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Бронирование не найдено"));

        User user = userRepo.findById(request.getUserId())
                        .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        booking.setUser(user);
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setRoute(booking.getRoute());

        return save(booking);
    }
}
