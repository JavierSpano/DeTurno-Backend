package com.javierfspano.deturno.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.javierfspano.deturno.entities.respuestamapquest.Coordenadas;

import java.util.List;

public class FarmaciasCercanas {

    @JsonProperty("centro_del_mapa")
    private Coordenadas centroDelMapa;

    private List<Farmacias> farmacias;

    public FarmaciasCercanas() {
    }

    public FarmaciasCercanas(Coordenadas centroDelMapa, List<Farmacias> farmacias) {
        this.centroDelMapa = centroDelMapa;
        this.farmacias = farmacias;
    }

    public Coordenadas getCentroDelMapa() {
        return centroDelMapa;
    }

    public void setCentroDelMapa(Coordenadas centroDelMapa) {
        this.centroDelMapa = centroDelMapa;
    }

    public List<Farmacias> getFarmacias() {
        return farmacias;
    }

    public void setFarmacias(List<Farmacias> farmacias) {
        this.farmacias = farmacias;
    }
}
