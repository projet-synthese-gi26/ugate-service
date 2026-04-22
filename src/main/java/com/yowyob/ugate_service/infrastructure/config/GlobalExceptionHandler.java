package com.yowyob.ugate_service.infrastructure.config;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseStatusException handleDuplicateKey(DuplicateKeyException ex) {
        return new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Conflit de données : entité déjà existante"
        );
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseStatusException handleIllegalState(IllegalStateException ex) {
        return new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
    }
}