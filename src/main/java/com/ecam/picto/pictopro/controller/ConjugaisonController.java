package com.ecam.picto.pictopro.controller;

import com.ecam.picto.pictopro.entity.Conjugaison;
import com.ecam.picto.pictopro.entity.Mot;
import com.ecam.picto.pictopro.service.ConjugaisonService;
import com.ecam.picto.pictopro.service.MotService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ConjugaisonController {

    @Autowired
    private MotService motService;
    @Autowired
    private ConjugaisonService conjugaisonService;
    @Autowired
    private ObjectMapper objectMapper;

 /*   @RequestMapping("/congugaisonsIrregulier/{id}")
    public String recupConjugaisonsIrregulier(@PathVariable("id") int id){

        Mot mot = motService.findById(id);
        List<Conjugaison> listeConjugaisons = conjugaisonService.findConjugaisonByMot(mot);
        return null;
    }
*/

    @RequestMapping("/conjugaisonsIrregulier/{id}")
    @ResponseBody
    public ResponseEntity<String> conjugaisonsIrregulier(@PathVariable("id") int id) throws JsonProcessingException {

        Mot mot = motService.findById(id);

        List<Conjugaison> listeConjugaisons = conjugaisonService.findConjugaisonByMot(mot);

        String json = objectMapper.writeValueAsString(listeConjugaisons);

        return ResponseEntity.ok(json);
    }
}
