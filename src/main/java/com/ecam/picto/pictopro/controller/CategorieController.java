package com.ecam.picto.pictopro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecam.picto.pictopro.entity.Categorie;
import com.ecam.picto.pictopro.service.CategorieService;

@Controller
@RequestMapping("/gestionDesCategories")
public class CategorieController {

	@Autowired
	private CategorieService categorieService;

	@GetMapping("/ajouterUneCategorie")
	public String goCategorie(Model model) {
		model.addAttribute("module", "gestionDesCategories");
		model.addAttribute("message", "Page Ajouter une catégorie/sous catégorie");
		return "ajouterUnePhrase";
	}

	@PostMapping("/ajouterUneCategorie")
	public String ajouterUneCategorie(Model model, @ModelAttribute("categorie") Categorie categorie) {
		categorieService.ajouterUneCategorie(categorie);
		model.addAttribute("module", "gestionDesCategories");
		return "redirect:/gestionDesCategories/ajouterUneCategorie";
	}

	@GetMapping("/consulterLesCategories")
	public String consulterLesCategories(Model model) {
		model.addAttribute("module", "gestionDesCategories");
		model.addAttribute("message", "Page Liste des catégories/sous catégories");
		return "consulterLesCategories";
	}
}