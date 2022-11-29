package com.example.vueandspringchat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.vueandspringchat.dto.LoginDto;
import com.example.vueandspringchat.entity.Login;
import com.example.vueandspringchat.form.LoginForm;
import com.example.vueandspringchat.service.LoginService;


@RestController
public class LoginController {

    @Autowired
    private LoginService service;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginDto login(@RequestBody LoginForm form) {
        List<Login> dto = service.login(form.getEmail(), form.getPassword());
        LoginDto loginDto = new LoginDto();
        if (dto.isEmpty()) {
            loginDto.setEmail("NotFoundEmailAndPassword");
            loginDto.setPassword("NotFoundEmailAndPassword");;
        }else{
            loginDto.setId(dto.get(0).getId());
            loginDto.setEmail(dto.get(0).getEmail());
            loginDto.setPassword(dto.get(0).getPassword());
        }
        return loginDto;
    }
}
