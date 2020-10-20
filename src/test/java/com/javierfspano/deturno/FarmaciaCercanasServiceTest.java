package com.javierfspano.deturno;

import com.javierfspano.deturno.entities.FarmaciasCercanas;
import com.javierfspano.deturno.entities.respuestamapquest.Coordenadas;
import com.javierfspano.deturno.exceptions.CoordenadasDeFarmaciasRepositoryException;
import com.javierfspano.deturno.exceptions.MapquestApiException;
import com.javierfspano.deturno.repositories.CoordenadasDeFarmaciasRepository;
import com.javierfspano.deturno.repositories.FarmaciasRepository;
import com.javierfspano.deturno.services.FarmaciasCercanasService;
import com.javierfspano.deturno.services.GeoCodingService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class FarmaciaCercanasServiceTest {

	@Mock
	private GeoCodingService geoCodingService;

	@Mock
	private CoordenadasDeFarmaciasRepository coordenadasDeFarmaciasRepository;

	@Mock
	private FarmaciasRepository farmaciasRepository;

	private FarmaciasCercanasService farmaciasCercanasService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		farmaciasCercanasService = new FarmaciasCercanasService(geoCodingService, coordenadasDeFarmaciasRepository, farmaciasRepository);
	}

	@Test
	public void testFarmaciasCercanasServiceUsaGeocoding() throws MapquestApiException, CoordenadasDeFarmaciasRepositoryException {
		String direccion = "sarasa";
		farmaciasCercanasService.getFarmaciasCercanasPorTexo(direccion, 0.6);
		Mockito.verify(geoCodingService).getCoordenadas(direccion);
	}


	@Test
	public void testFarmaciasCercanasServiceNoRetornaNulo() throws MapquestApiException, CoordenadasDeFarmaciasRepositoryException {
		String direccion = "sarasa";
		FarmaciasCercanas farmaciasCercanas = farmaciasCercanasService.getFarmaciasCercanasPorTexo(direccion,0.6);
		assertNotNull(farmaciasCercanas);
	}
	
	@Test
	public void testFarmaciasCercanasServiceUsaGeocodingCoord() throws MapquestApiException, CoordenadasDeFarmaciasRepositoryException {
		FarmaciasCercanas farmaciasCercanas = farmaciasCercanasService.getFarmaciasCercanasPorCoordenadas(new Coordenadas("-45.456453456465", "-35.4564564"), 0.6);
		assertNotNull(farmaciasCercanas);
	}

}
