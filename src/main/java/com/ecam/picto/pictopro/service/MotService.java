package com.ecam.picto.pictopro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecam.picto.pictopro.entity.Mot;

@Service
public interface MotService {
	public void ajouterUnMot(Mot mot);

	public List<Mot> findAll();

	public Mot findById(int id);

}
