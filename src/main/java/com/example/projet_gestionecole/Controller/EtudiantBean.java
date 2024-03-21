package com.example.projet_gestionecole.Controller;

import com.example.projet_gestionecole.Dao.DaoImpl.EtudiantServiceImpl;
import com.example.projet_gestionecole.Dao.EtudiantService;
import com.example.projet_gestionecole.Model.Etudiant;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

public class EtudiantBean {

    private String email;
    EtudiantService etudiantService;




    public EtudiantBean(){
        this.etudiantService = new EtudiantServiceImpl();

    }

    public List<String> listeInfoEtudiant(){
        List<String> infos = new ArrayList<>();
        Etudiant etudiant = etudiantService.findEtudiantByEmail(getEmail());
        infos.add("Numéro Aplogue   :" + etudiant.getNumAplogue());
        infos.add("Nom   :" + etudiant.getNom());
        infos.add("Prénom   :" + etudiant.getPrenom());
        infos.add("CIN   :" + etudiant.getCin());
        infos.add("Adresse   :" + etudiant.getAdresse());
        infos.add("Téléphone   :" + etudiant.getTelephone());
        infos.add("Email   :" + etudiant.getEmail());
        infos.add("Filière   :" + etudiant.getFiliere());
        return infos;
    }


    public void notesEtudiant(){


    }



    // Getters et Setters

    public String getEmail() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        if (session != null) {
            email = (String) session.getAttribute("email");
        }
        return email;
    }

    public void setEmail(String email) {
        this.email= email;
    }
}
