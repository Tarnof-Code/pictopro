package com.ecam.picto.pictopro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecam.picto.pictopro.entity.Categorie;
import com.ecam.picto.pictopro.entity.SousCategorie;

@Service
public interface CategorieService {
	public void ajouterUneCategorie(Categorie categorie);

	public void ajouterUneSousCategorie(SousCategorie sousCategorie);

	public List<Categorie> afficherCategories();

	public Categorie findCategorieById(int id);

	public SousCategorie findSousCategorieById(int id);
}
