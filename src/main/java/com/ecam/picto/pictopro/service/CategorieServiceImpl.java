package com.ecam.picto.pictopro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecam.picto.pictopro.entity.Categorie;
import com.ecam.picto.pictopro.entity.SousCategorie;
import com.ecam.picto.pictopro.repository.CategorieRepository;
import com.ecam.picto.pictopro.repository.SousCategorieRepository;

@Service
public class CategorieServiceImpl implements CategorieService {

	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private SousCategorieRepository sousCategorieRepository;

	@Override
	public void ajouterUneCategorie(Categorie categorie) {
		categorieRepository.save(categorie);
	}

	@Override
	public void ajouterUneSousCategorie(SousCategorie sousCategorie) {
		sousCategorieRepository.save(sousCategorie);
	}

	@Override
	public List<Categorie> afficherCategories() {
		try {
			return categorieRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Categorie findCategorieById(int id) {
		Optional<Categorie> categorie = categorieRepository.findById(id);
		return categorie.orElse(null);
	}

	@Override
	public SousCategorie findSousCategorieById(int id) {
		Optional<SousCategorie> sousCategorie = sousCategorieRepository.findById(id);
		return sousCategorie.orElse(null);
	}
}
