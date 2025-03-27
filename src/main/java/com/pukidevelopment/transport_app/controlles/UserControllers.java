package com.pukidevelopment.transport_app.controlles;

import com.pukidevelopment.transport_app.dto.users.DeleteUserRequest;
import com.pukidevelopment.transport_app.dto.users.RegisterRequest;
import com.pukidevelopment.transport_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserControllers {
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> signUp(@RequestBody RegisterRequest request){
        try{

            return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(request));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody DeleteUserRequest request){
        try {
            if(userService.existsByTelegramId(request.getTelegramId())){
                userService.deleteUser(request);
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
