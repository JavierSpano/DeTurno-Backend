package com.javierfspano.deturno.entities.respuestamapquest;


public class Coordenadas {

    private String lat;

    private String lng;

    public Coordenadas() {
    }

    public Coordenadas(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

}
