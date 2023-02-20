package com.developer.miniprojet.repository;

import com.developer.miniprojet.entity.Produit;
import java.util.List;
import java.util.Optional;
public interface ProduitService {
    List<Produit> afficherProduitByCategorie(Long idCategorie);
    List<Produit> findAll();
    Optional<Produit> findById(Long id);
    Produit ajout (Produit produit , long id);
    boolean supprimer(Long id);
    Produit modifier(Long id , Produit produit, Long idCat);
}
