package com.javierfspano.deturno.services;

import com.javierfspano.deturno.config.MapquestCredentials;
import com.javierfspano.deturno.config.ProveedorDeCredenciales;
import com.javierfspano.deturno.entities.respuestamapquest.Coordenadas;
import com.javierfspano.deturno.entities.respuestamapquest.RespuestaMapquest;
import com.javierfspano.deturno.exceptions.MapquestApiException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapquestGeoCodingServiceImpl implements GeoCodingService {

    private final ProveedorDeCredenciales proveedorDeCredenciales;

    public MapquestGeoCodingServiceImpl(ProveedorDeCredenciales proveedorDeCredenciales) {
        this.proveedorDeCredenciales = proveedorDeCredenciales;
    }

    @Override
    public Coordenadas getCoordenadas(String direccion) throws MapquestApiException {
        MapquestCredentials credentials = proveedorDeCredenciales.obtenerCredencialesDeMapquest();

        RespuestaMapquest json = new RespuestaMapquest();
        RestTemplate restTemplate = new RestTemplate();
        String url = credentials.getUrl() + "?key=" + credentials.getKey() + "&location=" + direccion;
        json = restTemplate.getForObject(url, RespuestaMapquest.class);
        if (json == null) {
            throw new MapquestApiException();
        }
        return json.getResults().get(0).getLocations().get(0).getLatLng();

    }
}
