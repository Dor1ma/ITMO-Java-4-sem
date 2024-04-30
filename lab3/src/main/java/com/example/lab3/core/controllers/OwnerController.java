package com.example.lab3.core.controllers;

import com.example.lab3.core.dto.OwnerDto;
import com.example.lab3.core.services.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDto> getOwner(@PathVariable Long id) {
        try {
            OwnerDto owner = ownerService.getOwner(id);
            return ResponseEntity.ok(owner);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<OwnerDto>> getAllOwners() {
        try {
            List<OwnerDto> owners = ownerService.getAllOwners();
            return ResponseEntity.ok(owners);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<OwnerDto> createOwner(@RequestBody OwnerDto ownerDto) {
        try {
            OwnerDto createdOwner = ownerService.createOwner(ownerDto);
            return ResponseEntity.ok(createdOwner);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerDto> updateOwner(@PathVariable Long id, @RequestBody OwnerDto ownerDto) {
        try {
            OwnerDto updatedOwner = ownerService.updateOwner(id, ownerDto);
            return ResponseEntity.ok(updatedOwner);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        try {
            ownerService.deleteOwner(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
