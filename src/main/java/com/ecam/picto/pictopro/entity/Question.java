package com.ecam.picto.pictopro.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String contenu;

	@OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
	private List<Phrase> listePhrasesParQuestion;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "question_categorie", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "categorie_id"))

	private List<Categorie> categories = new ArrayList<>();

	@CreationTimestamp
	private Date createdAt;

	public Question() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public List<Phrase> getListePhrasesParQuestion() {
		return listePhrasesParQuestion;
	}

	public void setListePhrasesParQuestion(List<Phrase> listePhrasesParQuestion) {
		this.listePhrasesParQuestion = listePhrasesParQuestion;
	}

	public List<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", contenu=" + contenu + ", listePhrasesParQuestion=" + listePhrasesParQuestion
				+ ", categories=" + categories + ", createdAt=" + createdAt + "]";
	}

}
