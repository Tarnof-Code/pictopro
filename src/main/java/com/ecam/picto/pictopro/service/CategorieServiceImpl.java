package com.ecam.picto.pictopro.service;

import com.ecam.picto.pictopro.entity.Categorie;
import com.ecam.picto.pictopro.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieServiceImpl implements CategorieService{

    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public List<Categorie> afficherCategories() {
        try{
            return categorieRepository.findAll();
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Categorie findCategorieById(int id) {
        Optional<Categorie> categorie = categorieRepository.findById(id);
        return categorie.orElse(null);
    }
}
