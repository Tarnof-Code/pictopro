package com.ecam.picto.pictopro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecam.picto.pictopro.entity.Question;

@Service
public interface QuestionService {
	public void ajouterUneQuestion(Question question);

	public List<Question> afficherQuestions();

	public Question findQuestionById(int id);
}
