package com.ecam.picto.pictopro.controller;

import com.ecam.picto.pictopro.entity.Categorie;
import com.ecam.picto.pictopro.entity.Mot;
import com.ecam.picto.pictopro.entity.SousCategorie;
import com.ecam.picto.pictopro.entity.Tag;
import com.ecam.picto.pictopro.repository.CategorieRepository;
import com.ecam.picto.pictopro.repository.TagRepository;
import com.ecam.picto.pictopro.service.CategorieService;
import com.ecam.picto.pictopro.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/gestionDesMots")
public class MotController {
@Autowired
private TagService tagService;
@Autowired
private CategorieService categorieService;

    @GetMapping
    public String gestionDesMots(Model model) {

    Mot motAAjouter = new Mot();
    model.addAttribute("mot",motAAjouter);

    List<Tag> listeTags = tagService.afficherTags();
    model.addAttribute("tags",listeTags);

    Categorie selectedCategorie = new Categorie();
    model.addAttribute("selectedCategorie",selectedCategorie);

    SousCategorie selectedSousCategorie = new SousCategorie();
    model.addAttribute("selectedSousCategorie",selectedSousCategorie);

    List<Categorie> listeCategories = categorieService.afficherCategories();
    model.addAttribute("categories",categorieService.afficherCategories());

        return "gestionDesMots";
    }

    @RequestMapping("/sousCategories/{id}")
    public String affichageSousCategories(Model model, @PathVariable("id") int id){

        Categorie categorie = categorieService.findCategorieById(id);
        model.addAttribute("categorie",categorie);

        SousCategorie selectedSousCategorie = new SousCategorie();
        model.addAttribute("selectedSousCategorie",selectedSousCategorie);

        if(categorie.getListeSousCategorie().size() > 0){
            return "/components/listeSousCategories::listeSousCategories";
        } else {
            return "/components/listeSousCategories::listeSousCategoriesVide";
        }

    }
}
