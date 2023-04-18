package com.ecam.picto.pictopro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EchangeController {
    @GetMapping("/echange")
    public String goEchange(Model model) {
        model.addAttribute("message", "Echanges");
        return "echange";
    }
}
