package com.example.lab3.core.controllers;

import com.example.lab3.core.dto.CatDto;
import com.example.lab3.core.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {

    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatDto> getCat(@PathVariable Long id) {
        try {
            CatDto cat = catService.getCat(id);
            return ResponseEntity.ok(cat);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<CatDto>> getAllCats() {
        try {
            List<CatDto> cats = catService.getAllCats();
            return ResponseEntity.ok(cats);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<CatDto> createCat(@RequestBody CatDto catDto) {
        try {
            CatDto createdCat = catService.createCat(catDto);
            return ResponseEntity.ok(createdCat);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatDto> updateCat(@PathVariable Long id, @RequestBody CatDto catDto) {
        try {
            CatDto updatedCat = catService.updateCat(id, catDto);
            return ResponseEntity.ok(updatedCat);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCat(@PathVariable Long id) {
        try {
            catService.deleteCat(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
