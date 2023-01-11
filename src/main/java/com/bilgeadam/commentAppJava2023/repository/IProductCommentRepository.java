package com.bilgeadam.commentAppJava2023.repository;

import com.bilgeadam.commentAppJava2023.repository.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IProductCommentRepository extends JpaRepository<ProductComment, Long> {

    /************************************************************************************************************/
    /* 15.Bir ürüne ait yorumları listeleyen bir metot yazınız.*/
    List<ProductComment> findAllByProductId(long productId);


    /************************************************************************************************************/
    /*16.Verilen tarih aralıklarında belirli bir ürüne yapılmış olan yorumları gösteren bir metot yazınız.*/

    List<ProductComment> findAllByCommentDateBetweenAndProductId(LocalDate now, LocalDate nextDate, long id);

    /************************************************************************************************************/
    /*17.Bir kullanıcının yapmış olduğu yorumları listeleyen metot yazınız.*/

    List<ProductComment> findAllByUserId(long userId);

    /************************************************************************************************************/
    /*18.Bir kullanıcının belirli tarihler arasında yapmış olduğu yorumları gösteren bir metot yazınız*/

    List<ProductComment> findAllByCommentDateBetweenAndUserId(LocalDate now, LocalDate nextDate, long userId);

    /************************************************************************************************************/
    /*19.Yorum içerisinde çok güzel geçenleri getiriniz*/
    List<ProductComment> findAllByCommentContaining(String comment);

    /************************************************************************************************************/
    /*20. Yorumun uzunluğu 20 karakterden büyük olanları getiriniz.*/
    @Query("select pc from ProductComment as pc where length(pc.comment)>?1 ")
    List<ProductComment> findByCommentLength(int length);


}
