package com.example.projet_gestionecole.Dao.DaoImpl;

import com.example.projet_gestionecole.Dao.NoteService;
import com.example.projet_gestionecole.Model.Note;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class NoteServiceImpl implements NoteService {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public NoteServiceImpl() {
        this.emf = Persistence.createEntityManagerFactory("persis");
        this.entityManager = this.emf.createEntityManager();
    }

    public NoteServiceImpl(String pu) {
        this.emf = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.emf.createEntityManager();
    }

    @Override
    public Note addNote(Note note) {
        entityManager.getTransaction().begin();
        entityManager.persist(note);
        entityManager.getTransaction().commit();
        return note;
    }

    @Override
    public Note find(Long id) {
        return entityManager.find(Note.class, id);
    }

    @Override
    public void update(Note note)  {
        entityManager.getTransaction().begin();
        entityManager.merge(note);
        entityManager.getTransaction().commit();
    }

//    @Override
//    public Note update(Note note) {
//        Note noteModifier = find(note.getId());
//        entityManager.getTransaction().begin();
//        noteModifier.setEtudiant(note.getEtudiant());
//        noteModifier.setModule(note.getModule());
//        noteModifier.setNote(note.getNote());
//        entityManager.getTransaction().commit();
//        return noteModifier;
//    }

    @Override
    public void delete(Note note) {
        entityManager.getTransaction().begin();
        entityManager.remove(note);
        entityManager.getTransaction().commit();
    }

    @Override
    public void close() {
        this.entityManager.close();
        this.emf.close();
    }
}
