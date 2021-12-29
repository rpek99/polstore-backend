package com.example.softwarebackend.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "user_cart")
public class UserCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //user_id

//    @ManyToMany
//    @JoinTable(name = "products",
//            joinColumns = @JoinColumn(name = "cart_id"))
//    private List<Product> products;

}
