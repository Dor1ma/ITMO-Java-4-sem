package org.core.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.Getter;

@Entity
@Table(name = "cats")
@Getter
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "breed")
    private String breed;

    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToMany
    @JoinTable(
            name = "cat_friends",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private List<Cat> listOfFriends;

    public Cat(String name, Date dateOfBirth, String breed, String color, Owner owner, List<Cat> listOfFriends) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.breed = breed;
        this.color = color;
        this.owner = owner;
        this.listOfFriends = listOfFriends;
    }
}
