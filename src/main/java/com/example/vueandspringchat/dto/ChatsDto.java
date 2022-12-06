package com.example.vueandspringchat.dto;

import lombok.Data;

@Data
public class ChatsDto {
    
    private Integer id;

    private String message;

    private Integer roomId;

    private Integer userId;
}
