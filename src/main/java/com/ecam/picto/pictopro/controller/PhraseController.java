package com.ecam.picto.pictopro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecam.picto.pictopro.entity.Phrase;
import com.ecam.picto.pictopro.service.PhraseService;

@Controller
@RequestMapping("/gestionDesPhrases")
public class PhraseController {

	@Autowired
	private PhraseService phraseService;

	@GetMapping("/ajouterUnePhrase")
	public String goPhrase(Model model) {
		model.addAttribute("module", "gestionDesPhrases");
		model.addAttribute("message", "Page Ajouter une phrase");
		return "ajouterUnePhrase";
	}

	@PostMapping("/ajouterUnePhrase")
	public String ajouterUnePhrase(Model model, @ModelAttribute("phrase") Phrase phrase) {
		phraseService.ajouterUnePhrase(phrase);
		model.addAttribute("module", "gestionDesPhrases");
		return "redirect:/gestionDesPhrases/ajouterUnePhrase";
	}

	@GetMapping("/consulterLesPhrases")
	public String consulterLesPhrases(Model model) {
		model.addAttribute("module", "gestionDesPhrases");
		model.addAttribute("message", "Page Liste des phrases");
		return "consulterLesPhrases";
	}
}
