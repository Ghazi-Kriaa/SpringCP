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
    public void findAllTest(){
        List<Produit> produit =service.findAll();
        assertThat(produit).isNotNull();
    }
    @Test
    public void findByIdTest(){
        Optional<Produit> produit = service.findById(8L);
        assertThat(produit).isNotNull();
    }
    @Test
    public void ajoutProduitTest(){
        Categorie category = categorieRepository.findById(2L).orElse(null);
        assertNotNull(category);
        Produit expectedProduit = Produit.builder()
                .nom("aaa")
                .quantite(41)
                .disponible(false)
                .categorie(category)
                .build();
        Produit savedProduit = service.ajout(expectedProduit,category.getId());
        assertNotNull(savedProduit);
        assertNotNull(savedProduit.getId());
        assertNotNull(expectedProduit.getQuantite() , String.valueOf(savedProduit.getQuantite()));
        assertNotNull(expectedProduit.getNom() , savedProduit.getNom());
    }
    @Test
    public void modifProduitTest(){
        Categorie category = categorieRepository.findById(1L).orElse(null);
        assertNotNull(category);
        Produit produit = produitRepository.findById(8L).orElse(null);
        assertNotNull(produit);
        produit.setNom("ethgth");
        produit.setDisponible(true);
        produit.setCategorie(category);
        produit.setQuantite(140);
        Produit savedProd=service.ajout(produit,category.getId());

        Produit upadateProduit = savedProd;
        savedProd = service.modifier(savedProd.getId(), upadateProduit,category.getId());


        assertNotNull(upadateProduit);
        assertNotNull(upadateProduit.getId());
        assertNotNull(upadateProduit.getQuantite() , String.valueOf(savedProd.getQuantite()));
        assertNotNull(upadateProduit.getNom() , savedProd.getNom());
    }
    @Test
    public void supprimerProduitTest(){
        Categorie categorie = categorieRepository.findById(1L).orElse(null);
        assertNotNull(categorie);
        Produit produit = new Produit();
        Categorie savedCategorie = categorieService.ajout(categorie);
        Produit expectedProduit = Produit.builder()
                .id(15L)
                .build();

        boolean isDelted= service.supprimer(expectedProduit.getId());
        assertTrue(isDelted);
        Optional<Produit> optionalProduit=produitRepository.findById(expectedProduit.getId());
        assertFalse(optionalProduit.isPresent());
    }
}