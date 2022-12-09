package com.example.vueandspringchat.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.vueandspringchat.dto.ChatsDto;
import com.example.vueandspringchat.entity.Chats;
import com.example.vueandspringchat.form.ChatsForm;
import com.example.vueandspringchat.service.ChatsService;

@RestController
public class ChatsController {

    @Autowired
    private ChatsService service;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(ChatsForm form) {
        ChatsDto dto = new ChatsDto();
        dto.setMessage(form.getMessage());
        dto.setRoomId(form.getRoomId());
        dto.setUserId(form.getUserId());
        service.save(dto);
    }

    @GetMapping(value = "/chats")
    public List<Chats> getMethodName(ChatsForm form) {
        List<Chats> lists = service.findByRoomId(form.getRoomId());
         return lists;

    }

}
