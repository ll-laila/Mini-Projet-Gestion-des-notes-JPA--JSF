package com.example.projet_gestionecole.Dao;

import com.example.projet_gestionecole.Model.Enseignant;

import java.util.List;

public interface EnseignantService {
    void addEnseignant(Enseignant enseignant);
    boolean findUser(String email, String password);
    Enseignant find(Long id);
    Long findEnseignantByEmail(String email);
    Enseignant findEnseignantByCIN(String cin);
    void update(Enseignant enseignant);
    void delete(Enseignant enseignant);
    public List<Enseignant> listEnseignant();
    void close();
}
