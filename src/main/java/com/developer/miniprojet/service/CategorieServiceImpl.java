package com.developer.miniprojet.service;

import com.developer.miniprojet.entity.Categorie;
import com.developer.miniprojet.repository.CategorieRepository;
import com.developer.miniprojet.repository.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class CategorieServiceImpl implements CategorieService {
    @Autowired
    private  CategorieRepository categorieRepository;

    @Override
    public Optional<Categorie> findById(Long id) {
        return categorieRepository.findById(id);
    }
    public List<Categorie> findAll(){
        return categorieRepository.findAll();
    }
    @Override
    public Categorie ajout(Categorie c) {
        if (isIdNull(c)) {
            c.setDateCreation(LocalDate.now());
            return categorieRepository.save(c);
        } else {
            throw new IllegalArgumentException("Object ID must be null for creation");
        }

    }

    @Override
    public boolean supprimer(Long id) {
        categorieRepository.deleteById(id);
        return true;
    }

    @Override
    public Categorie modif(Long id , Categorie c) {
            Categorie existingCategorie = categorieRepository.findById(id).orElse(null);
            if (existingCategorie != null) {
                existingCategorie.setNom(c.getNom());
                existingCategorie.setQuantite(c.getQuantite());
                existingCategorie.setDateModif(LocalDate.now());
                return categorieRepository.save(existingCategorie);
            }
            return null;
        }
    @Override
    public boolean isIdNull(Categorie c) {
        return c.getId() == null;
    }
    }

