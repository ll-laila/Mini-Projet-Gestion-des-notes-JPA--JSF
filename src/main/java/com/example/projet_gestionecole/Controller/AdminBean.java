package com.example.projet_gestionecole.Controller;

import com.example.projet_gestionecole.Dao.ModuleService;
import com.example.projet_gestionecole.Dao.DaoImpl.EnseignantServiceImpl;
import com.example.projet_gestionecole.Dao.DaoImpl.EtudiantServiceImpl;
import com.example.projet_gestionecole.Dao.DaoImpl.ModuleServiceImpl;
import com.example.projet_gestionecole.Dao.EnseignantService;
import com.example.projet_gestionecole.Dao.EtudiantService;
import com.example.projet_gestionecole.Model.Enseignant;
import com.example.projet_gestionecole.Model.Etudiant;
import com.example.projet_gestionecole.Model.Module;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;


import java.util.ArrayList;
import java.util.List;


@RequestScoped
@ManagedBean
public class AdminBean {

    private Etudiant nvEtudiant;
    private Etudiant editEtudiant;
    private List<Etudiant> etudiants;
    private Enseignant editEnseignant;
    private Enseignant nvEnseignant;
    private List<Enseignant> enseignants;
    private Module editModule;
    private Module nvModule;
    private List<Module> modules;
    private String cin;

    private boolean editFormEtud = false;
    private boolean editFormEnsig = false;
    private boolean editFormMod = false;


    private String page;

    EtudiantService etudiantService;
    EnseignantService enseignantService;
    ModuleService moduleService;

    public AdminBean(){
        this.etudiants = new ArrayList<>();
        this.enseignants = new ArrayList<>();
        this.nvEtudiant = new Etudiant();
        this.editEtudiant = new Etudiant();
        this.nvModule = new Module();
        this.editModule = new Module();
        this.nvEnseignant = new Enseignant();
        this.editEnseignant = new Enseignant();
        this.etudiantService = new EtudiantServiceImpl();
        this.enseignantService = new EnseignantServiceImpl();
        this.moduleService = new ModuleServiceImpl();
        this.page = "ListEtudiants";
    }


    // Methodes

    public String goToStudents() {
       return "GestionEtudiants";
    }
    public String goToTeachers() {
        return "GestionEnseignants";
    }
    public String goToModules() {
       return "GestionModules";
    }


    public List<Etudiant> listerEtudiants(){
         this.etudiants = etudiantService.listEtudiants();
         return this.etudiants;
    }

    public List<Enseignant> listEnseignants() {
        enseignants = enseignantService.listEnseignant();
        return enseignants;
    }

    public List<Module> listModule() {
        modules = moduleService.listModules();
        return modules;
    }


    public String ajoutPageEtud(){
        return "AjoutEtudiant";
    }

    public String ajoutPageEnsig(){
        return "AjoutEnseignant";
    }


    public String ajoutPageModule(){
        return "AjoutModule";
    }



    // etudiant
    public void EditPageEtud(Etudiant etudiant){
        editFormEtud = true;
        editEtudiant = new Etudiant();
        this.editEtudiant.setId(etudiant.getId());
        this.editEtudiant.setNom(etudiant.getNom());
        this.editEtudiant.setPrenom(etudiant.getPrenom());
        this.editEtudiant.setCin(etudiant.getCin());
        this.editEtudiant.setAdresse(etudiant.getAdresse());
        this.editEtudiant.setTelephone(etudiant.getTelephone());
        this.editEtudiant.setEmail(etudiant.getEmail());
        this.editEtudiant.setPassword(etudiant.getPassword());
        this.editEtudiant.setNumAplogue(etudiant.getNumAplogue());
        this.editEtudiant.setFiliere(etudiant.getFiliere());
        editFormEtud = true;
    }
    public void editetudiant(){
        etudiantService.update(editEtudiant);
        this.etudiants = etudiantService.listEtudiants();
        editFormEtud = false;
    }

    public String ajouterEtudiant() {
        this.etudiantService.addEtudiant(nvEtudiant);
        this.etudiants = etudiantService.listEtudiants();
        return "Admin";
    }

    public void supprimerEtud(Etudiant etudiant){
        this.etudiantService.delete(etudiant);
        this.etudiants = etudiantService.listEtudiants();
    }


