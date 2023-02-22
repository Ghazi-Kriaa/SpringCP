package com.developer.miniprojet;

import com.developer.miniprojet.entity.Categorie;
import com.developer.miniprojet.repository.CategorieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MiniProjetApplicationTests {
	@Autowired
	private CategorieService service;
	@Test
	void contextLoads() {
		Categorie expectedCategorie = Categorie.builder()
				.nom("GHF")
				.quantite(1)
				.build();
		Categorie savedCategorie = service.ajout(expectedCategorie);
		assertNotNull(savedCategorie);
		assertNotNull(savedCategorie.getId());
		assertEquals(expectedCategorie.getNom(),savedCategorie.getNom());
		assertEquals(expectedCategorie.getQuantite(),savedCategorie.getQuantite());
	}

}
