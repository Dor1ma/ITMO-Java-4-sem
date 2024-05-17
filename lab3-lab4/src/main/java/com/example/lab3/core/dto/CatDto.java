package com.example.lab3.core.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
public class CatDto {
    private Long id;
    private String name;
    private Date dateOfBirth;
    private String breed;
    private String color;
    private Long ownerId;
    private List<Long> listOfFriendsIds;
}
