package ppai.cu5.importarActualizacionesBodega.boundary;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConfigAPI {

    public List<String> obtenerActualizacionesVinos(String idBodega) {
        String urlString = "http://localhost:8080/api/externa/bodega/" + idBodega + "/csv";
        List<String> result = new ArrayList<>();

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            rd.readLine();
            while ((line = rd.readLine()) != null) {
                result.add(line);
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
