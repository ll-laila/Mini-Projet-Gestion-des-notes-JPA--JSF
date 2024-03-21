package com.example.projet_gestionecole.Controller;

import com.example.projet_gestionecole.Dao.DaoImpl.EnseignantServiceImpl;
import com.example.projet_gestionecole.Dao.DaoImpl.EtudiantServiceImpl;
import com.example.projet_gestionecole.Dao.DaoImpl.ModuleServiceImpl;
import com.example.projet_gestionecole.Dao.DaoImpl.NoteServiceImpl;
import com.example.projet_gestionecole.Dao.EnseignantService;
import com.example.projet_gestionecole.Dao.EtudiantService;
import com.example.projet_gestionecole.Dao.ModuleService;
import com.example.projet_gestionecole.Dao.NoteService;
import com.example.projet_gestionecole.Model.Etudiant;
import com.example.projet_gestionecole.Model.Module;
import com.example.projet_gestionecole.Model.Note;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;



public class EnseignantBean {

    private String email;
    private int note;
    private String selectedFiliere;
    private Boolean listerEtuds = false;
    private Etudiant etudiant;
    private Module module;
    private List<Module> modules;
    private List<Etudiant> etudiants;
    EnseignantService enseignantService;
    EtudiantService etudiantService;
    ModuleService moduleService;
    NoteService noteService;




    public EnseignantBean(){

        this.modules = new ArrayList<>();
        this.etudiants = new ArrayList<>();
        this.etudiant = new Etudiant();
        this.moduleService = new ModuleServiceImpl();
        this.enseignantService = new EnseignantServiceImpl();
        this.etudiantService = new EtudiantServiceImpl();
        this.noteService = new NoteServiceImpl();

    }

    public String accueilEnseig(){
        return "GestionNotes";
    }

    public String voirModules(){
        return "GestionNotes";
    }
    public String ajoutNotes(){
        return "EtudiantsList";
    }

    public List<Module> modulesEnsig(){
        Long id = enseignantService.findEnseignantByEmail(getEmail());
        modules = moduleService.getModulesByEnseignantId(id);
        return this.modules;
    }

    public void listeEtudiantsParFiliere(){
        etudiants = etudiantService.listEtudiantsParFiliere(selectedFiliere);
        listerEtuds = true;
    }

    public String ajoutNote(Etudiant etudiant){
            this.etudiant = etudiant;
            return "AjoutNote";
    }


    public String ajoutNoteEtudiant(){
        Note noteE = new Note();
        noteE.setEtudiant(etudiant);
        noteE.setModule(module);
        noteE.setNote(note);
        this.noteService.addNote(noteE);
        return "EtudiantsList";
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

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void setSelectedFiliere(String selectedFiliere) {
        this.selectedFiliere= selectedFiliere;
    }
    public String getSelectedFiliere() {
        return selectedFiliere;
    }

    public void setListerEtuds(boolean listerEtuds) {
        this.listerEtuds= listerEtuds;
    }
    public boolean getListerEtuds() {
        return listerEtuds;
    }


    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }



}
