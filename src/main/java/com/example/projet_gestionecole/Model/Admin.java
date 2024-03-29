package com.example.projet_gestionecole.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "administrateur")
public class Admin {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, length = 255)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 255)
    private String prenom;

    @Column(name = "email", nullable = false, length = 255)
    private String  email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "role", nullable = false, length = 255)
    private String role;

    public Admin(){

    }

    public Admin(String nom, String prenom, String email, String password,String role){
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.email = email;
        this.password = password;

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

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }


}
