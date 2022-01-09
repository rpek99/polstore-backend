package com.example.softwarebackend.services;


import com.example.softwarebackend.dto.UpdateUserInformation;
import com.example.softwarebackend.models.User;
import com.example.softwarebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public void updateUser(UpdateUserInformation updateUserInformation, User user) {
        user.setEmail(updateUserInformation.getEmail());
        user.setFirstName(updateUserInformation.getFirstName());
        user.setLastName(updateUserInformation.getLastName());
        user.setMobilePrefix(updateUserInformation.getMobilePrefix());
        user.setMobilePhone(updateUserInformation.getMobilePhone());

        userRepository.save(user);
    }

    public void createUser(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setMobilePhone(user.getMobilePhone());
        newUser.setEmail(user.getEmail());
        newUser.setMobilePrefix(user.getMobilePrefix());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(newUser);
    }
}
