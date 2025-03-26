package com.pukidevelopment.transport_app.dto.booking;

import com.pukidevelopment.transport_app.enums.BookingStatus;
import com.pukidevelopment.transport_app.model.Route;
import com.pukidevelopment.transport_app.model.User;
import lombok.Data;

@Data
public class AddBookingRequest {
    private User userId;
    private Route routeId;
    private BookingStatus status;
}
