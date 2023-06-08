package com.ecam.picto.pictopro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecam.picto.pictopro.entity.Categorie;
import com.ecam.picto.pictopro.entity.SousCategorie;

@Service
public interface CategorieService {
	void ajouterUneCategorie(Categorie categorie);

	void ajouterUneSousCategorie(SousCategorie sousCategorie);

	List<Categorie> afficherCategories();

	Categorie findCategorieById(int id);

	SousCategorie findSousCategorieById(int id);

}
