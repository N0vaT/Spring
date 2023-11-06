package edu.school21.service.exceptions;

public class NotSavedSubEntityException extends RuntimeException{
    public NotSavedSubEntityException(String message) {
        super(message);
    }
}
