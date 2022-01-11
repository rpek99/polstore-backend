package com.example.softwarebackend.controllers;

import com.example.softwarebackend.dto.ProductToCart;
import com.example.softwarebackend.models.Product;
import com.example.softwarebackend.models.User;
import com.example.softwarebackend.models.UserCart;
import com.example.softwarebackend.repositories.ProductRepository;
import com.example.softwarebackend.repositories.UserCartRepository;
import com.example.softwarebackend.repositories.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    UserCartRepository userCartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;


    //gets all products for specific user id
    @GetMapping("/getCartProducts")
    public Set<Product> getCartProducts(@RequestParam Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()){
            UserCart userCart = userCartRepository.findUserCartByUserId(userId);
            return userCart.getProducts();
        }
        return null;
    }

    //adding to cart a product
    @PostMapping("/addToCart")
    public ResponseEntity addToCart(@RequestBody ProductToCart productToCart) {
        Optional<User> user = userRepository.findById(productToCart.getUserId());
        Optional<Product> product = productRepository.findById(productToCart.getProductId());

        if (user.isPresent()) {
            if(product.isPresent()) {
                UserCart userCart = userCartRepository.findUserCartByUserId(productToCart.getUserId());
                if (userCart == null) {
                    userCart = new UserCart();
                    userCart.setUser(user.get());
                }
                userCart.getProducts().add(product.get());
                userCartRepository.save(userCart);
            }
        }
        return ResponseEntity.ok().build();
    }

    //remove product from user cart
    @PostMapping("/removeFromCart")
    public ResponseEntity removeFromCart (@RequestBody ProductToCart productToCart) {
        Optional<User> user = userRepository.findById(productToCart.getUserId());
        Optional<Product> product = productRepository.findById(productToCart.getProductId());

        if (user.isPresent()) {
            if(product.isPresent()) {
                UserCart userCart = userCartRepository.findUserCartByUserId(productToCart.getUserId());
                userCart.getProducts().remove(product.get());
                userCartRepository.save(userCart);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
