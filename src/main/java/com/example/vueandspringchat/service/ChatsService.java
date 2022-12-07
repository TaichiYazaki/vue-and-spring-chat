package com.example.vueandspringchat.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vueandspringchat.dto.ChatsDto;
import com.example.vueandspringchat.entity.Chats;
import com.example.vueandspringchat.repository.ChatsRepository;

@Service
public class ChatsService {

    @Autowired
    private ChatsRepository repository;

    public void save(ChatsDto dto) {

        Chats entity = new Chats();
        entity.setMessage(dto.getMessage());
        entity.setRoomId(dto.getRoomId());
        entity.setUserId(dto.getUserId());
        entity.setCreatedAt(new Date());
        repository.save(entity);
    }

    public List<Chats> findByRoomId(Integer roomId) {
        return repository.findByRoomId(roomId);
    }
}
