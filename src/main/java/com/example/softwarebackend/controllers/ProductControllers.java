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

import java.util.List;
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

    //adding product to general system
    @PostMapping(value = "/addProduct")
    public ResponseEntity addProduct(@RequestBody Product product) {

        Optional<User> user = userRepository.findById(product.getUser().getId());

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

    //delete product from general system
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

    //gets all products that user created
    @GetMapping(value = "/getUserProduct")
    public List<Product> getUserProducts(@RequestParam Long userId) {
        List<Product> products = productRepository.findAllByUserId(userId);
        if (products != null){
            return products;
        } else {
            return null;
        }
    }

    //gets all products in the system
    @GetMapping(value = "/getAllProducts")
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

}
