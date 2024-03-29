package com.example.projet_gestionecole.Controller;

import com.example.projet_gestionecole.Dao.DaoImpl.AdminServiceImpl;
import com.example.projet_gestionecole.Dao.DaoImpl.EnseignantServiceImpl;
import com.example.projet_gestionecole.Dao.DaoImpl.EtudiantServiceImpl;
import com.example.projet_gestionecole.Model.Admin;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;


public class LoginBean {

    private Admin nvAdmin;
    private String email;
    private String password;
    private AdminServiceImpl adminService;
    private EtudiantServiceImpl etudiantService;
    private EnseignantServiceImpl enseignantService;

    public LoginBean(){
        this.nvAdmin = new Admin();
        adminService = new AdminServiceImpl();
        etudiantService = new EtudiantServiceImpl();
        enseignantService = new EnseignantServiceImpl();
    }

    public Admin getNvAdmin() {
        return nvAdmin;
    }

    public void setNvAdmin(Admin nvAdmin) {
        this.nvAdmin = nvAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String login() {
        boolean isAdmin = adminService.findUser(email,password);
        boolean isStudent = etudiantService.findUser(email,password);
        boolean isTeacher = enseignantService.findUser(email,password);

        if(email.isEmpty() || password.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("username and password required"));
            return null ;
        }

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        session.setAttribute("email", email);


        if(isAdmin){
            return  "Admin";
        }else if(isStudent){
            return "Etudiant";
        }else if(isTeacher){
            return "Enseig";
        }else{
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("username or password not correct"));
            return null ;
        }
    }


    public String Inscrire(){
        this.adminService.addAdmin(nvAdmin);
        return "Admin";
    }


    public String logout(){
        email = null;
        password  = null;
        return "auth";
    }





}
