package com.javierfspano.deturno;

import com.google.firebase.auth.FirebaseAuthException;
import com.javierfspano.deturno.controllers.FarmaciasCercanasController;
import com.javierfspano.deturno.entities.Farmacia;
import com.javierfspano.deturno.entities.FarmaciasCercanas;
import com.javierfspano.deturno.entities.respuestamapquest.Coordenadas;
import com.javierfspano.deturno.exceptions.CoordenadasDeFarmaciasRepositoryException;
import com.javierfspano.deturno.exceptions.MapquestApiException;
import com.javierfspano.deturno.repositories.CoordenadasDeFarmaciasRepository;
import com.javierfspano.deturno.repositories.FarmaciasRepository;
import com.javierfspano.deturno.services.FarmaciasCercanasService;
import com.javierfspano.deturno.services.FirebaseAuthService;
import com.javierfspano.deturno.services.GeoCodingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


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
    public void testHttpOkCuandoElUsuarioEstaAutenticado() throws MapquestApiException, CoordenadasDeFarmaciasRepositoryException, FirebaseAuthException {
        String direccion = "sarasa";
        Double radio = 0.6;

        List<Farmacia> farmacias = new ArrayList<>();
        Coordenadas centroDelMapa =  new Coordenadas();
        FarmaciasCercanas expectedFarmaciasCercanas = new FarmaciasCercanas(centroDelMapa,farmacias);

        Mockito.when(firebaseAuthService.isAuthenticated(token)).thenReturn(true);
        Mockito.when(farmaciasCercanasService.getFarmaciasCercanasPorTexo(direccion,radio)).thenReturn(expectedFarmaciasCercanas);

        ResponseEntity<?> responseEntity = farmaciasCercanasController.farmaciasCercanasPorTexto(direccion, radio, token);
        FarmaciasCercanas responseBody = (FarmaciasCercanas) responseEntity.getBody();

        assertEquals(responseBody, expectedFarmaciasCercanas);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testHttpUnauthorizedCuandoElUsuarioNoEstaAutenticado() throws MapquestApiException, CoordenadasDeFarmaciasRepositoryException, FirebaseAuthException {
        String direccion = "sarasa";
        Double radio = 0.6;

        Mockito.when(firebaseAuthService.isAuthenticated(token)).thenReturn(false);

        ResponseEntity<?> responseEntity = farmaciasCercanasController.farmaciasCercanasPorTexto(direccion, radio, token);

        assertNull(responseEntity.getBody());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }


    @Test
    public void testHttpBadRequestCuandoLaDireccionEstaVacia() throws MapquestApiException, CoordenadasDeFarmaciasRepositoryException, FirebaseAuthException {
        String direccion = "";
        Double radio = 0.6;

        Mockito.when(firebaseAuthService.isAuthenticated(token)).thenReturn(true);

        ResponseEntity<?> responseEntity = farmaciasCercanasController.farmaciasCercanasPorTexto(direccion, radio, token);

        assertNull(responseEntity.getBody());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

}
