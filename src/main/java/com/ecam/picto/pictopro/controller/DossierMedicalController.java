package com.ecam.picto.pictopro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecam.picto.pictopro.entity.DossierMedical;
import com.ecam.picto.pictopro.entity.Professionnel;
import com.ecam.picto.pictopro.service.DossierMedicalService;
import com.ecam.picto.pictopro.service.PhraseService;
import com.ecam.picto.pictopro.service.ProfessionnelService;

@Controller
@RequestMapping("/gestionDesDossiers")
public class DossierMedicalController {

	@Autowired
	private ProfessionnelService professionnelService;
	@Autowired
	private PhraseService phraseService;
	@Autowired
	private DossierMedicalService dossierMedicalService;

	@GetMapping("/ajouterUnDossier")
	public String ajouterUnDossier(Model model) {
		model.addAttribute("dossiers", dossierMedicalService.findAll());
		DossierMedical dossierMedicalAAjouter = new DossierMedical();
		model.addAttribute("dossierMedical", dossierMedicalAAjouter);
		return "ajouterUnDossier";
	}

	@PostMapping("/ajouterUnDossier")
	public String ajouterUnDossierMedical(Model model, @ModelAttribute("dossierMedical") DossierMedical dossierMedical,
			@RequestParam("professionnelId") int idPro) {
		Professionnel professionnel = professionnelService.findById(idPro);
		dossierMedical.setProfessionnel(professionnel);
		dossierMedicalService.ajouterUnDossierMedical(dossierMedical);
		model.addAttribute("module", "gestionDesDossiers");
		return "redirect:/gestionDesDossiers/ajouterUnDossier";
	}

	@GetMapping("/consulterLesDossiers")
	public String consulterLesDossiers(Model model) {
		model.addAttribute("phrases", phraseService.afficherPhrases());
		model.addAttribute("dossierMedicals", dossierMedicalService.findAll());
		model.addAttribute("module", "gestionDesDossiers");
		return "consulterLesDossiers";
	}
}