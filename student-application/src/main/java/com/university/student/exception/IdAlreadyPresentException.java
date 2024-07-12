package com.university.student.exception;

public class IdAlreadyPresentException extends Exception{

    public IdAlreadyPresentException(){
        super();
    }

    public IdAlreadyPresentException(String message){
        super(message);
    }
}
