package com.javierfspano.deturno.controllers;

import com.google.firebase.auth.FirebaseAuthException;
import com.javierfspano.deturno.entities.FarmaciasCercanas;
import com.javierfspano.deturno.services.FarmaciasCercanasService;
import com.javierfspano.deturno.utils.FirebaseAuthUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FarmaciasCercanasController {

    private final FarmaciasCercanasService farmaciasCercanasService;

    public FarmaciasCercanasController(FarmaciasCercanasService farmaciasCercanasService) {
        this.farmaciasCercanasService = farmaciasCercanasService;
    }

    @GetMapping("/farmacias_cercanas")
    public ResponseEntity<?> getFarmaciasCercanas(@RequestParam String direccion, @RequestHeader(name = "IdToken") String idToken) throws FirebaseAuthException {
        if (direccion.isEmpty()) {
            return ResponseEntity.badRequest().body("La direccion no debe estar vacia");
        }

        if (FirebaseAuthUtil.isAuthenticated(idToken)) {

            FarmaciasCercanas farmaciasCercanas = farmaciasCercanasService.getFarmaciasCercanas(direccion);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(farmaciasCercanas);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
