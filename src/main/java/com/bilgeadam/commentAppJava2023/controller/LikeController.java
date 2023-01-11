package com.bilgeadam.commentAppJava2023.controller;

import com.bilgeadam.commentAppJava2023.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //rest api kullanıyorsak //mvc kullansaydık @controller olacaktı
@RequestMapping("/api/v1/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService; //final yazmazsak null pointer excepiton hatası yiyoruz.
}
