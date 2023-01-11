package com.bilgeadam.commentAppJava2023.utility;

import com.bilgeadam.commentAppJava2023.repository.entity.EUserType;
import com.bilgeadam.commentAppJava2023.repository.entity.Like;
import com.bilgeadam.commentAppJava2023.repository.entity.ProductComment;
import com.bilgeadam.commentAppJava2023.repository.entity.User;
import com.bilgeadam.commentAppJava2023.service.LikeService;
import com.bilgeadam.commentAppJava2023.service.ProductCommentService;
import com.bilgeadam.commentAppJava2023.service.ProductService;
import com.bilgeadam.commentAppJava2023.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataImpl {

    private final UserService userService;
    private final ProductService productService;
    private final ProductCommentService productCommentService;
    private final LikeService likeService;

    @PostConstruct
    public void loadData() {
        createUser();
        // createproduct();
        createproductComment();
        createLikes();
    }

    public void createUser() {

        User user = User.builder().email("mert@hotmail.com").name("Mert").
                surName("Kaya").password("123").phone("123").build();
        User user1 = User.builder().email("merve@gmail.com").name("Merve").
                surName("Öztürk").password("123").phone("123").build();
        User user2 = User.builder().email("hasan@yandex.com").name("Hasan").
                surName("Bayindir").password("123").phone("123").build();
        User user3 = User.builder().email("aras@gmail.com").name("Aras").
                surName("Gr").password("123").phone("123").build();
        User user4 = User.builder().email("didem@gmail.com").name("Didem").
                surName("Sönmez").password("123").phone("123").build();
        User user5 = User.builder().email("admin@gmail.com").name("Mustafa").
                surName("Öztürk").userType(EUserType.ADMIN).password("admin").phone("123").build();
        userService.saveAll(List.of(user, user1, user2, user3, user4, user5));
    }
/*
    private void createproduct() {
        Product product = Product.builder().name("Monster NoteBook").price(15000).build();
        Product product1 = Product.builder().name("Sutas Ayran").expirationDate(LocalDate.now().plusDays(7)).price(15).build();
        Product product2 = Product.builder().name("Pinar Sut").expirationDate(LocalDate.now().minusDays(10)).price(20).build();
        Product product3 = Product.builder().name("Pınar Salam").expirationDate(LocalDate.now().plusDays(30)).price(25).build();
        Product product4 = Product.builder().name("Cappy Meyve Suyu").expirationDate(LocalDate.now().minusDays(10)).price(15).build();
        Product product5 = Product.builder().name("Conon Yaizci").price(700).build();
        Product product6 = Product.builder().name("Logitech klavye").price(420).build();
        Product product7 = Product.builder().name("Logitech mouse").price(220).build();
        Product product8 = Product.builder().name("Banvit Tavuk").expirationDate(LocalDate.now().plusDays(5)).price(15).build();
        Product product9 = Product.builder().name("Pak  Maya").expirationDate(LocalDate.now().plusDays(120)).price(15).build();
        productService.saveAll(List.of(product, product1, product2, product3, product4, product5, product6, product7, product8, product9));

        ProductComment pc1 = ProductComment.builder().comment("begendim").product(product1).user(user).commentDate(LocalDate.now().minusMonths(3)).build();
        ProductComment pc2 = ProductComment.builder().comment("cok guzel bir bilgisayar").product(product).user(user1).commentDate(LocalDate.now().minusMonths(10)).build();
        ProductComment pc3 = ProductComment.builder().comment("fazla begenmedim idare eder").product(product).user(user2).commentDate(LocalDate.now().minusMonths(1)).build();
        ProductComment pc4 = ProductComment.builder().comment("begendim").product(product7).user(user3).commentDate(LocalDate.now().minusDays(3)).build();
        ProductComment pc5 = ProductComment.builder().comment("lezzetli").product(product2).user(user4).commentDate(LocalDate.now().minusDays(13)).build();
        ProductComment pc6 = ProductComment.builder().comment("lezzetli").product(product3).user(user4).commentDate(LocalDate.now().minusDays(1)).build();
        ProductComment pc7 = ProductComment.builder().comment("idare eder").product(product4).user(user4).commentDate(LocalDate.now().minusDays(13)).build();
        ProductComment pc8 = ProductComment.builder().comment("kotu bir urun").product(product5).user(user3).commentDate(LocalDate.now().minusDays(3)).build();
        ProductComment pc9 = ProductComment.builder().comment("cok guzel").product(product6).user(user2).commentDate(LocalDate.now().minusDays(266)).build();
        ProductComment pc10 = ProductComment.builder().comment("begendim").product(product7).user(user).commentDate(LocalDate.now().minusYears(1)).build();
        productCommentService.saveAll(List.of(pc1, pc2, pc3, pc4, pc5, pc6, pc7, pc8, pc9, pc10));

    }*/

    public void createproductComment() {
        ProductComment pc1 = ProductComment.builder().comment("begendim").productId(1L).userId(1L).commentDate(LocalDate.now().minusMonths(3)).build();
        ProductComment pc2 = ProductComment.builder().comment("cok guzel bir bilgisayar").productId(1L).userId(2L).commentDate(LocalDate.now().minusMonths(10)).build();
        ProductComment pc3 = ProductComment.builder().comment("fazla begenmedim idare eder").productId(1L).userId(3L).commentDate(LocalDate.now().minusMonths(1)).build();
        ProductComment pc4 = ProductComment.builder().comment("begendim").productId(8L).userId(4L).commentDate(LocalDate.now().minusDays(3)).build();
        ProductComment pc5 = ProductComment.builder().comment("lezzetli").productId(3L).userId(4L).commentDate(LocalDate.now().minusDays(13)).build();
        ProductComment pc6 = ProductComment.builder().comment("lezzetli").productId(4L).userId(5L).commentDate(LocalDate.now().minusDays(1)).build();
        ProductComment pc7 = ProductComment.builder().comment("idare eder").productId(5L).userId(5L).commentDate(LocalDate.now().minusDays(13)).build();
        ProductComment pc8 = ProductComment.builder().comment("kotu bir urun").productId(6L).userId(4L).commentDate(LocalDate.now().minusDays(3)).build();
        ProductComment pc9 = ProductComment.builder().comment("cok guzel").productId(7L).userId(2L).commentDate(LocalDate.now().minusDays(266)).build();
        ProductComment pc10 = ProductComment.builder().comment("begendim").productId(8L).userId(3L).commentDate(LocalDate.now().minusYears(1)).build();
        productCommentService.saveAll(List.of(pc1, pc2, pc3, pc4, pc5, pc6, pc7, pc8, pc9, pc10));
    }

    public void createLikes() {
        Like like = Like.builder().likedDate(LocalDate.now().minusMonths(1)).userId(1L).productId(1L).build();
        Like like2 = Like.builder().likedDate(LocalDate.now().minusMonths(5)).userId(1L).productId(8L).build();
        Like like3 = Like.builder().likedDate(LocalDate.now().minusYears(1)).userId(1L).productId(7L).build();
        Like like4 = Like.builder().likedDate(LocalDate.now().minusMonths(10)).userId(2L).productId(2L).build();
        Like like5 = Like.builder().likedDate(LocalDate.now().minusMonths(18)).userId(2L).productId(3L).build();
        Like like6 = Like.builder().likedDate(LocalDate.now().minusMonths(19)).userId(2L).productId(5L).build();
        Like like7 = Like.builder().likedDate(LocalDate.now().minusMonths(9)).userId(3L).productId(6L).build();
        Like like8 = Like.builder().likedDate(LocalDate.now().minusMonths(1)).userId(2L).productId(1L).build();
        Like like9 = Like.builder().likedDate(LocalDate.now().minusMonths(5)).userId(2L).productId(8L).build();
        Like like10 = Like.builder().likedDate(LocalDate.now().minusDays(15)).userId(3L).productId(1L).build();
        Like like11 = Like.builder().likedDate(LocalDate.now().minusWeeks(9)).userId(3L).productId(8L).build();
        Like like12 = Like.builder().likedDate(LocalDate.now().minusWeeks(31)).userId(4L).productId(8L).build();
        likeService.saveAll(List.of(like, like2, like3, like4, like5, like6, like7, like8, like9, like10, like11, like12));

    }
}