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

    @GetMapping(value = "/getUser")
    public UpdateUserInformation getUser(@RequestParam Long userId){

        UpdateUserInformation userInformation = new UpdateUserInformation();

        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()){
            userInformation.setId(user.get().getId());
            userInformation.setEmail(user.get().getEmail());
            userInformation.setFirstName(user.get().getFirstName());
            userInformation.setLastName(user.get().getLastName());
            userInformation.setMobilePhone(user.get().getMobilePhone());
            userInformation.setMobilePrefix(user.get().getMobilePrefix());

           return userInformation;
        }
        return null;
    }
}
