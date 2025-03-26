package com.pukidevelopment.transport_app.repo;

import com.pukidevelopment.transport_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByTelegramId(String telegram_id);
    User getUserByTelegramId(String telegram_id);
    boolean existsByPhoneNumber(String phone_number);
}
