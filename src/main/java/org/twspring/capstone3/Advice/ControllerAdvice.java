package org.twspring.capstone3.Advice;

import jakarta.transaction.TransactionalException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
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
    @ExceptionHandler(value = HttpMessageNotWritableException.class)
    public ResponseEntity HttpMessageNotWritableException (HttpMessageNotWritableException e){
        String msg =e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }
    @ExceptionHandler(value = UnexpectedTypeException.class)
    public ResponseEntity UnexpectedTypeException (UnexpectedTypeException e){
        String msg =e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }
    //JpaObjectRetrievalFailureException
    @ExceptionHandler(value = JpaObjectRetrievalFailureException.class)
    public ResponseEntity JpaObjectRetrievalFailureException (JpaObjectRetrievalFailureException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }
    //NullPointerException
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity NullPointerException (NullPointerException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }
    @ExceptionHandler(value = JpaSystemException.class)
    public ResponseEntity JpaSystemException(JpaSystemException e){
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }
    @ExceptionHandler(value = MissingPathVariableException.class)
    public ResponseEntity MissingPathVariableException(MissingPathVariableException e){
        String msg =e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ResponseEntity HttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e){
        String msg =e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }

    @ExceptionHandler(value = InvalidDataAccessApiUsageException.class)
    public ResponseEntity InvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException e){
        String msg =e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        String msg =e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }

    @ExceptionHandler(value = InvalidDataAccessResourceUsageException.class)
    public ResponseEntity InvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException e){
        String msg =e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity ConstraintViolationException(ConstraintViolationException e){
        String msg =e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }

}
