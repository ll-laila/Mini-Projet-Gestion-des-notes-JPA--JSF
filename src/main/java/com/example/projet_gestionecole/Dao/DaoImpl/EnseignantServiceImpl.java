package com.example.projet_gestionecole.Dao.DaoImpl;

import com.example.projet_gestionecole.Dao.EnseignantService;
import com.example.projet_gestionecole.Model.Enseignant;
import com.example.projet_gestionecole.Model.Etudiant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class EnseignantServiceImpl implements EnseignantService {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public EnseignantServiceImpl() {
        this.emf = Persistence.createEntityManagerFactory("persis");
        this.entityManager = this.emf.createEntityManager();
    }

    public EnseignantServiceImpl(String pu) {
        this.emf = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.emf.createEntityManager();
    }

    @Override
    public void addEnseignant(Enseignant enseignant) {
        entityManager.getTransaction().begin();
        entityManager.persist(enseignant);
        entityManager.getTransaction().commit();
    }

    @Override
    public boolean findUser(String email, String password) {
        boolean found = false;
        Query query = entityManager.createQuery("SELECT e FROM Enseignant e WHERE e.email = :email AND e.password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        try {
            Enseignant user = (Enseignant) query.getSingleResult();
            found = (user != null);
        } catch (NoResultException e) {
            found = false;
        }
        return found;
    }

    @Override
    public Enseignant find(Long id) {
        return entityManager.find(Enseignant.class, id);
    }


    @Override
    public Long findEnseignantByEmail(String email) {
        Query query = entityManager.createQuery("SELECT e.id FROM Enseignant e WHERE e.email = :email");
        query.setParameter("email", email);
        try {
            return (Long) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Enseignant findEnseignantByCIN(String cin) {
        Query query = entityManager.createQuery("SELECT e FROM Enseignant e WHERE e.cin = :cin");
        query.setParameter("cin", cin);
        try {
            return (Enseignant) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    @Override
    public void update(Enseignant enseignant)  {
        entityManager.getTransaction().begin();
        entityManager.merge(enseignant);
        entityManager.getTransaction().commit();
    }

//    @Override
//    public void update(Enseignant enseignant) {
//        Enseignant enseignantModifier = find(enseignant.getId());
//        entityManager.getTransaction().begin();
//        enseignantModifier.setNom(enseignant.getNom());
//        enseignantModifier.setPrenom(enseignant.getPrenom());
//        enseignantModifier.setCin(enseignant.getCin());
//        enseignantModifier.setAdresse(enseignant.getAdresse());
//        enseignantModifier.setTelephone(enseignant.getTelephone());
//        enseignantModifier.setEmail(enseignant.getEmail());
//        enseignantModifier.setPassword(enseignant.getPassword());
//        enseignantModifier.setSpecialite(enseignant.getSpecialite());
//        entityManager.getTransaction().commit();
//    }

    @Override
    public List<Enseignant> listEnseignant() {
        Query query = entityManager.createQuery("SELECT e FROM Enseignant e");
        return query.getResultList();
    }




    @Override
    public void delete(Enseignant enseignant) {
        entityManager.getTransaction().begin();
        entityManager.remove(enseignant);
        entityManager.getTransaction().commit();
    }

    @Override
    public void close() {
        this.entityManager.close();
        this.emf.close();
    }
}
