package com.ecam.picto.pictopro.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Irregulier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String participePasse;
    @JoinColumn(name="irregulier_id")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Conjugaison> conjugaisons;
    private String pluriel;
    private String feminin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParticipePasse() {
        return participePasse;
    }

    public void setParticipePasse(String participePasse) {
        this.participePasse = participePasse;
    }

    public List<Conjugaison> getConjugaisons() {
        return conjugaisons;
    }

    public void setConjugaisons(List<Conjugaison> conjugaisons) {
        this.conjugaisons = conjugaisons;
    }

    public String getPluriel() {
        return pluriel;
    }

    public void setPluriel(String pluriel) {
        this.pluriel = pluriel;
    }

    public String getFeminin() {
        return feminin;
    }

    public void setFeminin(String feminin) {
        this.feminin = feminin;
    }

    @Override
    public String toString() {
        return "Irregulier{" +
                "id=" + id +
                ", participePasse='" + participePasse + '\'' +
                ", conjugaisons=" + conjugaisons +
                ", pluriel='" + pluriel + '\'' +
                ", feminin='" + feminin + '\'' +
                '}';
    }
}
