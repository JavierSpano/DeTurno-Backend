package com.javierfspano.deturno.repositories;

import com.javierfspano.deturno.entities.Farmacia;

import java.util.List;

public interface FarmaciasRepository {
    List<Farmacia> get(List<String> ids);
}
