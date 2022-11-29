package com.example.vueandspringchat.form;

import lombok.Data;

@Data
public class LoginForm {

    private Integer id;

    private String email;

    private String password;
}
