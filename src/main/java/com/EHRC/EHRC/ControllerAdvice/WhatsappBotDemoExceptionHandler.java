package com.EHRC.EHRC.ControllerAdvice;

import com.EHRC.EHRC.CustomExceptions.PhoneNumberNotValidException;
import com.EHRC.EHRC.CustomResponseEntity.PhoneNumberNotValidErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WhatsappBotDemoExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<PhoneNumberNotValidErrorResponse> handlePhoneNumberNotValidException(PhoneNumberNotValidException exception) {

        PhoneNumberNotValidErrorResponse errorResponse = new PhoneNumberNotValidErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
