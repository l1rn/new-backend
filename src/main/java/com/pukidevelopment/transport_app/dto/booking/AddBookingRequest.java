package com.pukidevelopment.transport_app.dto.booking;

import com.pukidevelopment.transport_app.model.Route;
import lombok.Data;

@Data
public class AddBookingRequest {
    private String routeId;
}
