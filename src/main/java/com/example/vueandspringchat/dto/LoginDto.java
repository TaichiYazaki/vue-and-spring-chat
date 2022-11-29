package com.example.vueandspringchat.dto;

import lombok.Data;

@Data
public class LoginDto {

    private Integer id;
    
    private String email;

    private String password;
}
