package com.ecam.picto.pictopro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecam.picto.pictopro.entity.Categorie;
import com.ecam.picto.pictopro.entity.Mot;
import com.ecam.picto.pictopro.entity.SousCategorie;
import com.ecam.picto.pictopro.entity.Tag;
import com.ecam.picto.pictopro.service.CategorieService;
import com.ecam.picto.pictopro.service.MotService;
import com.ecam.picto.pictopro.service.TagService;

@Controller
@RequestMapping("/gestionDesMots")
public class MotController {
	@Autowired
	private TagService tagService;
	@Autowired
	private CategorieService categorieService;
	@Autowired
	private MotService motService;

	@GetMapping("/ajouterUnMot")
	public String ajouterUnMot(Model model) {

		model.addAttribute("module", "gestionDesMots");
		model.addAttribute("mots", motService.findAll());

		Mot motAAjouter = new Mot();
		model.addAttribute("mot", motAAjouter);

		List<Tag> listeTags = tagService.afficherTags();
		model.addAttribute("tags", listeTags);

		Categorie selectedCategorie = new Categorie();
		model.addAttribute("selectedCategorie", selectedCategorie);

		SousCategorie selectedSousCategorie = new SousCategorie();
		model.addAttribute("selectedSousCategorie", selectedSousCategorie);

		List<Categorie> listeCategories = categorieService.afficherCategories();
		model.addAttribute("categories", categorieService.afficherCategories());

		return "ajouterUnMot";
	}

	@RequestMapping("/sousCategories/{id}")
	public String affichageSousCategories(Model model, @PathVariable("id") int id) {

		Categorie categorie = categorieService.findCategorieById(id);
		model.addAttribute("categorie", categorie);

		SousCategorie selectedSousCategorie = new SousCategorie();
		model.addAttribute("selectedSousCategorie", selectedSousCategorie);

		if (categorie.getListeSousCategorie().size() > 0) {
			return "/components/listeSousCategories::listeSousCategories";
		} else {
			return "/components/listeSousCategories::listeSousCategoriesVide";
		}
	}

	@RequestMapping("/mot/{id}")
	public String afficherMot(Model model, @PathVariable("id") int id) {
		Mot mot = motService.findById(id);
		model.addAttribute("motSelection", mot);
		return "/components/consulterModifierMot";
	}

	@RequestMapping("/verbeIrregulier")
	public String affichageFormulaireVerbeIrregulier(@ModelAttribute("mot") Mot mot) {
		return "/components/formulairesIrreguliers::verbeIrregulier(conjugaisons='mot.irregulier.conjugaisons',participePasse='mot.irregulier.participePasse')";
	}

	@RequestMapping("/nomIrregulier")
	public String affichageFormulaireNomIrregulier(@ModelAttribute("mot") Mot mot) {
		return "/components/formulairesIrreguliers::nomIrregulier(nomPluriel='mot.irregulier.pluriel')";
	}

	@RequestMapping("/adjectifIrregulier")
	public String affichageFormulaireAdjectifIrregulier(@ModelAttribute("mot") Mot mot) {
		return "/components/formulairesIrreguliers::adjectifIrregulier(feminin='mot.irregulier.feminin',adjPluriel='mot.irregulier.pluriel')";
	}

	@PostMapping("/ajouterUnMot")
	public String ajouterUnMot(Model model, @ModelAttribute("mot") Mot mot, @RequestParam("categorieId") int idCat,
			@RequestParam("sousCategorieId") int idSousCat, @RequestParam("selectedTags") List<String> selectedTags) {

		Categorie categorie = categorieService.findCategorieById(idCat);
		SousCategorie sousCategorie = categorieService.findSousCategorieById(idSousCat);
		List<Tag> listeTags = tagService.findAllByNomIn(selectedTags);

		mot.setCategorie(categorie);
		mot.setSousCategorie(sousCategorie);
		mot.setTags(listeTags);

		motService.ajouterUnMot(mot);

		model.addAttribute("module", "gestionDesMots");

		return "redirect:/gestionDesMots/ajouterUnMot";
	}

	@GetMapping("/consulterLesMots")
	public String consulterLesMots(Model model) {
		model.addAttribute("categories", categorieService.afficherCategories());
		model.addAttribute("mots", motService.findAll());
		model.addAttribute("module", "gestionDesMots");
		return "consulterLesMots";
	}
}
