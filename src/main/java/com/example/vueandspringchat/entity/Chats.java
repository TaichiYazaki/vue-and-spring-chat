package com.example.vueandspringchat.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "chats")
@Data
public class Chats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String message;

    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private SignUp signup;

}