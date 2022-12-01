package com.example.vueandspringchat.dto;

import com.example.vueandspringchat.entity.Rooms;

import lombok.Data;

@Data
public class RoomsDto {

    private Integer id;
    
    private String name;

    private String file;

    /**
     * entity→dto変換用のメソッド
     * 
     * @param entity
     * @return
     */
    public static RoomsDto toRoomsDto(Rooms entity) {
        RoomsDto dto = new RoomsDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setFile(entity.getFile());
        return dto;
    }
}
