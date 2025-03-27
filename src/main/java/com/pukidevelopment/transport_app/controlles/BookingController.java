package com.pukidevelopment.transport_app.controlles;

import com.pukidevelopment.transport_app.dto.booking.AddBookingRequest;
import com.pukidevelopment.transport_app.dto.booking.ConfirmBookingRequest;
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

    @PostMapping("/add/{id}")
    public ResponseEntity<?> createBooking(@PathVariable(name = "id") int id,
                                           @RequestBody AddBookingRequest request)
    {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.addBooking(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Вы не авторизованы или у вас нет прав");
        }
    }

    @PatchMapping("/cancel/{id}")
    public ResponseEntity<?> cancelBooking(
            @PathVariable(name = "id") int id,
            @RequestBody DeleteBookingRequest request)
            throws AccessDeniedException
    {
        if(bookingService.cancelBooking(id, request)){
            bookingService.cancelBooking(id ,request);
            return ResponseEntity.ok("Бронирование отменено!");
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Вы не авторизованы или у вас нет прав");
        }
    }

    @PatchMapping("/confirm/{id}")
    public ResponseEntity<?> confirmBooking(@PathVariable(name = "id") int id,
                                            @RequestBody ConfirmBookingRequest request)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bookingService.confirmBooking(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("У вас нет прав на выполнение этого запрос");
        }
    }
}
