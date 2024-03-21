package com.example.projet_gestionecole.Dao;

import com.example.projet_gestionecole.Model.Enseignant;
import com.example.projet_gestionecole.Model.Module;

import java.util.List;

public interface ModuleService {
    void addModule(Module module);
    Module find(Long id);
    void update(Module module);
    public List<Module> listModules();
    List<Module> getModulesByEnseignantId(Long enseignantId);
    void delete(Module module);
    void close();
}
