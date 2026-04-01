package com.minhph091.estateportal.exception;

public class DuplicateResourceException extends RuntimeException{
    public DuplicateResourceException(String name){
        super(name);
    }
}
