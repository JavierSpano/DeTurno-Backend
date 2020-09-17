package com.javierfspano.deturno.repositories;

import com.javierfspano.deturno.entities.Farmacias;

import java.util.List;

public interface FarmaciasRepository {
    List<Farmacias> get(List<String> ids);
}
