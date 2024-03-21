package com.example.projet_gestionecole.Dao.DaoImpl;

import com.example.projet_gestionecole.Dao.EtudiantService;
import com.example.projet_gestionecole.Model.Etudiant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class EtudiantServiceImpl implements EtudiantService {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public EtudiantServiceImpl() {
        this.emf = Persistence.createEntityManagerFactory("persis");
        this.entityManager = this.emf.createEntityManager();
    }

    public EtudiantServiceImpl(String pu) {
        this.emf = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.emf.createEntityManager();
    }

    @Override
    public boolean findUser(String email, String password) {
        boolean found = false;
        Query query = entityManager.createQuery("SELECT e FROM Etudiant e WHERE e.email = :email AND e.password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        try {
            Etudiant user = (Etudiant) query.getSingleResult();
            found = (user != null);
        } catch (NoResultException e) {
            found = false;
        }
        return found;
    }

    @Override
    public void addEtudiant(Etudiant etudiant) {
        entityManager.getTransaction().begin();
        entityManager.persist(etudiant);
        entityManager.getTransaction().commit();
    }

    @Override
    public Etudiant find(Long id) {
        return entityManager.find(Etudiant.class, id);
    }

    @Override
    public Etudiant findByNumAplogue(String numAplogue) {
        Query query = entityManager.createQuery("SELECT e FROM Etudiant e WHERE e.numAplogue = :numAplogue");
        query.setParameter("numAplogue", numAplogue);
        try {
            return (Etudiant) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Etudiant findEtudiantByEmail(String email) {
        Query query = entityManager.createQuery("SELECT e FROM Etudiant e WHERE e.email = :email");
        query.setParameter("email", email);
        try {
            return (Etudiant) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    @Override
    public void update(Etudiant etudiant) {
        entityManager.getTransaction().begin();
        entityManager.merge(etudiant);
        entityManager.getTransaction().commit();
    }


//    @Override
//    public void update(Etudiant etudiant) {
//        Etudiant etudiantModifier = findByNumAplogue(etudiant.getNumAplogue());
//        entityManager.getTransaction().begin();
//        etudiantModifier.setNom(etudiant.getNom());
//        etudiantModifier.setPrenom(etudiant.getPrenom());
//        etudiantModifier.setCin(etudiant.getCin());
//        etudiantModifier.setAdresse(etudiant.getAdresse());
//        etudiantModifier.setTelephone(etudiant.getTelephone());
//        etudiantModifier.setEmail(etudiant.getEmail());
//        //etudiantModifier.setPassword(etudiant.getPassword());
//        etudiantModifier.setNumAplogue(etudiant.getNumAplogue());
//        etudiantModifier.setFiliere(etudiant.getFiliere());
//        entityManager.getTransaction().commit();
//    }


    @Override
    public void delete(Etudiant etudiant) {
        entityManager.getTransaction().begin();
        entityManager.remove(etudiant);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Etudiant> listEtudiants() {
        Query query = entityManager.createQuery("SELECT e FROM Etudiant e");
        return query.getResultList();
    }

    @Override
    public List<Etudiant> listEtudiantsParFiliere(String filiere) {
        Query query = entityManager.createQuery("SELECT e FROM Etudiant e WHERE e.filiere = :filiere");
        query.setParameter("filiere", filiere);
        return query.getResultList();
    }

    @Override
    public void close() {
        this.entityManager.close();
        this.emf.close();
    }
}
