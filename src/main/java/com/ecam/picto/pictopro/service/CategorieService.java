package com.ecam.picto.pictopro.service;

import com.ecam.picto.pictopro.entity.Categorie;
import com.ecam.picto.pictopro.entity.SousCategorie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategorieService {
    public List<Categorie> afficherCategories();
    public Categorie findCategorieById(int id);
    public SousCategorie findSousCategorieById(int id);

}