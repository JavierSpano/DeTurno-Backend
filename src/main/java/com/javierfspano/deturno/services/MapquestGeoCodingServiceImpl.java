package com.javierfspano.deturno.services;

import com.javierfspano.deturno.config.MapquestCredentials;
import com.javierfspano.deturno.config.ProveedorDeCredenciales;
import com.javierfspano.deturno.entities.respuestamapquest.Coordenadas;
import org.springframework.stereotype.Service;

@Service
public class MapquestGeoCodingServiceImpl implements GeoCodingService {

    private ProveedorDeCredenciales proveedorDeCredenciales;

    public MapquestGeoCodingServiceImpl(ProveedorDeCredenciales proveedorDeCredenciales) {
        this.proveedorDeCredenciales = proveedorDeCredenciales;
    }

    @Override
    public Coordenadas getCoordenadas(String direccion) {
        MapquestCredentials credentials = proveedorDeCredenciales.obtenerCredencialesDeMapquest();

        return null;
    }
}
