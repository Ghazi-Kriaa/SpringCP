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

import java.time.LocalDate;
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
    public void SaveCategorieSucces(){
        Categorie expectedCategorie = Categorie.builder()
                .nom("jfiv")
                .quantite(1)
                .build();
        Categorie savedCategorie = service.ajout(expectedCategorie);
        assertNotNull(savedCategorie);
        assertNotNull(savedCategorie.getId());
        assertEquals(expectedCategorie.getNom(),savedCategorie.getNom());
        assertEquals(expectedCategorie.getQuantite(),savedCategorie.getQuantite());
    }

    @Test
    public void DeleteCategorieSucces(){

        Categorie expectedCategorie = Categorie.builder()
                .id(4L)
                .build();
        Categorie savedCat=service.ajout(expectedCategorie);


        boolean isDelted= service.supprimer(savedCat.getId());
        assertTrue(isDelted);
        Optional<Categorie> optionalCategories=categorieRepository.findById(savedCat.getId());
        assertFalse(optionalCategories.isPresent());
    }

    @Test
    public void FindAllSucces() {
        List<Categorie> foundCategorie = service.findAll();
        assertNotNull(foundCategorie);

    }
    @Test
    public void FindByIdSucces() {
        Optional<Categorie> found = service.findById(1L);

        assertNotNull(found);
        assertThat(found).isNotNull();

    }

}