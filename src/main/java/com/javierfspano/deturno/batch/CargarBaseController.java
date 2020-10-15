package com.javierfspano.deturno.batch;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@RestController
public class CargarBaseController {


    @PostMapping("/cargarBase")
    public void CargaDataSet() throws InterruptedException, ExecutionException {


        Firestore db = FirestoreClient.getFirestore();
        String nombreArchivo = "farmacias.xlsx";
        String rutaArchivo = "C:\\Users\\Emiliano\\Desktop\\excel\\" + nombreArchivo;

        // Se realiza el borrado de la base primero para despues cargarla
        ApiFuture<QuerySnapshot> future2 =
                db.collection("farmacias").get();
        List<QueryDocumentSnapshot> documents = future2.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            ApiFuture<WriteResult> future1 = db.collection("farmacias").document(document.getId()).delete();
        }


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
            for (Row row : sheet) {
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
                    // System.out.println("Update time : " + future.get().getUpdateTime() + row.getCell(0).getNumericCellValue());
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }


}