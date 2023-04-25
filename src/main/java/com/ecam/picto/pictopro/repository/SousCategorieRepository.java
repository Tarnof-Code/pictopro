package com.ecam.picto.pictopro.repository;

import com.ecam.picto.pictopro.entity.Categorie;
import com.ecam.picto.pictopro.entity.SousCategorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SousCategorieRepository extends JpaRepository<SousCategorie,Integer> {
}
