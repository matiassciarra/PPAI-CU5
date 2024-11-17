package ppai.cu5.importarActualizacionesBodega.Observer;

import ppai.cu5.importarActualizacionesBodega.DTO.DTOVino;

import java.util.List;

public interface IObservadorNovedad {
    void enviarNotificacion(List<DTOVino> novedadesVino, List<String> suscriptores);
}
