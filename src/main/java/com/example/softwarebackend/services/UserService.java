package com.example.softwarebackend.services;

import com.example.softwarebackend.dto.UpdateUserInformation;
import com.example.softwarebackend.models.User;

public interface UserService {

    void updateUser(UpdateUserInformation updateUserInformation, User user);

    void createUser(User user);
}
