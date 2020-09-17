package com.javierfspano.deturno.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.javierfspano.deturno.entities.Farmacia;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class FarmaciasRepositoryImpl implements FarmaciasRepository {

    @Value("${firestore.coleccionDeFarmacias}")
    private String referenciaDeFarmacias;

    @Override
    public List<Farmacia> get(List<String> ids) {
        List<Farmacia> farmacias = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        for (String id : ids) {
            DocumentReference docRef = db.collection(referenciaDeFarmacias).document(id);
            ApiFuture<DocumentSnapshot> Apifuture = docRef.get();
            DocumentSnapshot document = null;
            try {
                document = Apifuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            if (document != null && document.exists()) {
                Farmacia farmacia = document.toObject(Farmacia.class);
                farmacias.add(farmacia);
            }
        }
        return farmacias;
    }
}
