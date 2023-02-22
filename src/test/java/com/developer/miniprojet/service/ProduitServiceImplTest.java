package com.developer.miniprojet.service;
import static org.assertj.core.api.Assertions.assertThat;

import com.developer.miniprojet.controller.ProduitController;
import com.developer.miniprojet.entity.Categorie;
import com.developer.miniprojet.entity.Produit;
import com.developer.miniprojet.repository.CategorieRepository;
import com.developer.miniprojet.repository.CategorieService;
import com.developer.miniprojet.repository.ProduitRepository;
import com.developer.miniprojet.repository.ProduitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProduitServiceImplTest {
    @Autowired
    private ProduitService service;
    @Autowired
    ProduitController produitController;
    @Autowired
    private CategorieService categorieService;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private ProduitRepository produitRepository;
    @Test
    public void SaveProduitSucces(){
        Categorie categories = new Categorie();
        Categorie savedCategorie = categorieService.ajout(categories);
        Produit expectedProduit = Produit.builder()
                .nom("name prod")
                .quantite(1)
                .disponible(true)
                .categorie(savedCategorie)
                .build();
        Produit savedProduit = service.ajout(expectedProduit, expectedProduit.getCategorie().getId());
        assertNotNull(savedProduit);
        assertNotNull(expectedProduit.getNom() , savedProduit.getNom());


    }

    @Test
    public void UpdateProduitSucces(){

        Categorie categories = categorieRepository.findById(1L).orElse(null);
        assertNotNull(categories);
        Produit produit = produitRepository.findById(49L).orElse(null);

        produit.setNom("name prodUpdate1");
        produit.setQuantite(33);
        produit.setDisponible(true);
        produit.setCategorie(categories);

        Produit savedProd=service.ajout(produit, produit.getCategorie().getId());

        Produit upadateProduit = savedProd;
        savedProd = service.modifier(produit.getId(),upadateProduit, produit.getCategorie().getId());


        assertNotNull(upadateProduit);
        assertNotNull(upadateProduit.getNom() , savedProd.getNom());


    }


    @Test
    public void DeleteProduitSucces(){
        Categorie categories = new Categorie();
        Categorie savedCategorie = categorieService.ajout(categories);
        Produit expectedProduit = Produit.builder()
                .id(59L)
                .nom("name prod")
                .quantite(1)
                .disponible(true)
                .categorie(savedCategorie)
                .build();
        Produit savedProd=service.ajout(expectedProduit, expectedProduit.getCategorie().getId());

        boolean isDelted= service.supprimer(savedProd.getId());
        assertTrue(isDelted);
        Optional<Produit>optionalProduit=produitRepository.findById(savedProd.getId());
        assertFalse(optionalProduit.isPresent());
    }

    @Test
    public void FindAllSucces() {
        List<Produit> produit =service.findAll();
        assertThat(produit).isNotNull();
    }
    @Test
    public void FindByIdSucces() {
        Optional<Produit> found = service.findById(2L);

        assertNotNull(found);
        assertThat(found).isNotNull();

    }

}