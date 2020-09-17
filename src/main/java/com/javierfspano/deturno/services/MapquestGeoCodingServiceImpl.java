package com.javierfspano.deturno.services;

import com.javierfspano.deturno.config.MapquestCredentials;
import com.javierfspano.deturno.config.ProveedorDeCredenciales;
import com.javierfspano.deturno.entities.respuestamapquest.Coordenadas;
import com.javierfspano.deturno.entities.respuestamapquest.RespuestaMapquest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapquestGeoCodingServiceImpl implements GeoCodingService {
	@Value("${mapquestapi.key}")
	private String KEY;
	@Value("${mapquestapi.url}")
	private String URL;

    private ProveedorDeCredenciales proveedorDeCredenciales;

    public MapquestGeoCodingServiceImpl(ProveedorDeCredenciales proveedorDeCredenciales) {
        this.proveedorDeCredenciales = proveedorDeCredenciales;
    }

    @Override
    public Coordenadas getCoordenadas(String direccion) {
        MapquestCredentials credentials = proveedorDeCredenciales.obtenerCredencialesDeMapquest();

		RespuestaMapquest json = new RespuestaMapquest();
		RestTemplate restTemplate = new RestTemplate();
 		json = restTemplate.getForObject(URL + KEY + "&location=" + direccion, RespuestaMapquest.class);

		return json.getResults().get(0).getLocations().get(0).getLatLng();
	
    }
}
