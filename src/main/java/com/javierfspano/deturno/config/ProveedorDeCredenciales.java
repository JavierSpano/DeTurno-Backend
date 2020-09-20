package com.javierfspano.deturno.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Component
public class ProveedorDeCredenciales {

    private final Environment environment;

    public ProveedorDeCredenciales(Environment environment) {
        this.environment = environment;
    }

    public InputStream obtenerCredencialesDeFirebase() throws JsonProcessingException {
        FirebaseCredentials firebaseCredential = new FirebaseCredentials();
        firebaseCredential.setType(environment.getRequiredProperty("FIREBASE_TYPE"));
        firebaseCredential.setProject_id(environment.getRequiredProperty("FIREBASE_PROJECT_ID"));
        firebaseCredential.setPrivate_key_id("FIREBASE_PRIVATE_KEY_ID");
        firebaseCredential.setPrivate_key(environment.getRequiredProperty("FIREBASE_PRIVATE_KEY").replace("\\n", "\n"));
        firebaseCredential.setClient_email(environment.getRequiredProperty("FIREBASE_CLIENT_EMAIL"));
        firebaseCredential.setClient_id(environment.getRequiredProperty("FIREBASE_CLIENT_ID"));
        firebaseCredential.setAuth_uri(environment.getRequiredProperty("FIREBASE_AUTH_URI"));
        firebaseCredential.setToken_uri(environment.getRequiredProperty("FIREBASE_TOKEN_URI"));
        firebaseCredential.setAuth_provider_x509_cert_url(environment.getRequiredProperty("FIREBASE_AUTH_PROVIDER_X509_CERT_URL"));
        firebaseCredential.setClient_x509_cert_url(environment.getRequiredProperty("FIREBASE_CLIENT_X509_CERT_URL"));
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(firebaseCredential);

        return new ByteArrayInputStream(jsonString.getBytes());
    }

    public MapquestCredentials obtenerCredencialesDeMapquest() {
        MapquestCredentials credentials = new MapquestCredentials();
        credentials.setUrl(environment.getRequiredProperty("MAPQUEST_URL"));
        credentials.setKey(environment.getRequiredProperty("MAPQUEST_KEY"));
        return credentials;
    }
}
