package ppai.cu5.importarActualizacionesBodega.Observer;

import java.util.List;

public interface ISujeto {
    void notificar (List<String> UsuariosNotificar);
    void quitar(IObservadorNovedad observador);
    void suscribir(IObservadorNovedad observador);

}
