package com.example.softwarebackend.controllers;

import com.example.softwarebackend.dto.RequestMessage;
import com.example.softwarebackend.models.Product;
import com.example.softwarebackend.models.User;
import com.example.softwarebackend.models.UserMail;
import com.example.softwarebackend.repositories.ProductRepository;
import com.example.softwarebackend.repositories.UserMailRepository;
import com.example.softwarebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/request")
public class RequestController {

    @Autowired
    UserMailRepository userMailRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/getUserMessages")
    public List<UserMail> getUserMessages(@RequestParam Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            return userMailRepository.findAllByUserId(userId);
        } else {
            return null;
        }
    }

    @PostMapping("/createMessage")
    public ResponseEntity createMessage(@RequestBody RequestMessage requestMessage) {
        Optional<User> buyer = userRepository.findById(requestMessage.getUserId());
        Optional<Product> product = productRepository.findById(requestMessage.getProductId());

        Optional<User> owner = userRepository.findById(product.get().getUser().getId());

        if(buyer.isPresent()) {
            if(owner.isPresent()){
                UserMail userMail = new UserMail();
                userMail.setUser(owner.get());
                userMail.setProductName(product.get().getProductName());
                userMail.setBuyerEmail(buyer.get().getEmail());
                userMail.setBuyerPhoneNumber(buyer.get().getMobilePhone());
                userMail.setBuyerName(buyer.get().getFirstName()+" "+buyer.get().getLastName());
                userMail.setDate(LocalDateTime.now());
                userMailRepository.save(userMail);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }




}
