package com.developer.miniprojet.repository;

import com.developer.miniprojet.entity.Categorie;
import com.developer.miniprojet.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,Long> {
    List<Produit> findByCategorie(Optional<Categorie> categorie);
}
