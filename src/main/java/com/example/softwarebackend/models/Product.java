package com.example.softwarebackend.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
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

    @Column(name = "is_sold", columnDefinition = "boolean default false")
    private boolean isSold = false;

}
