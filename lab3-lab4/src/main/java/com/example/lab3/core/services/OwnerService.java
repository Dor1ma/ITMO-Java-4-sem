package com.example.lab3.core.services;

import com.example.lab3.core.models.Cat;
import com.example.lab3.core.models.Owner;
import com.example.lab3.core.repository.OwnerRepository;
import com.example.lab3.core.dto.OwnerDto;
import com.example.lab3.core.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final CatRepository catRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository, CatRepository catRepository) {
        this.ownerRepository = ownerRepository;
        this.catRepository = catRepository;
    }

    public OwnerDto getOwner(Long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new RuntimeException("Owner not found"));
        return convertToDto(owner);
    }

    public List<OwnerDto> getAllOwners() {
        List<Owner> owners = ownerRepository.findAll();
        return owners.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public OwnerDto createOwner(OwnerDto ownerDto) {
        Owner owner = convertToEntity(ownerDto);
        Owner savedOwner = ownerRepository.save(owner);
        return convertToDto(savedOwner);
    }

    public OwnerDto updateOwner(Long id, OwnerDto ownerDto) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new RuntimeException("Owner not found"));
        // update owner fields here
        Owner updatedOwner = ownerRepository.save(owner);
        return convertToDto(updatedOwner);
    }

    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }

    private OwnerDto convertToDto(Owner owner) {
        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setId(owner.getId());
        ownerDto.setName(owner.getName());
        ownerDto.setDateOfBirth(owner.getDateOfBirth());
        if (owner.getCats() != null) {
            ownerDto.setCatsIds(owner.getCats().stream().map(Cat::getId).collect(Collectors.toList()));
        }
        return ownerDto;
    }

    private Owner convertToEntity(OwnerDto ownerDto) {
        long id = ownerDto.getId();
        String name = ownerDto.getName();
        Date dateOfBirth = ownerDto.getDateOfBirth();
        List<Long> catsIds = ownerDto.getCatsIds();
        List<Cat> cats = null;
        String username = ownerDto.getUsername();
        String password = ownerDto.getPassword();
        String role = ownerDto.getRole();

        if (catsIds != null) {
            cats = catsIds.stream()
                    .map(catId -> catRepository.findById(catId).orElseThrow(() -> new RuntimeException("Cat not found")))
                    .collect(Collectors.toList());
        }

        return new Owner(
                name,
                dateOfBirth,
                cats,
                username,
                password,
                role
        );
    }

}
