package com.ecam.picto.pictopro.entity;

import java.util.*;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class Professionnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(min = 6, max = 32)
    @Column(unique = true, nullable = false)
    @NotEmpty(message = "* Le champ pseudonyme ne peut pas être vide.")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ0-9]+(([',.-][a-zA-ZÀ-ÿ0-9])?[a-zA-ZÀ-ÿ0-9])$", message = "* Format de caractère non autorisé")
    private String username;

    @NotEmpty(message = "* Le champ nom ne peut pas être vide.")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ]+(([',. -][a-zA-ZÀ-ÿ ])?[a-zA-ZÀ-ÿ]*)*$", message = "* Format de caractère non autorisé")
    private String nom;

    @NotEmpty(message = "* Le champ prénom ne peut pas être vide.")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ]+(([',. -][a-zA-ZÀ-ÿ ])?[a-zA-ZÀ-ÿ]*)*$", message = "* Format de caractère non autorisé")
    private String prenom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "* Le champ date de naissance ne peut pas être vide.")
    private Date dateNaissance;

    @Pattern(regexp = "^[a-zA-ZÀ-ÿ0-9]+(([',. -][a-zA-ZÀ-ÿ0-9 ])?[a-zA-ZÀ-ÿ0-9]*)*$", message = "* Format de caractère non autorisé")
    private String service;

    @NotEmpty(message = "* Le champ numéro de tél. ne peut pas être vide")
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$", message = "* Format de caractère non autorisé")
    private String telephone;

    @Column(unique = true, nullable = false)
    @NotEmpty(message = "* Le champ email ne peut pas être vide.")
    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$", message = "* Format de caractère non autorisé")
    private String email;

    @Column(nullable = false)
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$", message = "* Le mot de passe doit contenir au moins une majuscule, une minuscule, un chiffre, un caractère spécial et comporter au moins 8 caractères")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

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

//    public Professionnel(String username, String password, String nom, String prenom, Date dateNaissance, String service, String email, String telephone) {
//        this.username = username;
//        this.password = password;
//        this.nom = nom;
//        this.prenom = prenom;
//        this.dateNaissance = dateNaissance;
//        this.service = service;
//        this.email = email;
//        this.telephone = telephone;
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
        return "Professionnel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", service='" + service + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + role +
                ", listeDossiersParProfessionnel=" + listeDossiersParProfessionnel +
                ", listeMotsParProfessionnel=" + listeMotsParProfessionnel +
                ", createdAt=" + createdAt +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}