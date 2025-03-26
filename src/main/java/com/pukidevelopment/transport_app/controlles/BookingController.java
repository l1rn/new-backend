package com.pukidevelopment.transport_app.controlles;

import com.pukidevelopment.transport_app.dto.booking.AddBookingRequest;
import com.pukidevelopment.transport_app.dto.booking.DeleteBookingRequest;
import com.pukidevelopment.transport_app.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/add")
    public ResponseEntity<?> createBooking(@RequestBody AddBookingRequest request){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.addBooking(request));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Вы не авторизованы или у вас нет прав");
        }
    }

    @PatchMapping("/cancel")
    public ResponseEntity<?> cancelBooking(@RequestBody DeleteBookingRequest request) throws AccessDeniedException {
        if(bookingService.cancelBooking(request)){
            return ResponseEntity.ok("Бронирование отменено!");
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Вы не авторизованы или у вас нет прав");
        }
    }
}
