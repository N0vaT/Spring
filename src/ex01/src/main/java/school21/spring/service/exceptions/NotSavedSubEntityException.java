package school21.spring.service.exceptions;

public class NotSavedSubEntityException extends RuntimeException{
    public NotSavedSubEntityException(String message) {
        super(message);
    }
}
