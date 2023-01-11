package com.bilgeadam.commentAppJava2023.service;

import com.bilgeadam.commentAppJava2023.exception.CommentAppException;
import com.bilgeadam.commentAppJava2023.exception.ErrorType;
import com.bilgeadam.commentAppJava2023.repository.IProductCommentRepository;
import com.bilgeadam.commentAppJava2023.repository.entity.Product;
import com.bilgeadam.commentAppJava2023.repository.entity.ProductComment;
import com.bilgeadam.commentAppJava2023.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCommentService {
    private final IProductCommentRepository productCommentRepository;

    private final ProductService productService; //service service ulaşacak ki diğer service in repositorysinden faydalansın


    private final UserService userService;


    public void saveAll(List<ProductComment> pc1) {
        productCommentRepository.saveAll(pc1);

    }

    /************************************************************************************************************/
    /* 15.Bir ürüne ait yorumları listeleyen bir metot yazınız.*/
    public List<ProductComment> findAllByProductId(long productId) {
        return productCommentRepository.findAllByProductId(productId);
    }

    /************************************************************************************************************/
    /*16.Verilen tarih aralıklarında belirli bir ürüne yapılmış olan yorumları gösteren bir metot yazınız.*/
    public List<ProductComment> findAllByCommentDateBetweenAndProductId(String now, String nextDate, long id) {

        return productCommentRepository.findAllByCommentDateBetweenAndProductId(LocalDate.parse(now), LocalDate.parse(nextDate)
                , id);
    }

    /************************************************************************************************************/
    /*17.Bir kullanıcının yapmış olduğu yorumları listeleyen metot yazınız.*/
    public List<ProductComment> findAllByUserId(long userId) {
        return productCommentRepository.findAllByUserId(userId);
    }

    /************************************************************************************************************/
    /*18.Bir kullanıcının belirli tarihler arasında yapmış olduğu yorumları gösteren bir metot yazınız*/
    public List<ProductComment> findAllByCommentDateBetweenAndUserId(String now, String nextDate, long userId) {
        return productCommentRepository.findAllByCommentDateBetweenAndUserId(LocalDate.parse(now), LocalDate.parse(nextDate), userId);
    }

    /************************************************************************************************************/
    /*19.Yorum içerisinde çok güzel geçenleri getiriniz*/
    public List<ProductComment> findAllByCommentContaining(String comment) {
        return productCommentRepository.findAllByCommentContaining(comment);
    }

    /************************************************************************************************************/
    /*20. Yorumun uzunluğu 20 karakterden büyük olanları getiriniz.*/
    @Query("select pc from ProductComment as pc where length(pc.comment)>?1 ")
    public List<ProductComment> findByCommentLength(int length) {
        return productCommentRepository.findByCommentLength(length);
    }

    /************************************************************************************************************/
    /*FindAll metot*/
    public List<ProductComment> findall() {
        return productCommentRepository.findAll();

    }

    /*FindById*/
    public ProductComment findById(Long id) {

        Optional<ProductComment> productComment = productCommentRepository.findById(id);
        if (productComment.isPresent()) {
            return productComment.get();
        } else {
            throw new CommentAppException(ErrorType.PRODUCTCOMMENT_NOT_FOUND);
        }
    }

    public ProductComment deleteById(Long id) {
        Optional<ProductComment> productComment = productCommentRepository.findById(id);
        if (productComment.isPresent()) {
            return productComment.get();
        } else {
            throw new CommentAppException(ErrorType.PRODUCTCOMMENT_NOT_FOUND);
        }


    }

    public ProductComment save(String comment, long productId, long userId) {
        Optional<Product> product = productService.findById(productId);
        Optional<User> user = userService.findById(userId);  //iki metot da productcommentService'in içerisine
        //productservice ve userService in enjekte ettik dolayısıyla metotların throw ile hata forlatlamaları var.
        //tekrar hata yakalamanın anlamı yok !
        if (product.isPresent() && user.isPresent()) {
            try {
                return productCommentRepository.save(ProductComment.builder()
                        .comment(comment)
                        .productId(productId)
                        .userId(userId)
                        .build());
            } catch (Exception e) {
                    throw new CommentAppException(ErrorType.PRODUCTCOMMENT_NOT_CREATED,e.getMessage());
            }else{
                throw new CommentAppException(ErrorType.PRODUCTCOMMENT_NOT_CREATED);



            }


        }


    }
}