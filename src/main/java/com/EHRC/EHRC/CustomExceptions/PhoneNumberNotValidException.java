package com.EHRC.EHRC.CustomExceptions;

public class PhoneNumberNotValidException extends RuntimeException{

    public PhoneNumberNotValidException(String message) {
        super(message);
    }

    public PhoneNumberNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneNumberNotValidException(Throwable cause) {
        super(cause);
    }
}
