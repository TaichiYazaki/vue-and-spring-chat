package com.example.vueandspringchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.vueandspringchat.entity.Chats;

@Repository
public interface ChatsRepository extends JpaRepository<Chats, String> {

    @Query(value = "select c.id, c.room_id, c.user_id, c.message, c.created_at,"
            + " m.id,m.name,m.email,m.password,m.file_name"
            + " from chats as c"
            + " join signup as m"
            + " on c.user_id = m.id"
            + " where c.room_id = :roomId", nativeQuery = true)
    List<Chats> findByRoomId(@Param("roomId") Integer roomId);
}
