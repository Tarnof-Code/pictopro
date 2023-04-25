package com.ecam.picto.pictopro.controller;

import com.ecam.picto.pictopro.entity.Categorie;
import com.ecam.picto.pictopro.entity.Mot;
import com.ecam.picto.pictopro.repository.CategorieRepository;
import com.ecam.picto.pictopro.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MotController {
@Autowired
private TagRepository tagRepository;
@Autowired
    CategorieRepository categorieRepository;

    @GetMapping("/ajouterUnMot")
    public String goAjoutMot(Model model) {
    model.addAttribute("mot",new Mot());
    model.addAttribute("tags",tagRepository.findAll());
    model.addAttribute("selectedCategorie",new Categorie());
    model.addAttribute("categories",categorieRepository.findAll());
        return "ajoutMot";
    }
}
