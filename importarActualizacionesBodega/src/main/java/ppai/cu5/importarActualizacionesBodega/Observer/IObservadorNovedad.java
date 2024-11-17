package ppai.cu5.importarActualizacionesBodega.Observer;

import java.util.List;

public interface IObservadorNovedad {
    void enviarNotificacion(String novedadesVino, List<String> suscriptores);
}
