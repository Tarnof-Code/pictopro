package com.ecam.picto.pictopro.controller;

import com.ecam.picto.pictopro.repository.MotRepository;
import com.ecam.picto.pictopro.service.CategorieService;
import com.ecam.picto.pictopro.service.MotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EchangeController {
    @Autowired
    private MotService motService;
    @Autowired
    private CategorieService categorieService;


    @GetMapping("/echange")
    public String goEchange(Model model) {
        model.addAttribute("categories",categorieService.afficherCategories());
        model.addAttribute("mots",motService.findAll());
        model.addAttribute("module","echange");
        return "echange";
    }
}
