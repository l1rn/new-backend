package com.pukidevelopment.transport_app.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    private Long chatId;

    @Column(name = "telegram_id")
    private String telegramId;

    @Column(name = "phone_number")
    private String phoneNumber;
}
