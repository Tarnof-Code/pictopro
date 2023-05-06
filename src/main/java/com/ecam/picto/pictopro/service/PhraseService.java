package com.ecam.picto.pictopro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecam.picto.pictopro.entity.Phrase;

@Service
public interface PhraseService {
	public void ajouterUnePhrase(Phrase phrase);

	public List<Phrase> afficherPhrases();

	public Phrase findPhraseById(int id);
}
