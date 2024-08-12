package com.neki_skill.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse error = new ErrorResponse(httpStatus.value(), "Erro Interno no Servidor", details);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementFoundException.class)
    protected ResponseEntity<Object> handleNoSuchElementFoundException(NoSuchElementFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ErrorResponse error = new ErrorResponse(httpStatus.value(), "Registro não encontrado", details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Erro de Validação", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

  
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        details.add("Parâmetro não processado: " + ex.getParameterName());
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "Falha na Validação dos Dados da Requisição", details);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "Falha na Validação dos Dados da Requisição", details);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyListException.class)
    public final ResponseEntity<Object> handleEmptyListException(EmptyListException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        HttpStatus httpStatus = HttpStatus.NO_CONTENT;
        ErrorResponse error = new ErrorResponse(httpStatus.value(), "Lista vazia", details);

        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(InvalidLoginException.class)
    public final ResponseEntity<Object> handleInvalidLoginException(InvalidLoginException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorResponse error = new ErrorResponse(httpStatus.value(), "Login inválido", details);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
