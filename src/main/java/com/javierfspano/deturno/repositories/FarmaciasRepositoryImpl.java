package com.javierfspano.deturno.repositories;

import com.javierfspano.deturno.entities.Farmacias;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FarmaciasRepositoryImpl implements FarmaciasRepository {

    @Override
    public List<Farmacias> get(List<String> ids) {
        return null;
    }
}
