package com.pukidevelopment.transport_app.repo;

import com.pukidevelopment.transport_app.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findByTelegramId(String telegram_id);
    boolean existsByPhoneNumber(String phone_number);
}
