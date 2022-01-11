package com.example.softwarebackend.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "user_mail")
public class UserMail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "buyer_name")
    private String buyerName;

    @Column(name = "buyer_phone_number")
    private String buyerPhoneNumber;

    @Column(name = "buyer_email")
    private String buyerEmail;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "date")
    private LocalDateTime date;

}
