package com.bilgeadam.commentAppJava2023.service;

import com.bilgeadam.commentAppJava2023.exception.CommentAppException;
import com.bilgeadam.commentAppJava2023.exception.ErrorType;
import com.bilgeadam.commentAppJava2023.repository.ILikeRepository;
import com.bilgeadam.commentAppJava2023.repository.entity.Like;
import com.bilgeadam.commentAppJava2023.repository.entity.Product;
import com.bilgeadam.commentAppJava2023.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final UserService userService;
    private final ProductService productService;

    private final ILikeRepository likeRepository;

    public void saveAll(List<Like> likes) {

        likeRepository.saveAll(likes);
    }

    public Like save(long userId, long productId) {
        Optional<User> user = userService.findById(userId);   //Like için gerekli servicler,db de varmı diye kontrol ediyoruz
        Optional<Product> product = productService.findById(productId);  //Like için gerekli serviceler, db de varmı diye kontrol ediyoruz
        if (user.isPresent() && product.isPresent()) {
            try {
                return likeRepository.save(Like.builder().productId(productId).user(user.get()).build());
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        } else {
            throw new CommentAppException(ErrorType.LIKE_NOT_CREATED);
        }
    }

    public List<Like> findAll() {
        return likeRepository.findAll();

    }
}
