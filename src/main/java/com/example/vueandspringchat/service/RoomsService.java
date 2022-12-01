package com.example.vueandspringchat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vueandspringchat.dto.RoomsDto;
import com.example.vueandspringchat.entity.Rooms;
import com.example.vueandspringchat.repository.RoomsRepository;

@Service
public class RoomsService {

    @Autowired
    private RoomsRepository repository;

    public Rooms submit(String name, String file) {
        Rooms rooms = new Rooms();
        rooms.setName(name);
        rooms.setFile(file);
        return repository.save(rooms);
    }
}
