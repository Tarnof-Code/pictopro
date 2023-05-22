package com.ecam.picto.pictopro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.ecam.picto.pictopro.entity.DossierMedical;
import com.ecam.picto.pictopro.entity.Professionnel;
import com.ecam.picto.pictopro.repository.DossierMedicalRepository;
import com.ecam.picto.pictopro.repository.ProfessionnelRepository;
import com.ecam.picto.pictopro.service.ProfessionnelService;

@Controller
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

	@GetMapping
	public String consulterLesPros(Model model) {
		model.addAttribute("professionnels", professionnelService.findAll());
		model.addAttribute("module", "consulterLesPros");
		return "consulterLesPros";
	}

	@GetMapping(path = "/{id}")
	public String afficherDetailsPro(@PathVariable("id") Long id, Model model) {
		Optional<Professionnel> professionnel = this.professionnelRepository.findById(id);
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
}