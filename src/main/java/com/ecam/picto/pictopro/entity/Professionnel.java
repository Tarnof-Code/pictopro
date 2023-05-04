package com.ecam.picto.pictopro.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Professionnel {
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

	@Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "* Format de caractère non autorisé")
	private String service;

	@NotEmpty(message = "* Le champ numéro de tél. ne peut pas être vide")
	private String telephone;

	@Column(unique = true)
	@NotEmpty(message = "* Le champ email ne peut pas être vide.")
	private String email;

	@Column(nullable = false)
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$", message = "* Le mot de passe doit contenir au moins une majuscule, une minuscule, un chiffre, un caractère spécial et comporter au moins 8 caractères")
	private String password;

	@OneToMany(mappedBy = "professionnel", fetch = FetchType.EAGER)
	private List<DossierMedical> listeDossiersParProfessionnel;

	@OneToMany(mappedBy = "professionnel", fetch = FetchType.EAGER)
	private List<Mot> listeMotsParProfessionnel;

//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@Temporal(TemporalType.DATE)
//	@Column(nullable = false)
	@CreationTimestamp
	private Date createdAt;

	@Transient
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$", message = "* Le mot de passe de confirmation doit contenir au moins une majuscule, une minuscule, un chiffre, un caractère spécial et comporter au moins 8 caractères")
	private String confirmPassword;

	public Professionnel() {
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

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<DossierMedical> getListeDossiersParProfessionnel() {
		return listeDossiersParProfessionnel;
	}

	public void setListeDossiersParProfessionnel(List<DossierMedical> listeDossiersParProfessionnel) {
		this.listeDossiersParProfessionnel = listeDossiersParProfessionnel;
	}

	public List<Mot> getListeMotsParProfessionnel() {
		return listeMotsParProfessionnel;
	}

	public void setListeMotsParProfessionnel(List<Mot> listeMotsParProfessionnel) {
		this.listeMotsParProfessionnel = listeMotsParProfessionnel;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "Professionnel [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance
				+ ", service=" + service + ", telephone=" + telephone + ", email=" + email + ", password=" + password
				+ ", listeDossiersParProfessionnel=" + listeDossiersParProfessionnel + ", listeMotsParProfessionnel="
				+ listeMotsParProfessionnel + ", createdAt=" + createdAt + ", confirmPassword=" + confirmPassword + "]";
	}

}