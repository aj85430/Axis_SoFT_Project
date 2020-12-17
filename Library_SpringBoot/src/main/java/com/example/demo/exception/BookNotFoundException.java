package com.example.demo.exception;

@SuppressWarnings("serial")
public class BookNotFoundException extends RuntimeException {
	
public BookNotFoundException() {}
    

    public BookNotFoundException( String message) {
        super(message);
        
    }

}
