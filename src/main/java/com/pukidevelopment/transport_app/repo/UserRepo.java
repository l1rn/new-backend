package com.pukidevelopment.transport_app.repo;

import com.pukidevelopment.transport_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findById(int id);
    boolean existsByPhoneNumber(String phone_number);
}
