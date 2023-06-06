package com.ecam.picto.pictopro.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Mot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Veuillez entrer un nom !")
	@Column(unique = false, nullable = false)
	private String nom;

	@ManyToOne
	private Categorie categorie;

	@ManyToOne
	private SousCategorie sousCategorie;

	@ManyToOne
	private Professionnel professionnel;

	private String pictoFile;

	@OneToOne(cascade = CascadeType.ALL)
	private Irregulier irregulier;

	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "mot_tag", joinColumns = @JoinColumn(name = "mot_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "mot_phrase", joinColumns = @JoinColumn(name = "mot_id"), inverseJoinColumns = @JoinColumn(name = "phrase_id"))
	private List<Phrase> phrases = new ArrayList<>();

	public Mot() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPictoFile() {
		return pictoFile;
	}

	public void setPictoFile(String pictoFile) {
		this.pictoFile = pictoFile;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Phrase> getPhrases() {
		return phrases;
	}

	public void setPhrases(List<Phrase> phrases) {
		this.phrases = phrases;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public SousCategorie getSousCategorie() {
		return sousCategorie;
	}

	public void setSousCategorie(SousCategorie sousCategorie) {
		this.sousCategorie = sousCategorie;
	}

	public Irregulier getIrregulier() {
		return irregulier;
	}

	public void setIrregulier(Irregulier irregulier) {
		this.irregulier = irregulier;
	}

	public Professionnel getProfessionnel() {
		return professionnel;
	}

	public void setProfessionnel(Professionnel professionnel) {
		this.professionnel = professionnel;
	}

	@Override
	public String toString() {
		return "Mot [id=" + id + ", nom=" + nom + ", professionnel=" + professionnel + ", pictoFile=" + pictoFile
				+ ", irregulier=" + irregulier + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", tags="
				+ tags + ", phrases=" + phrases + "]";
	}

}
