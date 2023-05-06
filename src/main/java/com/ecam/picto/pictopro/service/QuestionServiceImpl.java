package com.ecam.picto.pictopro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecam.picto.pictopro.entity.Question;
import com.ecam.picto.pictopro.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionRepository questionRepository;

	@Override
	public void ajouterUneQuestion(Question question) {
		questionRepository.save(question);
	}

	@Override
	public List<Question> afficherQuestions() {
		try {
			return questionRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Question findQuestionById(int id) {
		try {
			Optional<Question> question = questionRepository.findById(id);
			return question.orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}