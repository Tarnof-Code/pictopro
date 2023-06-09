package com.ecam.picto.pictopro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecam.picto.pictopro.entity.Phrase;

@Service
public interface PhraseService {
	void ajouterUnePhrase(Phrase phrase);

	List<Phrase> afficherPhrases();

	Phrase findPhraseById(int id);
}
