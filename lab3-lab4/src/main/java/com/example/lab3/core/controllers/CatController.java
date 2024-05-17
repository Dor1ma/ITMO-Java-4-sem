package com.example.lab3.core.controllers;

import com.example.lab3.core.models.Cat;
import com.example.lab3.core.repository.CatRepository;
import com.example.lab3.core.services.CatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {
    private final CatRepository catRepository;

    public CatController(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Cat> getCat(@PathVariable Long id)
    {
        try
        {
            Cat cat = catRepository.findById(id).orElseThrow(() -> new NullPointerException("No such cat"));

            if (cat == null)
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(cat);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Cat>> getCatsByName(@PathVariable String name)
    {
        try
        {
            List<Cat> cats = catRepository.findAllByName(name);

            if (cats.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(cats);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/breed/{breed}")
    public ResponseEntity<List<Cat>> getCatsByBreed(@PathVariable String breed)
    {
        try
        {
            List<Cat> cats = catRepository.findAllByBreed(breed);

            if (cats.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(cats);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dateOfBirth/{dateOfBirth}")
    public ResponseEntity<List<Cat>> getCatsByDateOfBirth(@PathVariable Date dateOfBirth)
    {
        try
        {
            List<Cat> cats = catRepository.findAllByDateOfBirth(dateOfBirth);

            if (cats.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(cats);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/color/{color}")
    public ResponseEntity<List<Cat>> getCatsByColor(@PathVariable String color)
    {
        try
        {
            List<Cat> cats = catRepository.findAllByColor(color);

            if (cats.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(cats);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Cat>> getAllCats()
    {
        try
        {
            List<Cat> cats = catRepository.findAll();

            if (cats.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(cats);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Cat> createCat(@RequestBody Cat cat)
    {
        try
        {
            Cat newCat = catRepository.save(cat);

            if (newCat == null)
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return ResponseEntity.ok(newCat);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Cat> updateCat(@PathVariable int id, @RequestBody Cat cat)
    {
        try
        {
            Cat updatedCat = catRepository.save(cat);

            if (updatedCat == null)
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return ResponseEntity.ok(updatedCat);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCat(@PathVariable Long id)
    {
        try
        {
            catRepository.deleteById(id);

            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/id/{id}/ownerId/{ownerId}")
    public ResponseEntity<Cat> getCat(@PathVariable Long id, @PathVariable Long ownerId)
    {
        try
        {
            Cat cat = catRepository.findByIdAndOwnerId(id, ownerId);

            if (cat == null)
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(cat);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/name/{name}/id/{id}")
    public ResponseEntity<List<Cat>> getCatsByName(@PathVariable String name, @PathVariable Long id)
    {
        try
        {
            List<Cat> cats = catRepository.findAllByNameAndOwnerId(name, id);

            if (cats.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(cats);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/breed/{breed}/id/{id}")
    public ResponseEntity<List<Cat>> getCatsByBreed(@PathVariable String breed, @PathVariable Long id)
    {
        try
        {
            List<Cat> cats = catRepository.findAllByBreedAndOwnerId(breed, id);

            if (cats.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(cats);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/dateOfBirth/{dateOfBirth}/id/{id}")
    public ResponseEntity<List<Cat>> getCatsBydateOfBirth(@PathVariable Date dateOfBirth, @PathVariable Long id)
    {
        try
        {
            List<Cat> cats = catRepository.findAllByDateOfBirthAndOwnerId(dateOfBirth, id);

            if (cats.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(cats);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/color/{color}/id/{id}")
    public ResponseEntity<List<Cat>> getCatsByColor(@PathVariable String color, @PathVariable Long id)
    {
        try
        {
            List<Cat> cats = catRepository.findAllByColorAndOwnerId(color, id);

            if (cats.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(cats);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("cats/id/{id}")
    public ResponseEntity<List<Cat>> getAllCatsByOwner(@RequestParam Long id)
    {
        try
        {
            List<Cat> cats = catRepository.findAllByOwnerId(id);

            if (cats.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(cats);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
