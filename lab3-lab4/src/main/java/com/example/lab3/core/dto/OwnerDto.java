package com.example.lab3.core.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OwnerDto {
    private Long id;
    private Date dateOfBirth;
    private List<Long> catsIds;
    private String username;
    private String password;
    private String role;
}
