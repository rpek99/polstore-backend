package com.example.softwarebackend.dto;

import lombok.Data;

@Data
public class UpdateUserInformation {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String mobilePhone;
    private Integer mobilePrefix;
}
