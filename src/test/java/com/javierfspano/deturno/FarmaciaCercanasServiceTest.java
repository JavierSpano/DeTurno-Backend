package com.javierfspano.deturno;

import com.javierfspano.deturno.entities.Farmacia;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
		Double radio = 0.6;

		Coordenadas expectedCoordenadas = new Coordenadas();
		List<String> expectedIdsCercanos = new ArrayList<>();
		expectedIdsCercanos.add("fafafa");
		List<Farmacia> expectedFarmacias = new ArrayList<>();
		Farmacia expectedFarmacia = new Farmacia();
		expectedFarmacia.setNombre("Farmacity");
		expectedFarmacias.add(expectedFarmacia);

		Mockito.when(geoCodingService.getCoordenadas(direccion)).thenReturn(expectedCoordenadas);
		Mockito.when(coordenadasDeFarmaciasRepository.getIdsCercanos(expectedCoordenadas, radio)).thenReturn(expectedIdsCercanos);
		Mockito.when(farmaciasRepository.get(expectedIdsCercanos)).thenReturn(expectedFarmacias);

		FarmaciasCercanas farmaciasCercanas = farmaciasCercanasService.getFarmaciasCercanasPorTexo(direccion, radio);

		assertEquals(expectedCoordenadas, farmaciasCercanas.getCentroDelMapa());
		assertEquals(expectedFarmacias, farmaciasCercanas.getFarmacias());
	}


	@Test
	public void testFarmaciasCercanasServiceNoRetornaNulo() throws MapquestApiException, CoordenadasDeFarmaciasRepositoryException {
		String direccion = "sarasa";
		Double radio = 0.6;

		FarmaciasCercanas farmaciasCercanas = farmaciasCercanasService.getFarmaciasCercanasPorTexo(direccion,radio);
		assertNotNull(farmaciasCercanas);

	}
	
	@Test
	public void testFarmaciasCercanasServiceUsaGeocodingCoord() throws MapquestApiException, CoordenadasDeFarmaciasRepositoryException {
		Double radio = 0.6;
		String lat = "-45.456453456465";
		String lng = "-35.4564564";

		
		Coordenadas expectedCoordenadas = new Coordenadas(lat,lng);
		List<String> expectedIdsCercanos = new ArrayList<>();
		expectedIdsCercanos.add("fafafa");
		List<Farmacia> expectedFarmacias = new ArrayList<>();
		Farmacia expectedFarmacia = new Farmacia();
		expectedFarmacia.setNombre("Farmacity");
		expectedFarmacias.add(expectedFarmacia);

		Mockito.when(coordenadasDeFarmaciasRepository.getIdsCercanos(expectedCoordenadas, radio)).thenReturn(expectedIdsCercanos);
		Mockito.when(farmaciasRepository.get(expectedIdsCercanos)).thenReturn(expectedFarmacias);

		FarmaciasCercanas farmaciasCercanas = farmaciasCercanasService.getFarmaciasCercanasPorCoordenadas(expectedCoordenadas, radio);

		assertEquals(expectedCoordenadas, farmaciasCercanas.getCentroDelMapa());
		assertEquals(expectedFarmacias, farmaciasCercanas.getFarmacias());	
	}

}
