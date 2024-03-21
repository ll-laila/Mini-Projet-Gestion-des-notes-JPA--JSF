package com.example.projet_gestionecole.Dao;

import com.example.projet_gestionecole.Model.Etudiant;

import java.util.List;

public interface EtudiantService {
    void addEtudiant(Etudiant etudiant);
    boolean findUser(String email, String password);
    Etudiant find(Long id);
    Etudiant findByNumAplogue(String numAplogue);
    Etudiant findEtudiantByEmail(String email);
    void update(Etudiant etudiant);
    void delete(Etudiant etudiant);
    public List<Etudiant> listEtudiants();
    List<Etudiant> listEtudiantsParFiliere(String filiere);
    void close();
}
