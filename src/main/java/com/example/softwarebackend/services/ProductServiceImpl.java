package com.example.softwarebackend.services;

import com.example.softwarebackend.dto.UpdateProductInfo;
import com.example.softwarebackend.models.Product;
import com.example.softwarebackend.models.User;
import com.example.softwarebackend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    public void createNewProduct(Product product, User user) {
        Product newProduct = new Product();

        newProduct.setProductName(product.getProductName());
        newProduct.setProductPrice(product.getProductPrice());
        newProduct.setProductDetail(product.getProductDetail());
        newProduct.setProductImageUrl(product.getProductImageUrl());
        newProduct.setUser(user);
//        newProduct.setSold(false);

        productRepository.save(newProduct);
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    public void updateProduct(UpdateProductInfo updateProductInfo, Product product) {

        product.setProductName(updateProductInfo.getProductName());
        product.setProductDetail(updateProductInfo.getProductDetail());
        product.setProductPrice(updateProductInfo.getProductPrice());
        product.setProductImageUrl(updateProductInfo.getProductImageUrl());

        productRepository.save(product);
    }

}
