package com.example.projet_gestionecole.Model;

import com.example.projet_gestionecole.Model.Enseignant;

import jakarta.persistence.*;

@Entity
@Table(name = "modules")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom", nullable = false, length = 255)
    private String nom;

    @Column(name = "semestre", nullable = false, length = 255)
    private String semestre;

    @Column(name = "filiere", nullable = false, length = 255)
    private String filiere;

    @ManyToOne
    @JoinColumn(name = "enseignant_id")
    private Enseignant enseignant;


    public Module() {
    }

    public Module(String nom, String semestre, String filiere,Enseignant enseignant) {
        this.nom = nom;
        this.semestre = semestre;
        this.filiere = filiere;
        this.enseignant = enseignant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }


    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }
}
