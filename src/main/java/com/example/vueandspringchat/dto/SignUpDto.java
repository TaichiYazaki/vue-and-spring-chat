package com.example.vueandspringchat.dto;

import lombok.Data;

@Data
public class SignUpDto {

    private Integer id;

    private String name;

    private String email;

    private String password;

    private String imgFile;
}
