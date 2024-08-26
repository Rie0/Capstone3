package org.twspring.capstone3.Advice;

import jakarta.transaction.TransactionalException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.twspring.capstone3.Api.ApiException;

@org.springframework.web.bind.annotation.ControllerAdvice

public class ControllerAdvice {
    @ExceptionHandler(value= ApiException.class)
    public ResponseEntity ApiEception(ApiException e){
        String msg =e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }
    @ExceptionHandler(value= HttpRequestMethodNotSupportedException.class)
    public ResponseEntity HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        String msg =e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }
    @ExceptionHandler(value= NoResourceFoundException.class)
    public ResponseEntity NoResourceFoundException(NoResourceFoundException e){
        String msg =e.getMessage();
        return ResponseEntity.status(400).body(msg);

    }
    @ExceptionHandler(value= DataIntegrityViolationException.class)//email , phone, username are unique
    public ResponseEntity DataIntegrityViolationException(DataIntegrityViolationException e){
        String msg =e.getMessage();
        return ResponseEntity.status(400).body(msg);

    }
    @ExceptionHandler(value= HttpMessageNotReadableException.class)
    public  ResponseEntity HttpMessageNotReadableException(HttpMessageNotReadableException e){
        String msg =e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e){
        String msg =e.getMessage();
        return ResponseEntity.status(400).body(msg);


    }
    @ExceptionHandler(value= TransactionSystemException.class)
    public ResponseEntity TransactionSystemException (TransactionSystemException e){
        String msg =e.getMessage();
        return ResponseEntity.status(400).body(msg);



    }
}
