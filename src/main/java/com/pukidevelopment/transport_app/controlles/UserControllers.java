package com.pukidevelopment.transport_app.controlles;

import com.pukidevelopment.transport_app.model.User;
import com.pukidevelopment.transport_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserControllers {
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(String telegram_id){
        try{
            if(userService.existsByTelegramId(telegram_id)){
                throw new RuntimeException("Пользователь уже зарегистрирован");
            }
            return ResponseEntity.ok(userService.addUser(telegram_id));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(String telegram_id){
        try {
            if(userService.existsByTelegramId(telegram_id)){
                userService.deleteUser(telegram_id);
                return ResponseEntity.ok(HttpStatus.NO_CONTENT);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Такого аккаунта не существует");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
