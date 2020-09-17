package com.javierfspano.deturno.repositories;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.javierfspano.deturno.entities.respuestamapquest.Coordenadas;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public class CoordenadasDeFarmaciasRepositoryImpl implements CoordenadasDeFarmaciasRepository {

    @Value("${firebase.database}")
    private String firebaseDatabase;

    @Override
    public List<String> getIdsCercanos(Coordenadas coordenadas) {
        CompletableFuture<List<String>> future = new CompletableFuture<>();
        List<String> ids = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        GeoFire geoFire = new GeoFire(ref.child(firebaseDatabase));

        GeoLocation geoLocation = new GeoLocation(Double.parseDouble(coordenadas.getLat()), Double.parseDouble(coordenadas.getLng()));
        GeoQuery geoQuery = geoFire.queryAtLocation(geoLocation, 0.6);

        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                ids.add(key);
            }

            @Override
            public void onKeyExited(String s) {
            }

            @Override
            public void onKeyMoved(String s, GeoLocation geoLocation) {
            }

            @Override
            public void onGeoQueryReady() {
                future.complete(ids);
            }

            @Override
            public void onGeoQueryError(DatabaseError error) {
                future.completeExceptionally(error.toException());
            }
        });

        //TODO "falta manejo de error"
        return future.getNow(Collections.emptyList());
    }
}
