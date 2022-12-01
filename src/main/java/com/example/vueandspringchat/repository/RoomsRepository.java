package com.example.vueandspringchat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vueandspringchat.entity.Rooms;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, String> {
    
}
