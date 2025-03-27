package com.pukidevelopment.transport_app.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;

    private Long chatId;

    @Column(name = "telegram_id")
    private String telegramId;

    @Column(name = "phone_number")
    private String phoneNumber;
}
