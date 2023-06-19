package com.ecam.picto.pictopro.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.ecam.picto.pictopro.entity.Categorie;
import com.ecam.picto.pictopro.entity.Mot;
import com.ecam.picto.pictopro.entity.SousCategorie;
import com.ecam.picto.pictopro.entity.Tag;
import com.ecam.picto.pictopro.service.CategorieService;
import com.ecam.picto.pictopro.service.MotService;
import com.ecam.picto.pictopro.service.TagService;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.spring6.SpringTemplateEngine;


@Controller
@RequestMapping("/gestionDesMots")
public class MotController {
@Autowired
private TagService tagService;
@Autowired
private CategorieService categorieService;
@Autowired
private MotService motService;
@Autowired
private SpringTemplateEngine templateEngine;
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

    listeCategories = categorieService.afficherCategories();
    model.addAttribute("categories",listeCategories);

    listeTags = tagService.afficherTags();
    model.addAttribute("tags",listeTags);

    Categorie selectedCategorie = new Categorie();
    model.addAttribute("selectedCategorie",selectedCategorie);

    List<Tag> listeTagsSelection = new ArrayList<>();
    model.addAttribute("listeTagsSelection",listeTagsSelection);

/*    SousCategorie selectedSousCategorie = new SousCategorie();
    model.addAttribute("selectedSousCategorie",selectedSousCategorie); */


    boolean noImage = false;
    model.addAttribute("noImage",noImage);

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
                               @Valid @ModelAttribute("mot") Mot mot,
                               BindingResult result,
                               @RequestParam("categorieId") int idCat,
                               @RequestParam("sousCategorieId") int idSousCat,
                               @RequestParam(value = "selectedTags",required = false) List<String> selectedTags,
                               @RequestParam("pictoFileImage") MultipartFile pictoFileImage ) throws IOException{

        boolean noImage = pictoFileImage.isEmpty();
        Categorie selectedCategorie = categorieService.findCategorieById(idCat);
        SousCategorie sousCategorie = categorieService.findSousCategorieById(idSousCat);
        List<Tag> listeTagsSelection = null;
        if(selectedTags != null){
            listeTagsSelection = tagService.findAllByNomIn(selectedTags);
        }

        if(result.hasErrors() || selectedCategorie == null ||
           listeTagsSelection == null || noImage){
            model.addAttribute("module", "gestionDesMots");
            model.addAttribute("categories",listeCategories);
            model.addAttribute("tags",listeTags);
            model.addAttribute("listeTagsSelection",listeTagsSelection);
            model.addAttribute("selectedCategorie",selectedCategorie);
            model.addAttribute("noImage",noImage);
            System.out.println("+++++++++++++++++++++++++++++ Erreur dans le formulaire Ajouter mot++++++++++++++++++++++++++++++");

            return "ajouterUnMot";
        }

        if (!pictoFileImage.isEmpty()) {
            try {
                String fileName = pictoFileImage.getOriginalFilename();
                mot.setPictoFile(fileName);
                byte[] bytes = pictoFileImage.getBytes();
                Path path = Paths.get("src/main/resources/static/images/Mots/"+fileName);
                Files.write(path, bytes);
                templateEngine.clearTemplateCache();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

		mot.setCategorie(selectedCategorie);
		mot.setSousCategorie(sousCategorie);
        if(listeTagsSelection != null){
            mot.setTags(listeTagsSelection);
        }

        motService.ajouterUnMot(mot);

        model.addAttribute("module","gestionDesMots");
        model.addAttribute("motAjoute", mot.getNom());

        return "ajoutMotConfirmation";
    }

    @PostMapping("/modifierUnMot")
    @ResponseBody
    public ResponseEntity<String> modifierUnMot(@ModelAttribute("mot") Mot nouveauMot,
                                                @RequestParam("categorieId") int idCat,
                                                @RequestParam("sousCategorieId") int idSousCat,
                                                @RequestParam("selectedTags") List<String> selectedTags,
                                                @RequestPart("pictoFileModifImage") MultipartFile pictoFileModifImage) throws IOException{

        Categorie categorie = categorieService.findCategorieById(idCat);
        SousCategorie sousCategorie = categorieService.findSousCategorieById(idSousCat);
        List<Tag> listeTags = tagService.findAllByNomIn(selectedTags);

        nouveauMot.setCategorie(categorie);
        nouveauMot.setSousCategorie(sousCategorie);
        nouveauMot.setTags(listeTags);

        if (!pictoFileModifImage.isEmpty()) {

            try {
                // Formatage de la date pour rendre unique le nom de fichier
                long timestamp = System.currentTimeMillis();
                LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
                String formattedDate = date.format(formatter);

                String originalFileName  = pictoFileModifImage.getOriginalFilename(); // Récupération du nom de l'image uploadé
                String extensionDuFichier = originalFileName.substring(originalFileName.lastIndexOf("."));  // Extendion du fichier
                String nomFichierSansExtension = originalFileName.substring(0, originalFileName.lastIndexOf("."));

                // Nouveau nom du fichier
                String fileName = nomFichierSansExtension + "_" + formattedDate + extensionDuFichier;

                // Assignation du nom de l'image dans l'objet Mot
                nouveauMot.setPictoFile(fileName);

                // Enregistrement de l'image dans le dossier static
                byte[] bytes = pictoFileModifImage.getBytes();
                Path path = Paths.get("src/main/resources/static/images/Mots/" + fileName);
                Files.write(path, bytes);

                // Supprimer l'ancien fichier du dossier static
                String nomAncienneImage = motSelection.getPictoFile();
                Path path2 = Paths.get("src/main/resources/static/images/Mots/" + nomAncienneImage);
                    Files.delete(path2);

                templateEngine.clearTemplateCache();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(nouveauMot.getPictoFile() == null){
            nouveauMot.setPictoFile(motSelection.getPictoFile());
        }
        motService.modifierUnMot(motSelection, nouveauMot);

        String successMessage = "Le mot '" + motSelection.getNom() + "' a été modifié avec succès. Si vous avez modifié l'image, veuillez rafraîchir le page pour voir les modifications !";
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
