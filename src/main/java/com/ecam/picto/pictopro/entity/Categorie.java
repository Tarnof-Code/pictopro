package com.ecam.picto.pictopro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String pictoFile;
    @OneToMany(mappedBy = "categorie",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Mot> listeMotsParCategorie;
    @OneToMany(mappedBy = "categorie",fetch = FetchType.EAGER)
    private List<SousCategorie> listeSousCategorie;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date createdAt;
    @UpdateTimestamp
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date updatedAt;

    public Categorie() {
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

    public List<Mot> getListeMotsParCategorie() {
        return listeMotsParCategorie;
    }

    public void setListeMotsParCategorie(List<Mot> listeMotsParCategorie) {
        this.listeMotsParCategorie = listeMotsParCategorie;
    }

    public List<SousCategorie> getListeSousCategorie() {
        return listeSousCategorie;
    }

    public void setListeSousCategorie(List<SousCategorie> listeSousCategorie) {
        this.listeSousCategorie = listeSousCategorie;
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
        return "Categorie{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", pictoFile='" + pictoFile + '\'' +
                ", listeMotsParCategorie=" + listeMotsParCategorie +
                ", listeSousCategorie=" + listeSousCategorie +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categorie categorie = (Categorie) o;
        return id == categorie.id && nom.equals(categorie.nom) && Objects.equals(pictoFile, categorie.pictoFile) && Objects.equals(listeMotsParCategorie, categorie.listeMotsParCategorie) && Objects.equals(listeSousCategorie, categorie.listeSousCategorie) && Objects.equals(createdAt, categorie.createdAt) && Objects.equals(updatedAt, categorie.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, pictoFile, listeMotsParCategorie, listeSousCategorie, createdAt, updatedAt);
    }
}
