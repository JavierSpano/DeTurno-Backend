package com.javierfspano.deturno.entities.respuestamapquest;

import java.util.List;

public class ResultadoMapquest {

    private UbicacionProveida providedLocation;

    private List<Ubicacion> locations;

    public UbicacionProveida getProvidedLocation() {
        return providedLocation;
    }

    public void setProvidedLocation(UbicacionProveida providedLocation) {
        this.providedLocation = providedLocation;
    }

    public List<Ubicacion> getLocations() {
        return locations;
    }

    public void setLocations(List<Ubicacion> locations) {
        this.locations = locations;
    }

}
