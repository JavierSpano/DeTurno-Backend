package com.javierfspano.deturno.controllers;

import com.google.firebase.auth.FirebaseAuthException;
import com.javierfspano.deturno.entities.FarmaciasCercanas;
import com.javierfspano.deturno.exceptions.CoordenadasDeFarmaciasRepositoryException;
import com.javierfspano.deturno.exceptions.MapquestApiException;
import com.javierfspano.deturno.services.FarmaciasCercanasService;
import com.javierfspano.deturno.services.FirebaseAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;

@RestController
public class FarmaciasCercanasController {

    private final FarmaciasCercanasService farmaciasCercanasService;

    private final FirebaseAuthService firebaseAuthService;

    public FarmaciasCercanasController(FarmaciasCercanasService farmaciasCercanasService, FirebaseAuthService firebaseAuthService) {
        this.farmaciasCercanasService = farmaciasCercanasService;
        this.firebaseAuthService = firebaseAuthService;
    }

    @GetMapping("/farmacias_cercanas")
    public ResponseEntity<?> getFarmaciasCercanas(@RequestParam String direccion, @RequestParam @Nullable Double radio, @RequestHeader(name = "IdToken") String idToken)
            throws FirebaseAuthException, MapquestApiException, CoordenadasDeFarmaciasRepositoryException {
        if (direccion.isEmpty()) {
            return ResponseEntity.badRequest().body("La direccion no debe estar vacia");
        }

        if (firebaseAuthService.isAuthenticated(idToken)) {

            FarmaciasCercanas farmaciasCercanas = farmaciasCercanasService.getFarmaciasCercanas(direccion, radio);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(farmaciasCercanas);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @ExceptionHandler({Exception.class, MapquestApiException.class, CoordenadasDeFarmaciasRepositoryException.class})
    public ResponseEntity<?> excepcionGenerica(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(FirebaseAuthException.class)
    public ResponseEntity<?> excepcionDeAutenticacionDeFirebase(FirebaseAuthException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
