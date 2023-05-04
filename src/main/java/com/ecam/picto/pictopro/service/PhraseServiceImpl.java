package com.ecam.picto.pictopro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecam.picto.pictopro.entity.Phrase;
import com.ecam.picto.pictopro.repository.PhraseRepository;

@Service
public class PhraseServiceImpl implements PhraseService {

	@Autowired
	PhraseRepository phraseRepository;

	@Override
	public void ajouterUnePhrase(Phrase phrase) {
		phraseRepository.save(phrase);
	}

	@Override
	public List<Phrase> afficherPhrases() {
		try {
			return phraseRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Phrase findPhraseById(int id) {
		try {
			Optional<Phrase> phrase = phraseRepository.findById(id);
			return phrase.orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}