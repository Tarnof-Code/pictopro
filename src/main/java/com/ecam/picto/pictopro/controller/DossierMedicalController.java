package com.ecam.picto.pictopro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.ecam.picto.pictopro.entity.DossierMedical;
import com.ecam.picto.pictopro.entity.Phrase;
import com.ecam.picto.pictopro.entity.Professionnel;
import com.ecam.picto.pictopro.repository.DossierMedicalRepository;
import com.ecam.picto.pictopro.repository.PhraseRepository;
import com.ecam.picto.pictopro.service.DossierMedicalService;
import com.ecam.picto.pictopro.service.PhraseService;
import com.ecam.picto.pictopro.service.ProfessionnelService;

@Controller

public class DossierMedicalController {

	private final DossierMedicalRepository dossierMedicalRepository;
	private final PhraseRepository phraseRepository;

	public DossierMedicalController(DossierMedicalRepository dossierMedicalRepository,
			PhraseRepository phraseRepository) {
		this.dossierMedicalRepository = dossierMedicalRepository;
		this.phraseRepository = phraseRepository;
	}

	@Autowired
	private ProfessionnelService professionnelService;
	@Autowired
	private PhraseService phraseService;
	@Autowired
	private DossierMedicalService dossierMedicalService;

	@GetMapping("/consulterLesDossiers")
	public String consulterLesDossiers(Model model) {
		model.addAttribute("dossierMedicals", dossierMedicalService.findAll());
		model.addAttribute("module", "gestionDesDossiers");
		return "consulterLesDossiers";
	}

	@GetMapping("/consulterLesDossiers/{id}")
	public String afficherDetailsDossier(@PathVariable("id") int id, Model model) {
		Optional<DossierMedical> dossierMedical = this.dossierMedicalRepository.findById(id);
		if (dossierMedical.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}
		model.addAttribute("dossierMedical", dossierMedical.get());
		Iterable<Phrase> phrasesIterable = this.phraseRepository
				.findAllByDossierMedicalId(dossierMedical.get().getId());
		List<Phrase> phrases = new ArrayList<>();
		phrasesIterable.forEach(phrases::add);
		model.addAttribute("phrases", phrases);
		model.addAttribute("module", "gestionDesDossiers");
		return "detailsDossier";
	}

	@GetMapping("/ajouterUnDossier")
	public String goDossierMedical(Model model) {
		model.addAttribute("dossiers", dossierMedicalService.findAll());
		DossierMedical dossierMedicalAAjouter = new DossierMedical();
		model.addAttribute("dossierMedical", dossierMedicalAAjouter);
		model.addAttribute("message", "Page Création dossier médical");
		model.addAttribute("module", "gestionDesDossiers");
		return "ajouterUnDossier";
	}

	@PostMapping("/ajouterUnDossier")
	public String ajouterUnDossierMedical(Model model, @ModelAttribute("dossierMedical") DossierMedical dossierMedical,
			@RequestParam("professionnelId") Long idPro) {
		Professionnel professionnel = professionnelService.findById(idPro);
		dossierMedical.setProfessionnel(professionnel);
		dossierMedicalService.ajouterUnDossierMedical(dossierMedical);
		model.addAttribute("module", "gestionDesDossiers");
		return "redirect:/gestionDesDossiers/ajouterUnDossier";
	}

}