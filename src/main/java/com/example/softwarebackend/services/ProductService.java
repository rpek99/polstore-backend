package com.example.softwarebackend.services;

import com.example.softwarebackend.models.Product;
import com.example.softwarebackend.models.User;

public interface ProductService {

    void createNewProduct(Product product, User user);
}
