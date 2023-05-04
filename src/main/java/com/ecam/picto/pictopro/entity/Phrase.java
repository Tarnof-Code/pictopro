package com.ecam.picto.pictopro.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Phrase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String audio;

	private int score;

	@CreationTimestamp
	private Date createdAt;

	@ManyToOne
	private DossierMedical dossierMedical;

	@ManyToOne
	private Question question;

	public Phrase() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public DossierMedical getDossierMedical() {
		return dossierMedical;
	}

	public void setDossierMedical(DossierMedical dossierMedical) {
		this.dossierMedical = dossierMedical;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Phrase [id=" + id + ", audio=" + audio + ", score=" + score + ", createdAt=" + createdAt
				+ ", dossierMedical=" + dossierMedical + ", question=" + question + "]";
	}

}
