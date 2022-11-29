package com.example.vueandspringchat.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vueandspringchat.dto.SignUpDto;
import com.example.vueandspringchat.entity.SignUp;
import com.example.vueandspringchat.repository.SignUpRepository;


@Service
public class SignUpService {

    @Autowired
    private SignUpRepository repository;

    public SignUp receive(SignUpDto dto) {
        SignUp entity = new SignUp();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        return repository.save(entity);
    }

    /**
     * idを元に画像の名前をDBへ保存します
     * 
     * @param id
     * @param fileName
     */
    public void insertImgFile(Integer id, String fileName) {
        repository.insertImgFile(id, fileName);
    }

    /**
     * idを元にDBからリストを取得します
     * 
     * @param id
     * @return
     */
    public Optional<SignUp> findById(Integer id) {
        return repository.findById(id);

    }

}
