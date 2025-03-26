package com.pukidevelopment.transport_app.dto.users;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterRequest {
    private Long chatId;
    private String telegramId;
    private String phoneNumber;
}
