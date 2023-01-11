package com.bilgeadam.commentAppJava2023.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {


    //fırlattığımız hatalar dışında oluşan hataları yakalamak için yazdığımız metot.
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<String> handlerRuntimeException(RuntimeException ex) {
        return ResponseEntity.ok("Beklenmeyen bir hata oluştu: " + ex.getMessage());
    }

    @ExceptionHandler(CommentAppException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handlerCommentAppException(CommentAppException ex) {
        ErrorType errorType = ex.getErrorType();
        HttpStatus httpStatus = errorType.getHttpStatus();
        return new ResponseEntity<>(createError(errorType, ex), httpStatus);
    }

    private ErrorMessage createError(ErrorType errorType, Exception exception) {
        System.out.println("Hata oluştu:  " + exception.getMessage());
        return ErrorMessage.builder()
                .code(errorType.getCode())
                .message(errorType.getMessage())
                .build();
    }
}
