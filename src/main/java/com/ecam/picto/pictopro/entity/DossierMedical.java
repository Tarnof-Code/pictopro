package com.ecam.picto.pictopro.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class DossierMedical {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "* Le champ nom ne peut pas être vide.")
	@Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "* Format de caractère non autorisé")
	private String nom;

	@NotEmpty(message = "* Le champ prénom ne peut pas être vide.")
	@Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "* Format de caractère non autorisé")
	private String prenom;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "* Le champ date de naissance ne peut pas être vide.")
	private Date dateNaissance;

	private String statistique;

	private String progression;

	@OneToMany(mappedBy = "dossierMedical", fetch = FetchType.EAGER)
	private List<Phrase> listePhrasesParDossierMedical;

	@ManyToOne
	private Professionnel professionnel;

	@CreationTimestamp
	private Date createdAt;

	@CreationTimestamp
	private Date updatedAt;

	public DossierMedical() {
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getStatistique() {
		return statistique;
	}

	public void setStatistique(String statistique) {
		this.statistique = statistique;
	}

	public String getProgression() {
		return progression;
	}

	public void setProgression(String progression) {
		this.progression = progression;
	}

	public List<Phrase> getListePhrasesParDossierMedical() {
		return listePhrasesParDossierMedical;
	}

	public void setListePhrasesParDossierMedical(List<Phrase> listePhrasesParDossierMedical) {
		this.listePhrasesParDossierMedical = listePhrasesParDossierMedical;
	}

	public Professionnel getProfessionnel() {
		return professionnel;
	}

	public void setProfessionnel(Professionnel professionnel) {
		this.professionnel = professionnel;
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

	@Override
	public String toString() {
		return "DossierMedical [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance
				+ ", statistique=" + statistique + ", progression=" + progression + ", listePhrasesParDossierMedical="
				+ listePhrasesParDossierMedical + ", professionnel=" + professionnel + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

}
