package com.bilgeadam.commentAppJava2023.controller;

import com.bilgeadam.commentAppJava2023.repository.entity.User;
import com.bilgeadam.commentAppJava2023.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //1.Kullanıcıları isme göre sıralı getir.
    //@GetMapping'i veri çekmek için kullanıyoruz
    @GetMapping("/orderbyname") //db den veri çekmek için GetMapping
    public ResponseEntity<List<User>> getUsersByName() {

        return ResponseEntity.ok(userService.findOrderByName());
    }

    //2.Dışarıdan girilen değer hangi kullanıcıların isimlerinde mevcuttur.
    @GetMapping("/containsvalue")
    public ResponseEntity<List<User>> findAllByNameContains(String value) {
        return ResponseEntity.ok(userService.findAllByNameContains(value).get());
    }

    //3.Emaillerinin içinde belirlediğimiz değer geçen kullanıcılar.
    @GetMapping("containsemail")
    public ResponseEntity<List<User>> findAllByEmailContains(String value) {
        return ResponseEntity.ok(userService.findAllByEmailContains(value).get());
    }

    //4.Emailleri belirlediğimiz değere göre biten kullanıcılar. Ending With= Bununla bitiyorsa...
    @GetMapping("/endingemail")
    public ResponseEntity<List<User>> findAllByEmailEnding(String value) {
        return ResponseEntity.ok(userService.findAllByEmailEnding(value).get());
    }

    //5.Email ve Passworde göre kullanıcı kontrolü LOGIN
    @GetMapping("/login") //5.sorunun
    public ResponseEntity<User> login(String email, String password) {
        return ResponseEntity.ok(userService.login(email, password).get());
    }

    //6.Passwordunun uzunluğu belirlediğimiz sayıdan buyuk olanlar(QUERY=sorgu)
    @GetMapping("/controlpassword")
    public ResponseEntity<List<User>> controlPassword(int length) {
        return ResponseEntity.ok(userService.passwordControl(length));
    }

    @GetMapping("/controlpassword2")
    public ResponseEntity<List<User>> controlPassword2(int length) {
        return ResponseEntity.ok(userService.passwordControl2(length));
    }


    //save metodu ,body siz user kaydetme
    @GetMapping("/save")
    public ResponseEntity<User> save(String name, String surName, String email, String password) {
        return ResponseEntity.ok(userService.save(name, surName, email, password));

    }

    //findall metodu
    @GetMapping("/findall")
    public ResponseEntity<List<User>> findall() {
        return ResponseEntity.ok(userService.findall());

    }

    //findbyid metot
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));

    }

    //delete metot
    @DeleteMapping("/delete")
    public void deleteById(Long id) {
        userService.deleteById(id);
    }


}
