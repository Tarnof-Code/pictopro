package com.ecam.picto.pictopro.controller;

import com.ecam.picto.pictopro.entity.DossierMedical;
import com.ecam.picto.pictopro.entity.Professionnel;
import com.ecam.picto.pictopro.repository.DossierMedicalRepository;
import com.ecam.picto.pictopro.repository.ProfessionnelRepository;
import com.ecam.picto.pictopro.service.ProfessionnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/consulterLesPros")
public class ProfessionnelController {

	private final ProfessionnelRepository professionnelRepository;
	private final DossierMedicalRepository dossierMedicalRepository;

	public ProfessionnelController(ProfessionnelRepository professionnelRepository,
			DossierMedicalRepository dossierMedicalRepository) {
		this.professionnelRepository = professionnelRepository;
		this.dossierMedicalRepository = dossierMedicalRepository;
	}

	@Autowired
	private ProfessionnelService professionnelService;

	@GetMapping("/listeDesPros")
	public String consulterLesPros(Model model) {
		model.addAttribute("professionnels", professionnelService.findAll());
		model.addAttribute("module", "consulterLesPros");
		return "consulterLesPros";
	}

	@GetMapping(path = "/detailsPro/{id}")
	public String afficherDetailsPro(@PathVariable("id") int id, Model model) {
		Optional<Professionnel> professionnel = Optional.ofNullable(this.professionnelRepository.findById(id));
		if (professionnel.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}
		model.addAttribute("professionnel", professionnel.get());
		Iterable<DossierMedical> dossierMedicalsIterable = this.dossierMedicalRepository
				.findAllByProfessionnelId(professionnel.get().getId());
		List<DossierMedical> dossierMedicals = new ArrayList<>();
		dossierMedicalsIterable.forEach(dossierMedicals::add);
		model.addAttribute("dossierMedicals", dossierMedicals);
		model.addAttribute("module", "consulterLesPros");
		return "detailsPro";
	}

	@PostMapping("/{id}/activationCompte")
	public String activationCompte(@PathVariable("id") int id,
								   @RequestParam("isActive") boolean isActive,
								   Model model) {
		Optional<Professionnel> optionalProfessionnel = Optional.ofNullable(professionnelRepository.findById(id));
		if (optionalProfessionnel.isPresent()) {
			Professionnel existingProfessionnel = optionalProfessionnel.get();
			existingProfessionnel.setActive(isActive);
			professionnelRepository.save(existingProfessionnel);
			model.addAttribute("message", "Succès de la mise à jour du compte !");
			model.addAttribute("professionnel", existingProfessionnel);
			int professionnelId = existingProfessionnel.getId();
			model.addAttribute("dossierMedicals", dossierMedicalRepository.findAllByProfessionnelId(professionnelId));
			model.addAttribute("module", "detailsPro");
		} else {
			model.addAttribute("message", "Erreur lors de la mise à jour du compte !");
			model.addAttribute("professionnel", new Professionnel());
			model.addAttribute("dossierMedicals", new ArrayList<DossierMedical>());
			model.addAttribute("module", "detailsPro");
		}
		return "detailsPro";
	}
}