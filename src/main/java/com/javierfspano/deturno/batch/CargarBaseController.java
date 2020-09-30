package com.javierfspano.deturno.batch;
 
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
 



@RestController
public class CargarBase {


    @Value("${firestore.coleccionDeFarmacias}")
    private String referenciaDeFarmacias;
 
    
    @PostMapping("/cargarBase")
	public  void Cargar(@RequestParam String codigo) {
		
	       
		Firestore db = FirestoreClient.getFirestore();
		String nombreArchivo = "farmacias.xlsx";
		String rutaArchivo = "C:\\" + nombreArchivo;

 
		try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {
			//leer archivo excel
			XSSFWorkbook worbook = new XSSFWorkbook(file);
			//obtener la hoja que se va leer
			XSSFSheet sheet = worbook.getSheetAt(0);
			//obtener todas las filas de la hoja excel
			Iterator<Row> rowIterator = sheet.iterator();
			
 
			Row firstRow = sheet.rowIterator().next();

			DataFormatter dataFormatter = new DataFormatter();
			// se recorre cada fila hasta el final
			for(Row row : sheet) {  
				row = rowIterator.next();
				if (row != firstRow) {	
					//se verifica que no esta en la primera fila y se cargan los valores de las celdas
				    Map<String, Object> docData = new HashMap<>();
				    docData.put("barrio", row.getCell(8).toString());
				    docData.put("calle_altura", dataFormatter.formatCellValue(row.getCell(6)));
				    docData.put("calle_nombre", row.getCell(5).toString());
				    docData.put("codigo_postal", dataFormatter.formatCellValue(row.getCell(10)));
				    docData.put("lat", row.getCell(2).toString());
				    docData.put("lng", row.getCell(1).toString());
				    docData.put("nombre", "Farmacity");
				    docData.put("telefono", dataFormatter.formatCellValue(row.getCell(3)));

				    ApiFuture<WriteResult> future = db.collection("farmacias").document(dataFormatter.formatCellValue(row.getCell(0))).set(docData);
				    System.out.println("Update time : " + future.get().getUpdateTime() + row.getCell(0).getNumericCellValue());
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
    
}