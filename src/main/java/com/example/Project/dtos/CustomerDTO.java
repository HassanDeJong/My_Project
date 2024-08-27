package com.example.Project.dtos;

import lombok.Data;

@Data
public class CustomerDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String nationalIdNumber;
}
