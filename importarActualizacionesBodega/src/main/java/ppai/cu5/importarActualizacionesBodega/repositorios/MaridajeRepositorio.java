package ppai.cu5.importarActualizacionesBodega.repositorios;

import ppai.cu5.importarActualizacionesBodega.entidades.Maridaje;

import java.util.ArrayList;
import java.util.List;

public class MaridajeRepositorio {

    public List<Maridaje> findAll() {
        Maridaje maridaje1 = new Maridaje(1L, "Lo bueno se hace sentir", "Fideos");
        Maridaje maridaje2 = new Maridaje(2L, "Lo bueno se hace mejorar", "Chorizo");
        Maridaje maridaje3 = new Maridaje(3L, "Lo bueno se hace divertir", "Mate");

        return new ArrayList<>(List.of(maridaje1, maridaje2, maridaje3));
    }

}
