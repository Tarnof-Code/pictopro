package com.ecam.picto.pictopro.controller;

import com.ecam.picto.pictopro.entity.*;
import com.ecam.picto.pictopro.repository.CategorieRepository;
import com.ecam.picto.pictopro.repository.TagRepository;
import com.ecam.picto.pictopro.service.CategorieService;
import com.ecam.picto.pictopro.service.MotService;
import com.ecam.picto.pictopro.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/gestionDesMots")
public class MotController {
@Autowired
private TagService tagService;
@Autowired
private CategorieService categorieService;
@Autowired
private MotService motService;

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

    @RequestMapping("/verbeIrregulier")
    public String affichageFormulaireVerbeIrregulier(@ModelAttribute("mot") Mot mot){
        return "/components/formulairesIrreguliers::verbeIrregulier(conjugaisons='mot.irregulier.conjugaisons',participePasse='mot.irregulier.participePasse')";
    }

    @RequestMapping("/nomIrregulier")
    public String affichageFormulaireNomIrregulier(@ModelAttribute("mot") Mot mot){
        return "/components/formulairesIrreguliers::nomIrregulier(nomPluriel='mot.irregulier.pluriel')";
    }

    @RequestMapping("/adjectifIrregulier")
    public String affichageFormulaireAdjectifIrregulier(@ModelAttribute("mot") Mot mot){
        return "/components/formulairesIrreguliers::adjectifIrregulier(feminin='mot.irregulier.feminin',adjPluriel='mot.irregulier.pluriel')";
    }




    @PostMapping("/ajouterUnMot")
    public String ajouterUnMot(@ModelAttribute("mot") Mot mot,
                               @RequestParam("categorieId") int idCat,
                               @RequestParam("sousCategorieId") int idSousCat,
                               @RequestParam("selectedTags") List<String> selectedTags){

        Categorie categorie = categorieService.findCategorieById(idCat);
        SousCategorie sousCategorie = categorieService.findSousCategorieById(idSousCat);
        List<Tag> listeTags = tagService.findAllByNomIn(selectedTags);

        mot.setCategorie(categorie);
        mot.setSousCategorie(sousCategorie);
        mot.setTags(listeTags);

        motService.ajouterUnMot(mot);

        return "redirect:/gestionDesMots";
    }
}
