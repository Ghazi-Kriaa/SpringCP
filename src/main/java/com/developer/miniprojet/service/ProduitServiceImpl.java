package com.developer.miniprojet.service;

import com.developer.miniprojet.entity.Categorie;
import com.developer.miniprojet.entity.Produit;
import com.developer.miniprojet.repository.CategorieRepository;
import com.developer.miniprojet.repository.CategorieService;
import com.developer.miniprojet.repository.ProduitRepository;
import com.developer.miniprojet.repository.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class ProduitServiceImpl implements ProduitService {
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private CategorieService categorieService;
    @Autowired
    CategorieRepository categorieRepository;
    @Override
    public List<Produit> afficherProduitByCategorie(Long idCategorie) {
        Optional<Categorie> categorie = categorieService.findById(idCategorie);
        return produitRepository.findByCategorie(categorie);
    }
    @Override
    public List<Produit> findAll(){
        return produitRepository.findAll();}
    @Override
    public Optional<Produit> findById(Long id) {
        return produitRepository.findById(id);
    }
    @Override
    public Produit ajout (Produit produit, long id){
        produit.getCategorie();
        Categorie categorie = categorieService.findById(id).orElse(null);
        produit.setCategorie(categorie);
        LocalDate date = LocalDate.now();
        produit.setDateCreation(date);
        return produitRepository.save(produit);
    }
    @Override
    public boolean supprimer(Long id) {
        produitRepository.deleteById(id);
        return true;
    }
    @Override
    public Produit modifier(Long id, Produit produit, Long idCat) {
        produit.getCategorie();
        Categorie categorie = categorieService.findById(idCat).orElse(null);
        produit.setCategorie(categorie);
        Produit existingProduct = produitRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setNom(produit.getNom());
            existingProduct.setQuantite(produit.getQuantite());
            existingProduct.setCategorie(produit.getCategorie());
            existingProduct.setDisponible(produit.isDisponible());
            existingProduct.setDateModif(LocalDate.now());
            return produitRepository.save(existingProduct);
        }
        return null;
    }
}
