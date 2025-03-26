package com.pukidevelopment.transport_app.service;

import com.pukidevelopment.transport_app.dto.users.DeleteUserRequest;
import com.pukidevelopment.transport_app.dto.users.RegisterRequest;
import com.pukidevelopment.transport_app.model.User;
import com.pukidevelopment.transport_app.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepo;

    public User save(User user){
        return userRepo.save(user);
    }

    public boolean existsByTelegramId(String telegramId) {
        return userRepo.findByTelegramId(telegramId).isPresent();
    }

    @Transactional
    public User addUser(RegisterRequest request){
        if(userRepo.existsByPhoneNumber(request.getPhoneNumber())){
            throw new RuntimeException("Пользователь с таким номер телефона уже есть");
        }

        String telegramId = request.getTelegramId();
        System.out.println(telegramId);

        User user = User.builder()
                .telegramId(request.getTelegramId())
                .chatId(request.getChatId())
                .phoneNumber(request.getPhoneNumber())
                .build();

        return save(user);
    }

    @Transactional
    public void deleteUser(DeleteUserRequest request){
        User user = userRepo.findByTelegramId(request.getTelegramId())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        userRepo.delete(user);
    }

}
