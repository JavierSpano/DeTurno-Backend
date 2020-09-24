package com.javierfspano.deturno;

import com.google.firebase.auth.FirebaseAuthException;
import com.javierfspano.deturno.controllers.FarmaciasCercanasController;
import com.javierfspano.deturno.exceptions.CoordenadasDeFarmaciasRepositoryException;
import com.javierfspano.deturno.exceptions.MapquestApiException;
import com.javierfspano.deturno.services.FarmaciasCercanasService;
import com.javierfspano.deturno.services.FirebaseAuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FarmaciaCercanasControllerTest {

    @Mock
    private FarmaciasCercanasService farmaciasCercanasService;

    @Mock
    private FirebaseAuthService firebaseAuthService;

    private FarmaciasCercanasController farmaciasCercanasController;

    private final String token = "token";

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        farmaciasCercanasController = new FarmaciasCercanasController(farmaciasCercanasService, firebaseAuthService);
    }

    @Test
    public void testHttpUnauthorizedCuandoElUsuarioNoEstaAutenticado() throws MapquestApiException, CoordenadasDeFarmaciasRepositoryException, FirebaseAuthException {
        String direccion = "sarasa";

        Mockito.when(firebaseAuthService.isAuthenticated(token)).thenReturn(false);

        ResponseEntity<?> responseEntity = farmaciasCercanasController.getFarmaciasCercanas(direccion, 0.6, token);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void testHttpOkCuandoElUsuarioEstaAutenticado() throws MapquestApiException, CoordenadasDeFarmaciasRepositoryException, FirebaseAuthException {
        String direccion = "sarasa";

        Mockito.when(firebaseAuthService.isAuthenticated(token)).thenReturn(true);

        ResponseEntity<?> responseEntity = farmaciasCercanasController.getFarmaciasCercanas(direccion, 0.6, token);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void testHttpBadRequestCuandoLaDireccionEstaVacia() throws MapquestApiException, CoordenadasDeFarmaciasRepositoryException, FirebaseAuthException {
        String direccion = "";

        Mockito.when(firebaseAuthService.isAuthenticated(token)).thenReturn(true);

        ResponseEntity<?> responseEntity = farmaciasCercanasController.getFarmaciasCercanas(direccion, 0.6, token);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

}
