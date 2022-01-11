package com.example.softwarebackend.dto;

import lombok.Data;

@Data
public class ProductToCart {

    private Long userId;
    private Long productId;
}
