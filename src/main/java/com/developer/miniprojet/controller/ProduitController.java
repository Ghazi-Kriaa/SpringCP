package com.developer.miniprojet.controller;

import com.developer.miniprojet.entity.Produit;
import com.developer.miniprojet.repository.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/produit")
public class ProduitController {
    private final ProduitService produitService;
    @Autowired
    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }
    @GetMapping("/categorie/{id}")
    public ResponseEntity<List<Produit>> getProductsByCategory(@PathVariable Long id) {
        List<Produit> produits = produitService.afficherProduitByCategorie(id)
                ;
        if (produits.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }
    @GetMapping
    public List<Produit> findAll(){return produitService.findAll();}
    @GetMapping("/{id}")
    public Optional<Produit> findById(@PathVariable("id") Long id) {
        return produitService.findById(id);
    }

    @PostMapping("/add/{id}")
    public Produit save(@RequestBody Produit produit , @PathVariable long id){
        return produitService.ajout(produit , id);
    }
    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable("id") Long id) {
        produitService.supprimer(id);
    }
    @PutMapping("update/{id}/{idCat}")
    public Produit modifier(@PathVariable("id") Long id , @RequestBody Produit produit, @PathVariable("idCat") Long idCat) {
        return produitService.modifier(id,produit,idCat);
    }
}
