package com.developer.miniprojet.service;
import com.developer.miniprojet.entity.Categorie;
import com.developer.miniprojet.repository.CategorieRepository;
import com.developer.miniprojet.repository.CategorieService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategorieServiseImplTest {
    @Autowired
    private CategorieService service;
    @Autowired
    private CategorieRepository categorieRepository;
    @Test
    public void findAllTest(){
        List<Categorie> categorie =service.findAll();
        assertThat(categorie).isNotNull();
    }
    @Test
    public void findByIdTest(){
        Optional<Categorie> categorie = service.findById(1L);
        assertThat(categorie).isNotNull();
    }
    @Test
    public void ajoutCategoryTest(){
        Categorie expectedCategorie = Categorie.builder()
                .nom("bbb")
                .quantite(1)
                .build();
        Categorie savedCategorie = service.ajout(expectedCategorie);
        assertNotNull(savedCategorie);
        assertNotNull(savedCategorie.getId());
        assertEquals(expectedCategorie.getNom(),savedCategorie.getNom());
        assertEquals(expectedCategorie.getQuantite(),savedCategorie.getQuantite());
    }

    @Test
    public void modifCategoryTest(){
        Categorie category = categorieRepository.findById(1L).orElse(null);
        category.setQuantite(111);
        category.setNom("Info");

        Categorie savedCat=service.ajout(category);

        Categorie upadateCategorie = savedCat;
        savedCat = service.modif(upadateCategorie.getId(), upadateCategorie);
        assertNotNull(upadateCategorie);
        assertNotNull(upadateCategorie.getId());
        assertNotNull(upadateCategorie.getQuantite() , String.valueOf(savedCat.getQuantite()));
        assertNotNull(upadateCategorie.getNom() , savedCat.getNom());


    }
    @Test
    public void supprimerCategoryTest() {
        Categorie expectedCategorie = Categorie.builder()
                .id(20L)
                .build();
        Categorie categorieAjoue = service.ajout(expectedCategorie);


        boolean isDeleted =service.supprimer(categorieAjoue.getId());
        Assertions.assertTrue(isDeleted);
        Optional<Categorie> categorie=service.findById(categorieAjoue.getId());
        assertFalse(categorie.isPresent());
}}