package utn.frc.apiBodegas;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;



@RestController
@RequestMapping("/api/externa/bodega")
public class ConfigAPIController {

    @GetMapping("/{idBodega}/csv")
    public ResponseEntity<String> getBodegaAsCSV(@PathVariable String idBodega) {
        // Generar el contenido del CSV
        List<String[]> dataLines = GeneradorAleatorioVinosBodega.crearListaVinosAleatorios(idBodega);

        StringWriter stringWriter = new StringWriter();
        try (PrintWriter csvWriter = new PrintWriter(stringWriter)) {
            for (String[] dataLine : dataLines) {
                csvWriter.println(String.join(";", dataLine));
            }
        }

        // Configurar los encabezados HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=bodega.csv");
        headers.add("Content-Type", "text/csv");

        return new ResponseEntity<>(stringWriter.toString(), headers, HttpStatus.OK);
    }

}
