package com.example.projet_gestionecole.Dao;

import com.example.projet_gestionecole.Model.Admin;

public interface AdminService {
    Admin addAdmin(Admin admin);
    boolean findUser(String email, String password);
    Admin find(Long id);
    Admin update(Admin admin);
    void delete(Admin admin);
    void close();
}
