package com.ecam.picto.pictopro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecam.picto.pictopro.entity.Professionnel;
import com.ecam.picto.pictopro.service.ProfessionnelService;

@Controller
@RequestMapping("/gestionDesPros")
public class ProfessionnelController {

	@Autowired
	private ProfessionnelService professionnelService;

	@RequestMapping("/pro/{id}")
	public String afficherPro(Model model, @PathVariable("id") int id) {
		Professionnel pro = professionnelService.findById(id);
		model.addAttribute("proSelection", pro);
		return "/components/consulterModifierPro";
	}

	@GetMapping("/consulterLesPros")
	public String consulterLesPros(Model model) {
		model.addAttribute("pros", professionnelService.findAll());
		model.addAttribute("module", "gestionDesPros");
		return "consulterLesPros";
	}
}