package com.university.student.controller;

import com.university.student.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.university.student.constant.StudentConstant.FAILED;
/**
 * Global Exception handler
 * Handling all exception
 * return {@link ResponseEntity} internal server error with exceptions
 * */
@Slf4j
@RestControllerAdvice
public class GobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> globalException(Exception e){
        return ResponseEntity.internalServerError().body(new Response(e.getMessage(),FAILED));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> methodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String, List<String>> errorMap = new HashMap<>();
        e.getBindingResult().getAllErrors();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorMap.computeIfAbsent(fieldName, k -> new ArrayList<>()).add(errorMessage);
        });
        log.info(e.getMessage());
        return ResponseEntity.internalServerError().body(new Response(errorMap,FAILED));
    }
}
