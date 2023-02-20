package com.developer.miniprojet.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
@Data
@Builder
public class CategorieDto {

    private Long Id;
    private String Nom;
    private int Qt;
    private Timestamp Date_creation;
    private Timestamp Date_modif;
    private List<ProduitDto> p;

}

