package com.ecam.picto.pictopro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecam.picto.pictopro.entity.Question;
import com.ecam.picto.pictopro.service.QuestionService;

@Controller
@RequestMapping("/gestionDesQuestions")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@GetMapping("/ajouterUneQuestion")
	public String goQuestion(Model model) {
		model.addAttribute("module", "gestionDesQuestions");
		model.addAttribute("message", "Page Ajouter une question");
		return "ajouterUneQuestion";
	}

	@PostMapping("/ajouterUneQuestion")
	public String ajouterUneQuestion(Model model, @ModelAttribute("question") Question question) {
		questionService.ajouterUneQuestion(question);
		model.addAttribute("module", "gestionDesQuestions");
		return "redirect:/gestionDesQuestions/ajouterUneQuestion";
	}

	@GetMapping("/consulterLesQuestions")
	public String consulterLesQuestions(Model model) {
		model.addAttribute("module", "gestionDesQuestions");
		model.addAttribute("message", "Page Liste des Questions");
		return "consulterLesQuestions";
	}
}