package com.javierfspano.deturno.repositories;

import com.javierfspano.deturno.entities.respuestamapquest.Coordenadas;

import java.util.List;

public interface CoordenadasDeFarmaciasRepository {

    List<String> getIdsCercanos(Coordenadas coordenadas);
}
