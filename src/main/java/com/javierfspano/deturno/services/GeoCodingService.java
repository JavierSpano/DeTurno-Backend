package com.javierfspano.deturno.services;

import com.javierfspano.deturno.entities.respuestamapquest.Coordenadas;

public interface GeoCodingService {

    Coordenadas getCoordenadas(String direccion);
}
