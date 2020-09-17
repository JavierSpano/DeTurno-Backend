package com.javierfspano.deturno.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.javierfspano.deturno.entities.respuestamapquest.Coordenadas;

import java.util.List;

public class FarmaciasCercanas {

    @JsonProperty("centro_del_mapa")
    private Coordenadas centroDelMapa;

    private List<Farmacia> farmacias;

    public FarmaciasCercanas() {
    }

    public FarmaciasCercanas(Coordenadas centroDelMapa, List<Farmacia> farmacias) {
        this.centroDelMapa = centroDelMapa;
        this.farmacias = farmacias;
    }

    public Coordenadas getCentroDelMapa() {
        return centroDelMapa;
    }

    public void setCentroDelMapa(Coordenadas centroDelMapa) {
        this.centroDelMapa = centroDelMapa;
    }

    public List<Farmacia> getFarmacias() {
        return farmacias;
    }

    public void setFarmacias(List<Farmacia> farmacias) {
        this.farmacias = farmacias;
    }
}
