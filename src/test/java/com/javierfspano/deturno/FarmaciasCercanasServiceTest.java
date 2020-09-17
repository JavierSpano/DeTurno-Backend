package com.javierfspano.deturno;

import com.javierfspano.deturno.entities.FarmaciasCercanas;
import com.javierfspano.deturno.repositories.CoordenadasDeFarmaciasRepository;
import com.javierfspano.deturno.repositories.FarmaciasRepository;
import com.javierfspano.deturno.services.FarmaciasCercanasService;
import com.javierfspano.deturno.services.GeoCodingService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class FarmaciasCercanasServiceTest {

	@Mock
	private GeoCodingService geoCodingService;

	@Mock
	private CoordenadasDeFarmaciasRepository coordenadasDeFarmaciasRepository;

	@Mock
	private FarmaciasRepository farmaciasRepository;

	private FarmaciasCercanasService farmaciasCercanasService;

	@BeforeAll
	public void setup() {
		MockitoAnnotations.initMocks(this);
		farmaciasCercanasService = new FarmaciasCercanasService(geoCodingService, coordenadasDeFarmaciasRepository, farmaciasRepository);
	}

	@Test
	public void testFarmaciasCercanasServiceUsaGeocoding() {
		String direccion = "sarasa";
		farmaciasCercanasService.getFarmaciasCercanas(direccion);
		Mockito.verify(geoCodingService).getCoordenadas(direccion);
	}


	@Test
	public void testFarmaciasCercanasServiceNoRetornaNulo() {
		String direccion = "sarasa";
		FarmaciasCercanas farmaciasCercanas = farmaciasCercanasService.getFarmaciasCercanas(direccion);
		assertNotNull(farmaciasCercanas);
	}

}
