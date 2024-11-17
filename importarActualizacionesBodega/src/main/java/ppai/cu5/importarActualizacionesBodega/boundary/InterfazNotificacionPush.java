package ppai.cu5.importarActualizacionesBodega.boundary;

import ppai.cu5.importarActualizacionesBodega.DTO.DTOVino;
import ppai.cu5.importarActualizacionesBodega.Observer.IObservadorNovedad;

import java.util.List;

public class InterfazNotificacionPush implements IObservadorNovedad {


    @Override
    public void enviarNotificacion(List<DTOVino> novedadesVino, List<String> suscriptores) {
        for (String suscriptor : suscriptores) {
            StringBuilder mensaje = new StringBuilder()
                    .append("Hola ")
                    .append(suscriptor)
                    .append(",\n\nSe encuentran nuevas actualizaciones de los siguientes vinos:\n");
            for (DTOVino vino : novedadesVino) {
                mensaje.append("- ").append(vino.toString()).append("\n");
            }

            System.out.println(mensaje);
        }
    }
}
