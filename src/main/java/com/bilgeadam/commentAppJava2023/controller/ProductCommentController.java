package com.bilgeadam.commentAppJava2023.controller;

import com.bilgeadam.commentAppJava2023.repository.entity.ProductComment;
import com.bilgeadam.commentAppJava2023.service.ProductCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productcomment")
@RequiredArgsConstructor
public class ProductCommentController {
    private final ProductCommentService productCommentService;

    /**************************************************************************************************************/
    /* 15.Bir ürüne ait yorumları listeleyen bir metot yazınız.*/
    @GetMapping("/findbyproductid")
    public ResponseEntity<List<ProductComment>> findAllByProductId(long productId) {
        return ResponseEntity.ok(productCommentService.findAllByProductId(productId));
    }

    /************************************************************************************************************/
    /*16.Verilen tarih aralıklarında belirli bir ürüne yapılmış olan yorumları gösteren bir metot yazınız.*/
    @GetMapping("/findbycommentdateandproductid")
    public ResponseEntity<List<ProductComment>> findAllByCommentDateBetweenAndProductId(String now, String nextDate, long id) {
        return ResponseEntity.ok(productCommentService.findAllByCommentDateBetweenAndProductId(now, nextDate, id));
    }

    /************************************************************************************************************/
    /*17.Bir kullanıcının yapmış olduğu yorumları listeleyen metot yazınız.*/
    @GetMapping("/findbyuserid")
    public ResponseEntity<List<ProductComment>> findAllByUserId(long userId) {
        return ResponseEntity.ok(productCommentService.findAllByUserId(userId));
    }

    /************************************************************************************************************/
    /*18.Bir kullanıcının belirli tarihler arasında yapmış olduğu yorumları gösteren bir metot yazınız*/
    @GetMapping("/findbycommentdateanduserid")
    public ResponseEntity<List<ProductComment>> findAllByCommentDateBetweenAndUserId(String now, String nextDate, long userId) {
        return ResponseEntity.ok(productCommentService.findAllByCommentDateBetweenAndUserId(now, nextDate, userId));
    }

    /************************************************************************************************************/
    /*19.Yorum içerisinde çok güzel geçenleri getiriniz*/
    @GetMapping("/findbycommentcontains")
    public ResponseEntity<List<ProductComment>> findAllByCommentContaining(String comment) {
        return ResponseEntity.ok(productCommentService.findAllByCommentContaining(comment));
    }

    /************************************************************************************************************/
    /*20. Yorumun uzunluğu 20 karakterden büyük olanları getiriniz.*/
    @GetMapping("/findbycommentlength")
    public ResponseEntity<List<ProductComment>> findByCommentLength(int length) {
        return ResponseEntity.ok(productCommentService.findByCommentLength(length));
    }

    //findall metodu
    @GetMapping("/findall")
    public ResponseEntity<List<ProductComment>> findall() {
        return ResponseEntity.ok(productCommentService.findall());

    }

    //findbyid metot
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<ProductComment> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productCommentService.findById(id));

    }

    //delete metot
    @DeleteMapping("/delete")
    public void deleteById(Long id) {
        productCommentService.deleteById(id);
    }


    @GetMapping("/save")
    public ResponseEntity<ProductComment> save(String comment, long productId, long userId) {
        return ResponseEntity.ok(productCommentService.save(comment, productId, userId));
    }


}
