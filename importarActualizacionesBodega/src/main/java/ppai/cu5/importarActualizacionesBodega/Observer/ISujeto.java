package ppai.cu5.importarActualizacionesBodega.Observer;

import ppai.cu5.importarActualizacionesBodega.DTO.DTOVino;

import java.util.List;

public interface ISujeto {
    void notificar (List<DTOVino> novedadesVino, List<String> UsuariosNotificar);
    void quitar(IObservadorNovedad observador);
    void suscribir(IObservadorNovedad observador);

}
