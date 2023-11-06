package edu.school21.service.exceptions;

public class UserWithEmailAlreadyExistsException extends RuntimeException{
    public UserWithEmailAlreadyExistsException(String message) {
        super(message);
    }
}
