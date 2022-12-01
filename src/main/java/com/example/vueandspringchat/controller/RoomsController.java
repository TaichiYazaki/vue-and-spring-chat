package com.example.vueandspringchat.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.vueandspringchat.service.RoomsService;

@RestController
public class RoomsController {
    
    @Autowired
    private RoomsService service;

    @RequestMapping(value = "/roomInfo", method = RequestMethod.POST)
    public void submit(@RequestParam("roomName") String roomName, @RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get("/Users/YAZAKITAICHI/env/vs-code/vue-and-spring-chat/vue/src/assets/img/roomIcon/" + fileName);
        try {
            byte[] bytes = file.getBytes();
            OutputStream stream = Files.newOutputStream(filePath);
            stream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        service.submit(roomName, fileName);
    }
}
