package com.example.vueandspringchat.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.vueandspringchat.dto.SignUpDto;
import com.example.vueandspringchat.entity.SignUp;
import com.example.vueandspringchat.form.LoginForm;
import com.example.vueandspringchat.service.SignUpService;

@RestController
public class HomeController {

    @Autowired
    private SignUpService service;


    /**
     * イメージ画像をDBに保存します
     * @param form
     * @param file
     * @return
     */
    @RequestMapping(value = "/insertImg", method = RequestMethod.POST)
    public SignUpDto updateIcon(LoginForm form, @RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get("/Users/YAZAKITAICHI/env/vs-code/vue-and-spring-chat/vue/src/assets/img/userIcon/" + fileName);
        try {
            byte[] bytes = file.getBytes();
            OutputStream stream = Files.newOutputStream(filePath);
            stream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Optional<SignUp> list = service.findById(form.getId());
        SignUpDto dto = toSignUpDto(list.get());
        return dto;
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public SignUpDto findById(Integer id){
        System.out.println(id);
        Optional<SignUp> list = service.findById(id);
        SignUpDto dto = toSignUpDto(list.get());
        return dto;
    }

    /**
     * entity→dto変換用のメソッド
     * @param entity
     * @return
     */
    private SignUpDto toSignUpDto(SignUp entity) {
        SignUpDto dto = new SignUpDto();
        dto.setImgFile(entity.getFileName());
        return dto;
    }
}
