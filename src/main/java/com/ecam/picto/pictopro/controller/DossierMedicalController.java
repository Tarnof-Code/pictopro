package com.ecam.picto.pictopro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/consulterLesDossiers")
	public String consulterLesDossiers(Model model) {
		model.addAttribute("dossierMedicals", dossierMedicalService.findAll());
		model.addAttribute("module", "gestionDesDossiers");
		return "consulterLesDossiers";
	}

	@PreAuthorize("hasRole('ROLE_PRO')")
	@GetMapping("/consulterMesDossiers")
	public String consulterMesDossiers(Model model, Authentication authentication) {
		String username = authentication.getName();
		Professionnel currentUser = professionnelService.findByUsername(username);

		List<DossierMedical> dossiers = dossierMedicalService.findByForeignKey(currentUser.getId());
		model.addAttribute("dossierMedicals", dossiers);
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
		model.addAttribute("dossierMedical", new DossierMedical());
		model.addAttribute("module", "gestionDesDossiers");
		return "ajouterUnDossier";
	}

	@PostMapping("/ajouterUnDossier")
	public String ajouterUnDossierMedical(RedirectAttributes redirectAttributes, @ModelAttribute("dossierMedical") @Valid DossierMedical dossierMedical, BindingResult bindingResult, Authentication authentication) {
		String username = authentication.getName();
		Professionnel currentUser = professionnelService.findByUsername(username);

		dossierMedical.setProfessionnel(currentUser);

		try {
			if (bindingResult.hasErrors()) {
				return "ajouterUnDossier";
			}
			dossierMedicalService.ajouterUnDossierMedical(dossierMedical);
			redirectAttributes.addFlashAttribute("successMessage", "Dossier médical ajouté avec succès.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Une erreur s'est produite lors de l'ajout du dossier médical.");
		}

		return "redirect:/ajouterUnDossier";
	}



}