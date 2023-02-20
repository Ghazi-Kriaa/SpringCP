package com.developer.miniprojet.controller;

import com.developer.miniprojet.entity.Categorie;
import com.developer.miniprojet.repository.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/categorie")
public class CategorieController {
    private CategorieService categorieService;
    @Autowired
    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }
    @GetMapping("/{id}")
    public Optional<Categorie> findById(@PathVariable("id") Long id) {
        return categorieService.findById(id);
    }
    @GetMapping
    public List<Categorie> findAll(){return categorieService.findAll();}
    @PostMapping
    public Categorie ajout(@RequestBody Categorie c) {
        return categorieService.ajout(c);
    }
    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable("id") Long id) {
        categorieService.supprimer(id);
    }
    @PutMapping("/{id}")
    public Categorie modif(@PathVariable("id") Long id , @RequestBody Categorie c) {
        return categorieService.modif(id,c);
    }
}
