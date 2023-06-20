package com.ecam.picto.pictopro.controller;

import com.ecam.picto.pictopro.entity.Categorie;
import com.ecam.picto.pictopro.entity.DossierMedical;
import com.ecam.picto.pictopro.entity.Phrase;
import com.ecam.picto.pictopro.repository.CategorieRepository;
import com.ecam.picto.pictopro.repository.DossierMedicalRepository;
import com.ecam.picto.pictopro.repository.PhraseRepository;
import com.ecam.picto.pictopro.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ecam.picto.pictopro.entity.Question;
import com.ecam.picto.pictopro.service.QuestionService;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/gestionDesQuestions")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	private final QuestionRepository questionRepository;
	private final CategorieRepository categorieRepository;
	public QuestionController(QuestionRepository questionRepository, CategorieRepository categorieRepository) {
		this.questionRepository = questionRepository;
		this.categorieRepository  = categorieRepository;
	}

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
		model.addAttribute("questions", questionService.afficherQuestions());
		model.addAttribute("module", "gestionDesQuestions");
		return "consulterLesQuestions";
	}

	@GetMapping("/consulterLesQuestions/{id}")
	public String afficherDetailsQuestion(@PathVariable("id") int id, Model model) {
		Optional<Question> question = this.questionRepository.findById(id);
		if (question.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
		}
		model.addAttribute("question", question.get());
		List<Categorie> categories = question.get().getCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("module", "gestionDesQuestions");
		return "detailsQuestion";
	}

}