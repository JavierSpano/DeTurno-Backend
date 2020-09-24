package com.javierfspano.deturno.services;

import com.javierfspano.deturno.entities.Farmacia;
import com.javierfspano.deturno.entities.FarmaciasCercanas;
import com.javierfspano.deturno.entities.respuestamapquest.Coordenadas;
import com.javierfspano.deturno.exceptions.CoordenadasDeFarmaciasRepositoryException;
import com.javierfspano.deturno.exceptions.MapquestApiException;
import com.javierfspano.deturno.repositories.CoordenadasDeFarmaciasRepository;
import com.javierfspano.deturno.repositories.FarmaciasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmaciasCercanasService {

    private final GeoCodingService geoCodingService;

    private final CoordenadasDeFarmaciasRepository coordenadasDeFarmaciasRepository;

    private final FarmaciasRepository farmaciasRepository;

    public FarmaciasCercanasService(GeoCodingService geoCodingService, CoordenadasDeFarmaciasRepository coordenadasDeFarmaciasRepository, FarmaciasRepository farmaciasRepository) {
        this.geoCodingService = geoCodingService;
        this.coordenadasDeFarmaciasRepository = coordenadasDeFarmaciasRepository;
        this.farmaciasRepository = farmaciasRepository;
    }

    public FarmaciasCercanas getFarmaciasCercanas(String direccion, Double radio) throws MapquestApiException, CoordenadasDeFarmaciasRepositoryException {
        Coordenadas coordenadas = geoCodingService.getCoordenadas(direccion);

        List<String> ids = coordenadasDeFarmaciasRepository.getIdsCercanos(coordenadas, radio);

        List<Farmacia> farmacias = farmaciasRepository.get(ids);

        return new FarmaciasCercanas(coordenadas, farmacias);
    }

}
