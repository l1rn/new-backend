package com.pukidevelopment.transport_app.repo;

import com.pukidevelopment.transport_app.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepo extends MongoRepository<Booking, String> {
    boolean findStatusById(String id);
}
