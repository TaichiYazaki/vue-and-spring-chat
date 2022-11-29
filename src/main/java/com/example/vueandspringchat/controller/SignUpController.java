package com.example.vueandspringchat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.vueandspringchat.dto.SignUpDto;
import com.example.vueandspringchat.entity.SignUp;
import com.example.vueandspringchat.form.SignUpForm;
import com.example.vueandspringchat.service.SignUpService;


@RestController
public class SignUpController {

    @Autowired
    private SignUpService service;

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public SignUp receive(@RequestBody SignUpForm form) {
        SignUpDto dto = new SignUpDto();
        dto.setName(form.getName());
        dto.setEmail(form.getEmail());
        dto.setPassword(form.getPassword());
        return service.receive(dto);
    }
}
