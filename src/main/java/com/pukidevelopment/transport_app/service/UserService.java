package com.pukidevelopment.transport_app.service;

import com.pukidevelopment.transport_app.model.User;
import com.pukidevelopment.transport_app.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepo;

    public boolean existsByTelegramId(String telegram_id){
        return userRepo.findByTelegramId(telegram_id).isPresent();
    }


    public User addUser(String telegram_id){
        User user = userRepo.findByTelegramId(telegram_id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return userRepo.save(user);
    }

    @Transactional
    public void deleteUser(String telegram_id){
        User user = userRepo.findByTelegramId(telegram_id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        userRepo.delete(user);
    }

}
