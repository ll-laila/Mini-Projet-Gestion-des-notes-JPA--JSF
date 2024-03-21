package com.example.projet_gestionecole.Dao.DaoImpl;

import com.example.projet_gestionecole.Dao.ModuleService;
import com.example.projet_gestionecole.Model.Enseignant;
import com.example.projet_gestionecole.Model.Module;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class ModuleServiceImpl implements ModuleService {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public ModuleServiceImpl() {
        this.emf = Persistence.createEntityManagerFactory("persis");
        this.entityManager = this.emf.createEntityManager();
    }

    public ModuleServiceImpl(String pu) {
        this.emf = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.emf.createEntityManager();
    }

    @Override
    public void addModule(Module module) {
        entityManager.getTransaction().begin();
        entityManager.persist(module);
        entityManager.getTransaction().commit();
    }

    @Override
    public Module find(Long id) {
        return entityManager.find(Module.class, id);
    }

    @Override
    public void update(Module module)  {
        entityManager.getTransaction().begin();
        entityManager.merge(module);
        entityManager.getTransaction().commit();
    }


    @Override
    public List<Module> listModules() {
        Query query = entityManager.createQuery("SELECT m FROM Module m");
        return query.getResultList();
    }

    @Override
    public List<Module> getModulesByEnseignantId(Long enseignantId) {
        Query query = entityManager.createQuery("SELECT m FROM Module m WHERE m.enseignant.id = :enseignantId");
        query.setParameter("enseignantId", enseignantId);
        return query.getResultList();
    }



    @Override
    public void delete(Module module) {
        entityManager.getTransaction().begin();
        entityManager.remove(module);
        entityManager.getTransaction().commit();
    }

    @Override
    public void close() {
        this.entityManager.close();
        this.emf.close();
    }

}
