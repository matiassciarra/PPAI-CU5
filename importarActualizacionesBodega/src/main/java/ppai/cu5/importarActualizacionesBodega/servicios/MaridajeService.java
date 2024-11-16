package ppai.cu5.importarActualizacionesBodega.servicios;

import java.util.List;

import ppai.cu5.importarActualizacionesBodega.entidades.Maridaje;
import ppai.cu5.importarActualizacionesBodega.repositorios.MaridajeRepositorio;

public class MaridajeService {
    MaridajeRepositorio repo;

    public MaridajeService() {
        this.repo = new MaridajeRepositorio();
    }

    public List<Maridaje> obtenerTodosLosMaridajes() {
        return repo.findAll();
    }

}
