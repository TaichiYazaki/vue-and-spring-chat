package com.example.vueandspringchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vueandspringchat.entity.Login;

public interface LoginRepository extends JpaRepository<Login, String> {
    
    List<Login> findByEmailAndPassword(String email, String password);
}
