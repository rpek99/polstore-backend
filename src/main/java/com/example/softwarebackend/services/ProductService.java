package com.example.softwarebackend.services;

import com.example.softwarebackend.dto.UpdateProductInfo;
import com.example.softwarebackend.models.Product;
import com.example.softwarebackend.models.User;

public interface ProductService {

    void createNewProduct(Product product, User user);

    void deleteProduct(Product product);

    void updateProduct(UpdateProductInfo updateProductInfo, Product product);
}
