package com.example.softwarebackend.controllers;

import com.example.softwarebackend.dto.UpdateProductInfo;
import com.example.softwarebackend.models.Product;
import com.example.softwarebackend.models.User;
import com.example.softwarebackend.repositories.ProductRepository;
import com.example.softwarebackend.repositories.UserRepository;
import com.example.softwarebackend.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductControllers {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductService productService;

    @PostMapping(value = "/addProduct")
    public ResponseEntity addProduct(@RequestPart Product product, @RequestParam Long userId) {

        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            try {
                productService.createNewProduct(product, user.get());
            } catch (Exception e) {
                System.out.println("" +e);
                return ResponseEntity.badRequest().body(e);
            }
            logger.info("Product Created");
            return ResponseEntity.ok().body("Product Created");
        }
        return ResponseEntity.badRequest().body("User not found");
    }

    @PostMapping(value = "/deleteProduct")
    public ResponseEntity deleteProduct(@RequestParam Long productId) {

        Optional<Product> product = productRepository.findById(productId);

        if(product.isPresent()) {
            try {
                productService.deleteProduct(product.get());
                return ResponseEntity.ok("Product deleted successfully");
            } catch (Exception e) {
                System.out.println("" + e);
                return ResponseEntity.badRequest().body(e);
            }
        } else {
            return ResponseEntity.badRequest().body("Product doesn't exist");
        }
    }

    @PostMapping(value = "/updateProduct")
    public ResponseEntity updateProduct(@RequestPart UpdateProductInfo updateProductInfo, @RequestParam Long productId) {

        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            try{
                productService.updateProduct(updateProductInfo, product.get());
                return ResponseEntity.ok("Product updated successfully");
            } catch (Exception e) {
                System.out.println("" +e);
                return ResponseEntity.badRequest().body(e);
            }
        } else {
            return ResponseEntity.badRequest().body("Product doesn't exist");
        }

    }
}
