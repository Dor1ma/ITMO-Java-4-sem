package org.core.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.Getter;

@Entity
@Table(name = "owners")
@Getter
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @OneToMany(mappedBy = "owner")
    private List<Cat> cats;

    public Owner(String name, Date dateOfBirth, List<Cat> cats) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.cats = cats;
    }
}
