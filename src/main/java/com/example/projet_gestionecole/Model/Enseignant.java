package com.example.projet_gestionecole.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "enseignant")
public class Enseignant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, length = 255)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 255)
    private String prenom;

    @Column(name = "cin", nullable = false, length = 255)
    private String cin;

    @Column(name = "adresse", nullable = false, length = 255)
    private String adresse;

    @Column(name = "telephone", nullable = false, length = 255)
    private String telephone;

    @Column(name = "email", nullable = false, length = 255)
    private String  email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;


    @Column(name = "specialite", nullable = false, length = 255)
    private String specialite;

    @OneToMany(mappedBy = "enseignant")
    private List<Module> modules;


    public Enseignant(){

    }

    public Enseignant(String nom, String prenom, String cin, String adresse, String telephone, String email, String password,String specialite){
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.specialite = specialite;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setNom(String nom){
        this.nom=nom;
    }

    public String getNom(){
        return this.nom;
    }

    public void setPrenom(String prenom){
        this.prenom=prenom;
    }

    public String getPrenom(){
        return this.prenom;
    }

    public void setCin(String cin){
        this.cin=cin;
    }

    public String getCin(){
        return this.cin;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getPassword(){
        return this.password;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public List<Module> getModules(){
        return this.modules;
    };

    public void setModules(List<Module> modules){
        this.modules = modules;
    };
}
