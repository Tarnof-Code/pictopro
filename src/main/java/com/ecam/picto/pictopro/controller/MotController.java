package com.ecam.picto.pictopro.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.ecam.picto.pictopro.entity.Categorie;
import com.ecam.picto.pictopro.entity.Mot;
import com.ecam.picto.pictopro.entity.SousCategorie;
import com.ecam.picto.pictopro.entity.Tag;
import com.ecam.picto.pictopro.service.CategorieService;
import com.ecam.picto.pictopro.service.MotService;
import com.ecam.picto.pictopro.service.TagService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/gestionDesMots")
public class MotController {
@Autowired
private TagService tagService;
@Autowired
private CategorieService categorieService;
@Autowired
private MotService motService;

private List<Categorie> listeCategories = new ArrayList<>();
private List<Tag> listeTags = new ArrayList<>();
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
    public String affichageSousCategories(Model model, 
                                          HttpServletRequest request,
                                          @PathVariable("id") int id){

        Categorie categorie = categorieService.findCategorieById(id);
        model.addAttribute("categorie",categorie);

        SousCategorie selectedSousCategorie = new SousCategorie();
        model.addAttribute("selectedSousCategorie",selectedSousCategorie);

        String referer = request.getHeader("referer");
        
        if(referer.contains("ajouterUnMot") || referer.contains("consulterLesMots")){
            if(categorie.getListeSousCategorie().size() > 0){
                return "/components/listesParCategorie::dropdownSousCategories";
            } else {
                return "/components/listesParCategorie::dropdownSousCategoriesVide";
            }
        }

        if (referer.contains("echange")) {
            if(categorie.getListeSousCategorie().size() > 0){
                return "/components/listesParCategorie::sousCategorieItems";
            } else {
                return "/components/listesParCategorie::sousCategorieItemsVide";
            }
        } else {
            return null;
        }


    }

    @RequestMapping("/mot/{id}")
    public String afficherMot(Model model, @PathVariable("id") int id){

        Mot mot = motService.findById(id);
        model.addAttribute("mot",mot);
        motSelection = mot;

        model.addAttribute("irregulier",mot.getIrregulier());

        return "/components/infosMots";
    }

    @RequestMapping("/infosMot")
    public  String afficherInfosMot(Model model) {

        model.addAttribute("irregulier",motSelection.getIrregulier());
        model.addAttribute("mot", motSelection);
        model.addAttribute("categories", listeCategories);
        model.addAttribute("tags",listeTags);

        return "/components/formulaireModifierMot";

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
                               @RequestParam("selectedTags") List<String> selectedTags,
                               @RequestParam("pictoFileImage") MultipartFile pictoFileImage ) throws IOException{

		Categorie categorie = categorieService.findCategorieById(idCat);
		SousCategorie sousCategorie = categorieService.findSousCategorieById(idSousCat);
		List<Tag> listeTags = tagService.findAllByNomIn(selectedTags);

        if (!pictoFileImage.isEmpty()) {
            try {
                String fileName = pictoFileImage.getOriginalFilename();
                mot.setPictoFile(fileName);
                byte[] bytes = pictoFileImage.getBytes();
                Path path = Paths.get("src/main/resources/static/images/Mots/"+fileName);
                Files.write(path, bytes);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

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
        listeCategories = categorieService.afficherCategories();
        listeTags = tagService.afficherTags();

        model.addAttribute("categories",listeCategories);
        model.addAttribute("mots", motService.findAll());
        model.addAttribute("module","gestionDesMots");
        return "consulterLesMots";
    }
}
