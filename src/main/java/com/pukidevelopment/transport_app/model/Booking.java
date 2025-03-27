package com.pukidevelopment.transport_app.model;

import com.pukidevelopment.transport_app.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "booking")
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    private String id;

    @DBRef
    private Route route;

    @DBRef
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status")
    private BookingStatus status;
}
