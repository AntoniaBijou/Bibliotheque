package com.example.service;

import com.example.model.Admin;
import com.example.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin verifierConnexion(String nom, String email, String motDePasse) {
        return adminRepository.verifierConnexion(nom, email, motDePasse);
    }

    
}
