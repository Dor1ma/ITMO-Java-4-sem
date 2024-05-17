package com.example.lab3.core.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    private String password;
}
