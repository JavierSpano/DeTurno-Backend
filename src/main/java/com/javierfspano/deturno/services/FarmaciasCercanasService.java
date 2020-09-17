package com.javierfspano.deturno.services;

import com.javierfspano.deturno.entities.Farmacias;
import com.javierfspano.deturno.entities.FarmaciasCercanas;
import com.javierfspano.deturno.entities.respuestamapquest.Coordenadas;
import com.javierfspano.deturno.repositories.CoordenadasDeFarmaciasRepository;
import com.javierfspano.deturno.repositories.FarmaciasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmaciasCercanasService {

    private GeoCodingService geoCodingService;

    private CoordenadasDeFarmaciasRepository coordenadasDeFarmaciasRepository;

    private FarmaciasRepository farmaciasRepository;

    public FarmaciasCercanasService(GeoCodingService geoCodingService, CoordenadasDeFarmaciasRepository coordenadasDeFarmaciasRepository, FarmaciasRepository farmaciasRepository) {
        this.geoCodingService = geoCodingService;
        this.coordenadasDeFarmaciasRepository = coordenadasDeFarmaciasRepository;
        this.farmaciasRepository = farmaciasRepository;
    }

    public FarmaciasCercanas getFarmaciasCercanas(String direccion){
        Coordenadas coordenadas = geoCodingService.getCoordenadas(direccion);

        List<String> ids = coordenadasDeFarmaciasRepository.getIdCercanos();

        List<Farmacias> farmacias = farmaciasRepository.get(ids);

        return new FarmaciasCercanas(coordenadas,farmacias);
    }

}
