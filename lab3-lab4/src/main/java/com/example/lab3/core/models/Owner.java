package com.example.lab3.core.models;

import lombok.*;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "owners")
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @OneToMany(mappedBy = "owner")
    private List<Cat> cats;

    public Owner(Date dateOfBirth, List<Cat> cats,
                 String username, String password, String role) {
        this.dateOfBirth = dateOfBirth;
        this.cats = cats;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
