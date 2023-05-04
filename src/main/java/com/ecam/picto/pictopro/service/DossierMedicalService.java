package com.ecam.picto.pictopro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecam.picto.pictopro.entity.DossierMedical;

@Service
public interface DossierMedicalService {
	public void ajouterUnDossierMedical(DossierMedical dossierMedical);

	public List<DossierMedical> findAll();

	public DossierMedical findById(int id);
}