package com.university.student.exception;

import com.university.student.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.university.student.constant.StudentConstant.FAILED;

@RestControllerAdvice
public class GobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> globalException(Exception e){
        return ResponseEntity.internalServerError().body(new Response(e.getMessage(),FAILED));
    }
}
