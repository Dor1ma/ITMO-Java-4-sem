package com.example.lab3.core.controllers;

import com.example.lab3.core.customExceptions.RoleNotSuitableException;
import com.example.lab3.core.models.Owner;
import com.example.lab3.core.repository.OwnerRepository;
import com.example.lab3.core.services.OwnerService;
import com.example.lab3.core.services.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerRepository ownerRepository;

    public OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @GetMapping
    public Owner userAccess(Authentication authentication)
    {
        if (authentication == null) return null;

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        try
        {
            var owner = ownerRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            owner.setPassword("********");

            return owner;
        }
        catch (Exception e)
        {
            e.printStackTrace();

            return null;
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/owners")
    public List<Owner> adminAccess(Authentication authentication)
    {
        try
        {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            if (!userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
            {
                throw new RoleNotSuitableException("You don't have sufficient rights");
            }

            List<Owner> owners = ownerRepository.findAll();

            if(owners.isEmpty())
            {
                throw new UsernameNotFoundException("Users not found");
            }

            return owners;
        }
        catch (Exception e)
        {
            e.printStackTrace();

            return null;
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable Long id)
    {
        try
        {
            Owner owner = ownerRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));

            if (owner == null)
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(owner);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner)
    {
        try
        {
            Owner newOwner = ownerRepository.save(owner);

            if (newOwner == null)
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return ResponseEntity.ok(newOwner);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable int id, @RequestBody Owner owner)
    {
        try
        {
            Owner newOwner = ownerRepository.save(owner);

            if (newOwner == null)
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return ResponseEntity.ok(newOwner);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id)
    {
        try
        {
            ownerRepository.deleteOwnerDaoById(id);

            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
