package com.pukidevelopment.transport_app.repo;

import com.pukidevelopment.transport_app.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {
    Booking findById(int id);
}
