package com.javierfspano.deturno.services;

import com.javierfspano.deturno.entities.respuestamapquest.Coordenadas;
import com.javierfspano.deturno.exceptions.MapquestApiException;

public interface GeoCodingService {

    Coordenadas getCoordenadas(String direccion) throws MapquestApiException;
}
