package com.javierfspano.deturno.controllers;

import com.google.firebase.auth.FirebaseAuthException;
import com.javierfspano.deturno.exceptions.MapquestApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErroresController {

    @ExceptionHandler({Exception.class, MapquestApiException.class})
    public ResponseEntity<?> excepcionGenerica(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(FirebaseAuthException.class)
    public ResponseEntity<?> excepcionDeAutenticacionDeFirebase(FirebaseAuthException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
