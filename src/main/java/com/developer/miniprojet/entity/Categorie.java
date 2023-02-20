package com.developer.miniprojet.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nom" , unique = true,length = 20)
    private String nom;
    @Column(name = "quantite")
    private int quantite;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @Column(name = "date_modif")
    private LocalDate dateModif;
    @JsonManagedReference
    @OneToMany(mappedBy = "categorie",cascade = CascadeType.ALL)
    private List<Produit> products;

}
