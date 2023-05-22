package com.ecam.picto.pictopro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecam.picto.pictopro.entity.Professionnel;

@Service
public interface ProfessionnelService {
	public void ajouterUnPro(Professionnel professionnel);

	public List<Professionnel> findAll();

	public Professionnel findById(Long id);
}
