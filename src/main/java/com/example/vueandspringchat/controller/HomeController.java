package com.example.vueandspringchat.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.vueandspringchat.dto.RoomsDto;
import com.example.vueandspringchat.dto.SignUpDto;
import com.example.vueandspringchat.entity.Rooms;
import com.example.vueandspringchat.entity.SignUp;
import com.example.vueandspringchat.form.LoginForm;
import com.example.vueandspringchat.service.RoomsService;
import com.example.vueandspringchat.service.SignUpService;

@RestController
public class HomeController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private RoomsService roomsService;

    /**
     * イメージ画像をDBに保存します
     * 
     * @param form
     * @param file
     * @return
     */
    @RequestMapping(value = "/insertImg", method = RequestMethod.POST)
    public SignUpDto updateIcon(LoginForm form, @RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Path filePath = Paths
                .get("/Users/YAZAKITAICHI/env/vs-code/vue-and-spring-chat/vue/src/assets/img/userIcon/" + fileName);
        try {
            byte[] bytes = file.getBytes();
            OutputStream stream = Files.newOutputStream(filePath);
            stream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ファイル名をDBに保存
        signUpService.insertImgFile(form.getId(), fileName);

        // 保存したファイル名を取得
        Optional<SignUp> list = signUpService.findById(form.getId());
        SignUpDto dto = toSignUpDto(list.get());
        return dto;
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public SignUpDto findById(Integer id) {
        Optional<SignUp> list = signUpService.findById(id);
        SignUpDto dto = toSignUpDto(list.get());
        return dto;
    }

    @RequestMapping(value = "/showRooms", method = RequestMethod.GET)
    public List<RoomsDto> showRooms() {
        List<RoomsDto> dto = toRoomsDto(roomsService.showRooms());
       
        return dto;
    }

    /**
     * entity→dto変換用のメソッド
     * 
     * @param entity
     * @return
     */
    public static SignUpDto toSignUpDto(SignUp entity) {
        SignUpDto dto = new SignUpDto();
        dto.setImgFile(entity.getFileName());
        return dto;
    }

    /**
     * List<entity>→List<dto>変換用のメソッド
     * @param entity
     * @return
     */
    private List<RoomsDto> toRoomsDto(List<Rooms> entity) {
        List<RoomsDto> dto = 
        entity.stream().map(RoomsDto::toRoomsDto).collect(Collectors.toList());
        return dto;
    }

}
