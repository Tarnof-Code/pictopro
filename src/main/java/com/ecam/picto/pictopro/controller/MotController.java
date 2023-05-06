package com.ecam.picto.pictopro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ecam.picto.pictopro.entity.Categorie;
import com.ecam.picto.pictopro.entity.Mot;
import com.ecam.picto.pictopro.entity.SousCategorie;
import com.ecam.picto.pictopro.entity.Tag;
import com.ecam.picto.pictopro.service.CategorieService;
import com.ecam.picto.pictopro.service.MotService;
import com.ecam.picto.pictopro.service.TagService;

@Controller
@RequestMapping("/gestionDesMots")
public class MotController {
@Autowired
private TagService tagService;
@Autowired
private CategorieService categorieService;
@Autowired
private MotService motService;
private Mot motSelection = new Mot();

    @GetMapping("/ajouterUnMot")
    public String ajouterUnMot(Model model) {

		model.addAttribute("module", "gestionDesMots");
		model.addAttribute("mots", motService.findAll());

    Mot motAAjouter = new Mot();
    motSelection = motAAjouter;
    model.addAttribute("mot",motAAjouter);

    List<Tag> listeTags = tagService.afficherTags();
    model.addAttribute("tags",listeTags);

    Categorie selectedCategorie = new Categorie();
    model.addAttribute("selectedCategorie",selectedCategorie);

    SousCategorie selectedSousCategorie = new SousCategorie();
    model.addAttribute("selectedSousCategorie",selectedSousCategorie);

    List<Categorie> listeCategories = categorieService.afficherCategories();
    model.addAttribute("categories",categorieService.afficherCategories());

        return "ajouterUnMot";
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

    @RequestMapping("/mot/{id}")
    public String afficherMot(Model model, @PathVariable("id") int id){

        Mot mot = motService.findById(id);
        model.addAttribute("mot",mot);
        motSelection = mot;
        model.addAttribute("irregulier",mot.getIrregulier());

        List<Categorie> listeCategories = categorieService.afficherCategories();
        model.addAttribute("categories",categorieService.afficherCategories());

        List<Tag> listeTags = tagService.afficherTags();
        model.addAttribute("tags",listeTags);

        return "/components/infosMots";
    }

    @RequestMapping("/verbeIrregulier")
    public String affichageFormulaireVerbeIrregulier(Model model){
            model.addAttribute("mot", motSelection);
        return "/components/formulairesIrreguliers::verbeIrregulier(conjugaisons='mot.irregulier.conjugaisons',participePasse='mot.irregulier.participePasse')";
    }

    @RequestMapping("/nomIrregulier")
    public String affichageFormulaireNomIrregulier(Model model){
        model.addAttribute("mot", motSelection);
        return "/components/formulairesIrreguliers::nomIrregulier(nomPluriel='mot.irregulier.pluriel')";
    }

    @RequestMapping("/adjectifIrregulier")
    public String affichageFormulaireAdjectifIrregulier(Model model){
        model.addAttribute("mot", motSelection);
        return "/components/formulairesIrreguliers::adjectifIrregulier(feminin='mot.irregulier.feminin',adjPluriel='mot.irregulier.pluriel')";
    }

	@PostMapping("/ajouterUnMot")
	public String ajouterUnMot(Model model,
                               @ModelAttribute("mot") Mot mot,
                               @RequestParam("categorieId") int idCat,
                               @RequestParam("sousCategorieId") int idSousCat,
                               @RequestParam("selectedTags") List<String> selectedTags) {

		Categorie categorie = categorieService.findCategorieById(idCat);
		SousCategorie sousCategorie = categorieService.findSousCategorieById(idSousCat);
		List<Tag> listeTags = tagService.findAllByNomIn(selectedTags);

		mot.setCategorie(categorie);
		mot.setSousCategorie(sousCategorie);
		mot.setTags(listeTags);

        motService.ajouterUnMot(mot);

        model.addAttribute("module","gestionDesMots");

        return "redirect:/gestionDesMots/ajouterUnMot";
    }

@PostMapping("/modifierUnMot")
@ResponseBody
public ResponseEntity<String> modifierUnMot(@ModelAttribute("mot") Mot nouveauMot,
                                         @RequestParam("categorieId") int idCat,
                                         @RequestParam("sousCategorieId") int idSousCat,
                                         @RequestParam("selectedTags") List<String> selectedTags){

    Categorie categorie = categorieService.findCategorieById(idCat);
    SousCategorie sousCategorie = categorieService.findSousCategorieById(idSousCat);
    List<Tag> listeTags = tagService.findAllByNomIn(selectedTags);

    nouveauMot.setCategorie(categorie);
    nouveauMot.setSousCategorie(sousCategorie);
    nouveauMot.setTags(listeTags);

    motService.modifierUnMot(motSelection,nouveauMot);

    String successMessage = "Le mot '" + motSelection.getNom() + "' a été modifié avec succès";
    String response = motSelection.getId() + ":" + successMessage;

    return ResponseEntity.ok(response);
}



    @GetMapping("/consulterLesMots")
    public String consulterLesMots(Model model){
        model.addAttribute("categories",categorieService.afficherCategories());
        model.addAttribute("mots", motService.findAll());
        model.addAttribute("module","gestionDesMots");
        return "consulterLesMots";
    }
}
