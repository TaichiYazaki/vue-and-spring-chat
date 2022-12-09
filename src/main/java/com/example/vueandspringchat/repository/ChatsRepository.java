package com.example.vueandspringchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.vueandspringchat.entity.Chats;

@Repository
public interface ChatsRepository extends JpaRepository<Chats, Integer> {

    @Query("SELECT DISTINCT e FROM Chats e INNER JOIN e.signup WHERE e.roomId = :roomId ")
    List<Chats> findByRoomId(@Param("roomId") Integer roomId);
}
