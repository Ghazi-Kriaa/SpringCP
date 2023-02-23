package com.developer.miniprojet.service;
import com.developer.miniprojet.entity.Categorie;
import com.developer.miniprojet.repository.CategorieRepository;
import com.developer.miniprojet.repository.CategorieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import java.text.ParseException;
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
    public void testAddCategorie() throws ParseException {
        Categorie categorie = new Categorie();
        categorie.setNom("Test Category Final");
        categorie.setQuantite(110);
        categorie.setDateCreation(LocalDate.now());
        Categorie savedCategorie = service.ajout(categorie);
        assertThat(savedCategorie).isNotNull();
        assertThat(savedCategorie.getNom()).isEqualTo("Test Category Final");
        assertThat(savedCategorie.getQuantite()).isEqualTo(110);
    }

    @Test
    public void testUpdateCategorie() {
        Categorie categorie = new Categorie();
        categorie.setNom("45");
        categorie.setQuantite(12);
        Categorie savedCategorie = service.ajout(categorie);
        assertThat(savedCategorie).isNotNull();
        assertThat(savedCategorie.getNom()).isEqualTo("45");
        assertThat(savedCategorie.getQuantite()).isEqualTo(12);
        Categorie updatedCategorie = new Categorie();

        updatedCategorie.setNom("Test Category Updated");
        updatedCategorie.setQuantite(10);

        Categorie modifiedCategorie = service.modif(savedCategorie.getId(), updatedCategorie);
        assertThat(modifiedCategorie.getId()).isNotNull();
        assertThat(modifiedCategorie.getNom()).isEqualTo("Test Category Updated");
        assertThat(modifiedCategorie.getQuantite()).isEqualTo(10);
    }


    @Test
    public void testDeleteCategorie()  {
        Categorie categorie = new Categorie();
        categorie.setNom("Test Category3");
        Categorie savedCategorie = service.ajout(categorie);
        assertThat(savedCategorie).isNotNull();
        assertThat(savedCategorie.getNom()).isEqualTo("Test Category3");

        boolean isDeleted = service.supprimer(savedCategorie.getId());
        assertTrue(isDeleted);
        Optional<Categorie> optionalCategorie = service.findById(savedCategorie.getId());
        assertFalse(optionalCategorie.isPresent());
    }


    @Test
    public void FindAllSucces() {
        List<Categorie> foundCategorie = service.findAll();
        assertNotNull(foundCategorie);

    }
    @Test
    public void testFindCategorieById() {

        Categorie categorie = new Categorie();
        categorie.setNom("Test Category");
        categorie.setQuantite(5);
        Categorie savedCategorie = service.ajout(categorie);
        Optional<Categorie> foundCategorie = service.findById(savedCategorie.getId());
        assertThat(foundCategorie).isNotNull();

    }

}