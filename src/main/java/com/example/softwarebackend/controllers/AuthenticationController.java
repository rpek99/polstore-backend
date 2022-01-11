package com.example.softwarebackend.controllers;

import com.example.softwarebackend.dto.AuthResponse;
import com.example.softwarebackend.dto.UserRequest;
import com.example.softwarebackend.models.User;
import com.example.softwarebackend.repositories.UserRepository;
import com.example.softwarebackend.services.UserService;
import com.example.softwarebackend.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    //login request
    @PostMapping(path = "/login")
    public ResponseEntity login(@RequestBody UserRequest userRequest) {

        User user = userRepository.findByEmail(userRequest.getEmail());

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if(user != null) {
            if(bCryptPasswordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword());
                Authentication auth = authenticationManager.authenticate(authToken);
                SecurityContextHolder.getContext().setAuthentication(auth);
                String jwtToken = jwtTokenProvider.generateJwtToken(auth);
                AuthResponse authResponse = new AuthResponse();
                authResponse.setMessage("Bearer " +jwtToken);
                authResponse.setUserId(user.getId());
                return ResponseEntity.ok().body(authResponse);
            }
            return ResponseEntity.badRequest().body("Password is wrong");
        }
        return ResponseEntity.badRequest().body("There is no any record with this email address");
    }

    //register request
    @PostMapping(path = "/register")
    public ResponseEntity register(@RequestPart User user) {
        User registeredUser = userRepository.findByEmail(user.getEmail());
        if(registeredUser != null) {
            return ResponseEntity.badRequest().body("Email already in use");
        }
        userService.createUser(user);
        return ResponseEntity.ok().body("User successfully registered");
    }

}
