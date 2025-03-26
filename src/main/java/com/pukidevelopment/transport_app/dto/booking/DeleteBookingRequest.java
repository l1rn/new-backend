package com.pukidevelopment.transport_app.dto.booking;

import lombok.Data;

@Data
public class DeleteBookingRequest {
    private Integer bookingId;
    private String telegramId;
}
