package com.example.softwarebackend.controllers;

import com.example.softwarebackend.dto.UserInformation;
import com.example.softwarebackend.models.User;
import com.example.softwarebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    @PostMapping(path = "/authentication")
    public ResponseEntity userAuthentication(@RequestBody UserInformation userInformation) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findByEmail(userInformation.getEmail());

        if (user != null) {
            if (bCryptPasswordEncoder.matches(userInformation.getPassword(), user.getPassword())) {
                return ResponseEntity.ok().body("Login successful");
            }
            return ResponseEntity.badRequest().body("Password is wrong");
        }
        return ResponseEntity.badRequest().body("There is no any record with this email address");

    }
}
