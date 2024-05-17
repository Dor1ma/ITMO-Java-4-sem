package com.example.lab3.core.repository;

import com.example.lab3.core.models.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
    List<Cat> findAllByOwnerId(Long id);

    List<Cat> findAllByNameAndOwnerId(String name, Long id);

    List<Cat> findAllByDateOfBirthAndOwnerId(Date dateOfBirth, Long id);

    List<Cat> findAllByBreedAndOwnerId(String breed, Long id);

    List<Cat> findAllByColorAndOwnerId(String color, Long id);

    Cat findByIdAndOwnerId(Long id, Long ownerId);

    List<Cat> findAllByName(String name);

    List<Cat> findAllByDateOfBirth(Date dateOfBirth);

    List<Cat> findAllByBreed(String breed);

    List<Cat> findAllByColor(String color);

    List<Cat> findAll();

    Cat findById(long id);;

    void deleteById(long id);
}
