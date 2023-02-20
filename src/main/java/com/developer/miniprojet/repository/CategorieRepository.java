package com.developer.miniprojet.repository;

import com.developer.miniprojet.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategorieRepository extends JpaRepository<Categorie,Long> {
}
