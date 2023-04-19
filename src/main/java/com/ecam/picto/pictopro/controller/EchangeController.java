package com.ecam.picto.pictopro.controller;

import com.ecam.picto.pictopro.repository.MotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EchangeController {
    @Autowired
    private MotRepository motRepository;


    @GetMapping("/echange")
    public String goEchange(Model model) {
        model.addAttribute("mots",motRepository.findAll());
        model.addAttribute("module","echange");
        return "echange";
    }
}
