package com.example.softwarebackend.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //user_id

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_detail")
    private String productDetail;

    @Column(name = "product_image_url")
    private String productImageUrl;

    @Column(name = "product_price")
    private int productPrice;

    @ManyToMany(mappedBy = "products")
    private Set<UserCart> carts = new HashSet<>();

}
