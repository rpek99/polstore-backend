package com.example.softwarebackend.controllers;

import com.example.softwarebackend.dto.UpdateUserInformation;
import com.example.softwarebackend.models.User;
import com.example.softwarebackend.repositories.UserRepository;
import com.example.softwarebackend.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "/register")
    public ResponseEntity registerUser(@RequestPart User user) {

        String email = user.getEmail();
        User userMail = userRepository.findByEmail(email);

        if (userMail != null) {
            logger.error("Email already exist");
            return ResponseEntity.badRequest().body("Email already exist");
        }

        try {
            userService.createUser(user);
        } catch (Exception e) {
            System.out.println("" +e);
            return ResponseEntity.badRequest().body(e);
        }

        logger.info("User registered successfully...");
        return ResponseEntity.ok().body("Complete");
    }

    @PostMapping(value = "/updateUser")
    public ResponseEntity updateUser(@RequestBody UpdateUserInformation updateUserInformation) {

        Optional<User> user = userRepository.findById(updateUserInformation.getId());
        if (user.isPresent()) {
            try {
                userService.updateUser(updateUserInformation, user.get());
                logger.info("User Updated");
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                System.out.println("" +e);
                return ResponseEntity.badRequest().body(e);
            }
        }
        return ResponseEntity.badRequest().body("User not found");
    }
}