    // enseignant
    public void EditPageEnsg(Enseignant enseignant) {
        editFormEnsig = true;
        editEnseignant = new Enseignant();
        this.editEnseignant.setId(enseignant.getId());
        this.editEnseignant.setNom(enseignant.getNom());
        this.editEnseignant.setPrenom(enseignant.getPrenom());
        this.editEnseignant.setCin(enseignant.getCin());
        this.editEnseignant.setAdresse(enseignant.getAdresse());
        this.editEnseignant.setTelephone(enseignant.getTelephone());
        this.editEnseignant.setEmail(enseignant.getEmail());
        this.editEnseignant.setPassword(enseignant.getPassword());
        this.editEnseignant.setSpecialite(enseignant.getSpecialite());
    }

    public void editEnseignant(){
        this.enseignantService.update(editEnseignant);
        enseignants = enseignantService.listEnseignant();
        editFormEtud = false;
    }

    public String ajouterEnseignant() {
        this.enseignantService.addEnseignant(nvEnseignant);
        enseignants = enseignantService.listEnseignant();
        return "Admin";
    }

    public void supprimerEnseig(Enseignant enseignant) {
        enseignantService.delete(enseignant);
        enseignants = enseignantService.listEnseignant();
    }

    // module
    public void EditPageModule(Module module){
        editFormMod = true;
        editModule = new Module();
        this.editModule.setNom(module.getNom());
        this.editModule.setSemestre(module.getSemestre());
        this.editModule.setFiliere(module.getFiliere());
        this.editModule.setEnseignant(module.getEnseignant());
    }
    public void editModule(){
        this.moduleService.update(this.editModule);
        this.modules = moduleService.listModules();
        editFormMod = false;
    }

    public String ajouterModule() {

        Enseignant enseignant = enseignantService.findEnseignantByCIN(this.cin);
        nvModule.setEnseignant(enseignant);

        this.moduleService.addModule(this.nvModule);
        this.modules = moduleService.listModules();
        return "GestionModules";

    }

    public void supprimerModule(Module module){
        this.moduleService.delete(module);
        this.modules = moduleService.listModules();
    }





    // Setters and Getters

    public Etudiant getNvEtudiant() {
        return this.nvEtudiant;
    }
    public void setNvEtudiant(Etudiant nvEtudiant) {
        this.nvEtudiant = nvEtudiant;
    }

    public Etudiant getEditEtudiant() {
        return this.editEtudiant;
    }

    public void setEditEtudiant(Etudiant editEtudiant) {
        this.editEtudiant = editEtudiant;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public Enseignant getEditEnseignant() {
        return editEnseignant;
    }

    public void setEditEnseignant(Enseignant editEnseignant) {
        this.editEnseignant = editEnseignant;
    }

    public Enseignant getNvEnseignant() {
        return nvEnseignant;
    }

    public void setNvEnseignant(Enseignant nvEnseignant) {
        this.nvEnseignant = nvEnseignant;
    }

    public List<Enseignant> getEnseignants() {
        return enseignants;
    }
    public void setEnseignants(List<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }

    public Module getEditModule() {
        return editModule;
    }

    public void setEditModule(Module editModule) {
        this.editModule = editModule;
    }

    public Module getNvModule() {
        return nvModule;
    }

    public void setNvModule(Module nvModule) {
        this.nvModule = nvModule;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public boolean getEditFormEtud() {
        return this.editFormEtud;
    }
    public void setEditFormEtud(boolean editFormEtud) {
        this.editFormEtud = editFormEtud;
    }
    public boolean getEditFormEnsig() {
        return this.editFormEnsig;
    }
    public void setEditFormEnsig(boolean editFormEnsig) {
        this.editFormEnsig = editFormEnsig;
    }

    public boolean getEditFormMod() {
        return this.editFormMod;
    }
    public void setEditFormMod(boolean editFormMod) {
        this.editFormMod = editFormMod;
    }

    public String getPage() {
        return this.page;
    }
    public void setPage(String page) {
        this.page = page;
    }

    public String getCin() {
        return this.cin;
    }
    public void setCin(String cin) {
        this.cin = cin;
    }

}
