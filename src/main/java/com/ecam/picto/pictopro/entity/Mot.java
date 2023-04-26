package com.ecam.picto.pictopro.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Mot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;

    @ManyToOne
    private Categorie categorie;
    @ManyToOne
    private SousCategorie sousCategorie;
    private String pictoFile;
    @OneToOne(cascade = CascadeType.ALL)
    private Irregulier irregulier;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name="mot_tag",
            joinColumns = @JoinColumn(name="mot_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<>();

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

    @Override
    public String toString() {
        return "Mot{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", pictoFile='" + pictoFile + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", tags=" + tags +
                '}';
    }
}
