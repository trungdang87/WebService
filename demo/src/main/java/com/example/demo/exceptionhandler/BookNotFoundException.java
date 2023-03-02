package com.example.demo.exceptionhandler;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException() {
        super();
    }

    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
