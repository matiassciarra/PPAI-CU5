package ppai.cu5.importarActualizacionesBodega.boundary;

import ppai.cu5.importarActualizacionesBodega.Observer.IObservadorNovedad;

import java.util.List;

public class InterfazNotificacionPush implements IObservadorNovedad {


    @Override
    public void enviarNotificacion(String novedadesVino, List<String> suscriptores) {
        suscriptores.forEach(suscriptor -> {
            System.out.println("Hola," + suscriptor + novedadesVino);
        });
    }


}
