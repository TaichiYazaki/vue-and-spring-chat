package com.example.vueandspringchat.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;


@Entity
@Table(name = "signup")
@Data
public class SignUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "file_name")
    private String fileName;

    @OneToMany(mappedBy="signup", cascade=CascadeType.ALL)
    @ToString.Exclude // @Dataを利用した場合、ハッシュコードエラーを回避するために必要
    @JsonIgnore // jackson.databindのエラーを回避するために必要
    private List<Chats> chats;



    
}
