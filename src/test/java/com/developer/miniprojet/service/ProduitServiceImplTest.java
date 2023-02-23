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

import java.text.ParseException;
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
        categories.setNom("aa");
        categories.setQuantite(120);
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
    public void testUpdateProduct() {
        Produit produit = new Produit();
        Categorie categorie = new Categorie();
        categorie.setNom("Test Category");

        Categorie savedCategorie = categorieService.ajout(categorie);
        produit.setCategorie(savedCategorie);
        Long categorieId = savedCategorie.getId();

        Produit produitSaved = service.ajout(produit,categorieId);

        produitSaved.setNom("Updated Test Product");
        produitSaved.setQuantite(20);
        produitSaved.setDisponible(true);

        service.modifier(produit.getId(), produitSaved, categorieId);

        Optional<Produit> produitRetrieved = service.findById(produitSaved.getId());
        assertTrue(produitRetrieved.isPresent());
        Produit retrievedProduct = produitRetrieved.get();
        assertEquals("Updated Test Product", retrievedProduct.getNom());
        assertEquals(20, retrievedProduct.getQuantite());
        assertEquals(true, retrievedProduct.isDisponible());
    }




    @Test
    public void testDeleteProduit() throws ParseException {
        Produit produit = new Produit();
        Categorie categorie = new Categorie();
        categorie.setNom("Test");

        Categorie savedCategorie = categorieService.ajout(categorie);
        produit.setNom("produit test");
        produit.setDisponible(true);
        produit.setCategorie(savedCategorie);
        produit.setQuantite(10);
        Long categorieId = savedCategorie.getId();
        produit = service.ajout(produit,categorieId);
        boolean isDeleted = service.supprimer(produit.getId());
        assertTrue(isDeleted);
        Optional<Produit> optionalProduit = produitRepository.findById(produit.getId());
        assertFalse(optionalProduit.isPresent());
    }

    @Test
    public void FindAllSucces() {
        List<Produit> produit =service.findAll();
        assertThat(produit).isNotNull();
    }
    @Test
    public void testFindProduitById() {
        Categorie categorie = new Categorie();
        categorie.setNom("Test Category");
        categorie.setQuantite(5);
        Categorie savedCategorie = categorieService.ajout(categorie);

        Produit produit = new Produit();
        produit.setNom("Test Product");
        produit.setQuantite(10);
        produit.setCategorie(savedCategorie);
        Produit savedProduit = service.ajout(produit,savedCategorie.getId());


        Optional<Produit> foundProduit = service.findById(savedProduit.getId());
        assertThat(foundProduit).isNotNull();

    }





}