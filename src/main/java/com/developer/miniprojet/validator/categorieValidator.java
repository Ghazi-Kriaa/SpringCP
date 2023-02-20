package com.developer.miniprojet.validator;

import com.developer.miniprojet.dto.CategorieDto;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class categorieValidator {
    public static List<String> validate(CategorieDto categorieDto){
        List<String>errors=new ArrayList<>();
        if(categorieDto == null || !StringUtils.hasLength(String.valueOf(categorieDto.getId()))){
            errors.add("Veuillez renseigner l'id de la categorie");
        }
        return errors;
    }
}
