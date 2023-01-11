package com.bilgeadam.commentAppJava2023.service;

import com.bilgeadam.commentAppJava2023.repository.ILikeRepository;
import com.bilgeadam.commentAppJava2023.repository.entity.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final ILikeRepository likeRepository;

    public void saveAll(List<Like> likes) {
        likeRepository.saveAll(likes);
    }
}
