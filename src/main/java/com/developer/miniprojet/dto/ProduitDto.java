package com.developer.miniprojet.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProduitDto {
    private Long id;
    private CategorieDto cat;
    private String nom;
    private int quantite;
    private boolean disponible;



}
