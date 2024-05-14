package com.example.lab3.core.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String password;
    private String role;
}
