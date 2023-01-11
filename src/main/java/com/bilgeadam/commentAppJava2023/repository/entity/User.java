package com.bilgeadam.commentAppJava2023.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder  //Yazmakta ki amaç, istedğimiz doğrultuda construcktorlar oluşturabilmek
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id'nin nasıl artacağını belirtiyoruz.
    private long id;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String surName;

    @Column(length = 50)
    private String email;
    @Column(length = 32)
    private String password;
    @Column(length = 15)
    private String phone;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private EUserType userType = EUserType.USER;

    @ElementCollection //ElementCollection User ile favProduct arasında yeni bir tablo oluşturuyor,
    @Builder.Default
    List<Long> favProduct = new ArrayList<>();           // productId'nin türü Long

    @OneToMany  //user ın like ı birden çoktur
    private List<Like> likes;

}
