package com.bilgeadam.commentAppJava2023.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorType {

    USER_NOT_FOUND(1000, "Aramakta olduğunuz kullanıcı sistemde kayıtlı değildir.", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_CREATED(1003, "KULLANICI OLUŞTURULAMADI.", HttpStatus.INTERNAL_SERVER_ERROR),

    VALUE_NOT_FOUND_IN_NAMES(1001, "Aramakta olduğunuz veri kullanıcı işlemlerinde bulunmamaktadır.", HttpStatus.INTERNAL_SERVER_ERROR),

    VALUE_NOT_FOUND_IN_EMAILS(1002, "Aramakta olduğunuz veri kullanıcı işlemlerinde bulunmamaktadır.", HttpStatus.INTERNAL_SERVER_ERROR),

    PRODUCT_NOT_FOUND(2000, "Aramakta olduğunuz ürün sistemde kayıtlı değildir.", HttpStatus.INTERNAL_SERVER_ERROR),

    PRODUCT_ALREADY_EXİSTS_IN_FAVORITE_LIST(2001, "Ürün zaten favori listesinde var", HttpStatus.INTERNAL_SERVER_ERROR),
    PRODUCT_NOT_CREATED(2003, "Ürün oluşturulamadı.", HttpStatus.INTERNAL_SERVER_ERROR),

    PRODUCTCOMMENT_NOT_FOUND(3000, "Aramakta olduğunuz yorum sistemde kayıtlı değildir.", HttpStatus.INTERNAL_SERVER_ERROR),

    PRODUCTCOMMENT_NOT_CREATED(3003, "Yorum oluşturulamadı.", HttpStatus.INTERNAL_SERVER_ERROR),

    LIKE_NOT_CREATED(4003, "Like oluşturulamadı.", HttpStatus.INTERNAL_SERVER_ERROR);

    //Constructorlı ENUM OLUŞTURDUK CODE olarak 1000 yazdık,mesaj verdik ve http hatası yazdırdık.

    private int code;
    private String message;
    HttpStatus httpStatus;
}
