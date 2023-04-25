package com.ecam.picto.pictopro.controller;

import com.ecam.picto.pictopro.entity.Mot;
import com.ecam.picto.pictopro.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AjoutMotController {
@Autowired
private TagRepository tagRepository;

    @GetMapping("/ajouterUnMot")
    public String goAjoutMot(Model model) {
    model.addAttribute("mot",new Mot());
    model.addAttribute("tags",tagRepository.findAll());
        return "ajoutMot";
    }
}
