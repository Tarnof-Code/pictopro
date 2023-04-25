package com.ecam.picto.pictopro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
public class SousCategorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String pictoFile;
    @ManyToOne
    private Categorie categorie;
    @OneToMany(mappedBy = "sousCategorie",fetch = FetchType.EAGER)
    private List<Mot> listeMotsParSousCategorie;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<Mot> getListeMotsParSousCategorie() {
        return listeMotsParSousCategorie;
    }

    public void setListeMotsParSousCategorie(List<Mot> listeMotsParSousCategorie) {
        this.listeMotsParSousCategorie = listeMotsParSousCategorie;
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
        return "SousCategorie{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", pictoFile='" + pictoFile + '\'' +
                ", categorie=" + categorie +
                ", listeMotsParSousCategorie=" + listeMotsParSousCategorie +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
