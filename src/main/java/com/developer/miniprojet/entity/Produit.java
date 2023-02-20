package com.developer.miniprojet.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name ="id_categorie")
    private Categorie categorie;
    @Column(name = "nom", length = 20)
    private String nom;
    @Column(name = "quantite")
    private int quantite;
    @Column(name = "disponible")
    private boolean disponible;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @Column(name = "date_modif")
    private LocalDate dateModif;


}