package com.ecam.picto.pictopro.payload.response;

import java.util.Date;
import java.util.List;

public class UserInfoResponse {
    private Long id;
    private String username;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String service;
    private String email;
    private String telephone;
    private List<String> roles;

    public UserInfoResponse(Long id, String username, String nom, String prenom, Date dateNaissance, String service, String email, String telephone, List<String> roles) {
        this.id = id;
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = String.valueOf(dateNaissance);
        this.service = service;
        this.email = email;
        this.telephone = telephone;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
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

    public List<String> getRoles() {
        return roles;
    }
}
