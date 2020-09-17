package com.javierfspano.deturno.controllers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.javierfspano.deturno.utils.FirebaseAuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;

@RestController()
public class FarmaciasCercanasController {
//
//	@Value("${mapquestapi.key}")
//	private String KEY;
//
//	@Value("${mapquestapi.url}")
//	private String URL;
//
//	@Autowired
//	GeoService geoService;
//
//
//	@GetMapping("/farmacias_cercanas")
//	public ResponseEntity<?> getFarmaciasCercanas(@RequestParam String direccion, @RequestHeader(name = "IdToken") String idToken) throws InterruptedException, ExecutionException, FirebaseAuthException {
//		if (FirebaseAuthUtil.isAuthenticated(idToken)) {
//
//			ResponseApi json = new ResponseApi();
//			RestTemplate restTemplate = new RestTemplate();
//
//			try {
//				json = restTemplate.getForObject(URL + KEY + "&location=" + direccion, ResponseApi.class);
//			} catch (HttpClientErrorException e) {
//				System.out.println("Error llamando a mapquestapi " + e);
//				return ResponseEntity
//						.status(HttpStatus.INTERNAL_SERVER_ERROR).
//								body(new RsErrorResponse("GEO_API_00", "Momentáneamente fuera de servicio."));
//
//			} catch (Exception e) {
//				System.out.println("Error generico " + e);
//			}
//
//			return ResponseEntity
//					.status(HttpStatus.OK).
//							body(geoService.getFarmaciasCercanas(json.getResults().get(0).getLocations().get(0).getLatLng()).get());
//
//		} else {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//		}
//	}

}
