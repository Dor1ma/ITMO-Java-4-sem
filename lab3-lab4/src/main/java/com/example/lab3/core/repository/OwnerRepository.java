package com.example.lab3.core.repository;

import com.example.lab3.core.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByUsername(String username);
    Boolean existsOwnerByUsername(String username);
    Optional<Owner> findById(Long id);
    List<Owner> findAll();

    void deleteOwnerDaoById(Long id);
}
