package com.ecam.picto.pictopro.controller;

import com.ecam.picto.pictopro.entity.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecam.picto.pictopro.service.CategorieService;
import com.ecam.picto.pictopro.service.MotService;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EchangeController {
	@Autowired
	private MotService motService;
	@Autowired
	private CategorieService categorieService;
	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("/echange")
	public String goEchange(Model model) {
		model.addAttribute("categories", categorieService.afficherCategories());
		model.addAttribute("mots", motService.findAll());
		model.addAttribute("module", "echange");
		return "echange";
	}

	@RequestMapping("/listeMotsCat/{id}")
	public String listeParCategorie(Model model, @PathVariable("id") int id) {

		Categorie categorie = categorieService.findCategorieById(id);
		model.addAttribute("categorie", categorie);

		if (categorie.getListeMotsParCategorie().size() > 0) {
			return "/components/listesParCategorie::motItemsCat";
		} else {
			return "/components/listesParCategorie::motItemsVide";
		}
	}

	@RequestMapping("/listeMotsSousCat/{id}")
	public String listeParSousCategorie(Model model, @PathVariable("id") int id) {

		SousCategorie sousCategorie = categorieService.findSousCategorieById(id);
		model.addAttribute("sousCategorie", sousCategorie);

		if (sousCategorie.getListeMotsParSousCategorie().size() > 0) {
			return "/components/listesParCategorie::motItemsSousCat";
		} else {
			return "/components/listesParCategorie::motItemsVide";
		}
	}

	@RequestMapping("/getConjugaisonsIrregulier/{id}")
	@ResponseBody
	public ResponseEntity<String> conjugaisonsIrregulier(@PathVariable("id") int id) throws JsonProcessingException {

		Mot mot = motService.findById(id);
		List<Conjugaison> listeConjugaisons = mot.getIrregulier().getConjugaisons();
		String json = objectMapper.writeValueAsString(listeConjugaisons);

		return ResponseEntity.ok(json);
	}

	@RequestMapping("/getIrregulier/{id}")
	@ResponseBody
	public ResponseEntity<String> accordNomIrregulier(@PathVariable("id") int id) throws JsonProcessingException {

		Mot mot = motService.findById(id);
		Irregulier irregulier = mot.getIrregulier();
		String json = objectMapper.writeValueAsString(irregulier);

		return ResponseEntity.ok(json);

	}
}
