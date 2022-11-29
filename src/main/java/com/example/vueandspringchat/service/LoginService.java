package com.example.vueandspringchat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vueandspringchat.entity.Login;
import com.example.vueandspringchat.repository.LoginRepository;

@Service
public class LoginService {

    @Autowired
    private LoginRepository repository;

    public List<Login> login(String email, String password) {
        Login entity = new Login();
        entity.setEmail(email);
        entity.setPassword(password);
        return repository.findByEmailAndPassword(entity.getEmail(), entity.getPassword());

    }
}
