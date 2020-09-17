package com.javierfspano.deturno.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.javierfspano.deturno.entities.Farmacias;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class FarmaciasRepositoryImpl implements FarmaciasRepository {
	ArrayList<Farmacias> response = new ArrayList();
	Firestore db = com.google.firebase.cloud.FirestoreClient.getFirestore();
    @Override
    public List<Farmacias> get(List<String> ids) {
    	
    	for(String str : ids)
    	{
    	DocumentReference docRef = db.collection("farmacias").document(str);
		ApiFuture<DocumentSnapshot> Apifuture = docRef.get();
		DocumentSnapshot document = null;
		try {
			document = Apifuture.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (document.exists()) {
			System.out.println("Datos de la farmacia" + document.getData());
			Farmacias dato = document.toObject(Farmacias.class);
			response.add(dato);
		} else {
			System.out.println("La farmacia no tiene datos asociados");
		}

    }
		return response;
    
   }
}
