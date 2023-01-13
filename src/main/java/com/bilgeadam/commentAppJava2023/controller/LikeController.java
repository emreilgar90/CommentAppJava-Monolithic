package com.bilgeadam.commentAppJava2023.controller;

import com.bilgeadam.commentAppJava2023.repository.entity.Like;
import com.bilgeadam.commentAppJava2023.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**/
@RestController  //rest api kullanıyorsak //mvc kullansaydık @controller olacaktı
@RequestMapping("/api/v1/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService; //final yazmazsak null pointer excepiton hatası yiyoruz.

    @GetMapping("/save")
    public ResponseEntity<Like> save(long userId, long productId) {
        return ResponseEntity.ok(likeService.save(userId, productId));
    }

    @GetMapping("findall")
    public ResponseEntity<List<Like>> findall() {
        return ResponseEntity.ok(likeService.findAll());
    }
}
