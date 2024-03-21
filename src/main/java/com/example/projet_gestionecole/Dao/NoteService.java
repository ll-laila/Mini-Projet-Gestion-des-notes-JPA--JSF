package com.example.projet_gestionecole.Dao;

import com.example.projet_gestionecole.Model.Note;

public interface NoteService {
    Note addNote(Note note);
    Note find(Long id);
    void update(Note note);
    void delete(Note note);
    void close();
}
