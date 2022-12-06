package com.example.vueandspringchat.form;

import lombok.Data;

@Data
public class ChatsForm {

    private Integer id;

    private String message;

    private Integer roomId;

    private Integer userId;
}
