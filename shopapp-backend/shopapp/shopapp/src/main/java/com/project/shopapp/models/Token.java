package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    @Id //Khai bao khoa chinh
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", length = 255)
    private String token;

    @Column(name = "token_type", length = 50)
    private String tokenType;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;   //Chi ro ngay gio nao token het thoi han

    private boolean revoked;    // Trang thai token da bi huy chua
    private  boolean expired;   //Trang thai token da het han chua

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
