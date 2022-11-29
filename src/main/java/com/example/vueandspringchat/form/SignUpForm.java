package com.example.vueandspringchat.form;

import lombok.Data;

@Data
public class SignUpForm {
    
    private Integer id;

    private String name;

    private String email;

    private String password;

    private String fileName;
}
