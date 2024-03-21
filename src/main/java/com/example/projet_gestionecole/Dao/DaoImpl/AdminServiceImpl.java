package com.example.projet_gestionecole.Dao.DaoImpl;

import com.example.projet_gestionecole.Dao.AdminService;
import com.example.projet_gestionecole.Model.Admin;
import jakarta.persistence.*;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public AdminServiceImpl() {
        this.emf = Persistence.createEntityManagerFactory("persis");
        this.entityManager = this.emf.createEntityManager();
    }

    public AdminServiceImpl(String pu) {
        this.emf = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.emf.createEntityManager();
    }

    @Override
    public Admin addAdmin(Admin admin) {
        entityManager.getTransaction().begin();
        entityManager.persist(admin);
        entityManager.getTransaction().commit();
        return admin;
    }

    @Override
    public boolean findUser(String email, String password) {
        Query query = entityManager.createQuery("SELECT a FROM Admin a WHERE a.email = :email AND a.password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        try {
            Admin user = (Admin) query.getSingleResult();
            return user != null;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public Admin find(Long id) {
        return entityManager.find(Admin.class, id);
    }

    @Override
    public Admin update(Admin admin) {
        entityManager.getTransaction().begin();
        Admin adminModifier = find(admin.getId());
        adminModifier.setNom(admin.getNom());
        adminModifier.setPrenom(admin.getPrenom());
        adminModifier.setEmail(admin.getEmail());
        adminModifier.setPassword(admin.getPassword());
        adminModifier.setRole(admin.getRole());
        entityManager.getTransaction().commit();
        return adminModifier;
    }

    @Override
    public void delete(Admin admin) {
        entityManager.getTransaction().begin();
        entityManager.remove(admin);
        entityManager.getTransaction().commit();
    }

    @Override
    public void close() {
        this.entityManager.close();
        this.emf.close();
    }
}
