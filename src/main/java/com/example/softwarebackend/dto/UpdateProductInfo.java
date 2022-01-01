package com.example.softwarebackend.dto;

import lombok.Data;

@Data
public class UpdateProductInfo {

    private String productName;
    private String productDetail;
    private String productImageUrl;
    private int productPrice;

}
