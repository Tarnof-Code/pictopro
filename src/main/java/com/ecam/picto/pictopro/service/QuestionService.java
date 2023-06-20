package com.ecam.picto.pictopro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecam.picto.pictopro.entity.Question;


public interface QuestionService {
	void ajouterUneQuestion(Question question);

	List<Question> afficherQuestions();

	Question findQuestionById(int id);
}
