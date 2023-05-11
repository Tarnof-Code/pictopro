package com.ecam.picto.pictopro.controller;

import com.ecam.picto.pictopro.entity.Categorie;
import com.ecam.picto.pictopro.entity.SousCategorie;
import com.ecam.picto.pictopro.repository.MotRepository;
import com.ecam.picto.pictopro.service.CategorieService;
import com.ecam.picto.pictopro.service.MotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/listeMotsCat/{id}")
    public String listeParCategorie(Model model, @PathVariable("id") int id){

        Categorie categorie = categorieService.findCategorieById(id);
        model.addAttribute("categorie",categorie);

        if(categorie.getListeMotsParCategorie().size() > 0 && categorie.getListeSousCategorie().size() == 0){
            return "/components/listesParCategorie::motItemsCat";
        } else {
            return "/components/listesParCategorie::motItemsVide";
        }
    }

    @RequestMapping("/listeMotsSousCat/{id}")
    public  String listeParSousCategorie(Model model, @PathVariable("id") int id){

        SousCategorie sousCategorie = categorieService.findSousCategorieById(id);
        model.addAttribute("sousCategorie",sousCategorie);

        if(sousCategorie.getListeMotsParSousCategorie().size() > 0) {
            return "/components/listesParCategorie::motItemsSousCat";
        } else {
            return "/components/listesParCategorie::motItemsVide";
        }
    }
}
