package ppai.cu5.importarActualizacionesBodega.servicios;

import ppai.cu5.importarActualizacionesBodega.entidades.Bodega;
import ppai.cu5.importarActualizacionesBodega.repositorios.BodegaRepositorio;

import java.util.List;

public class BodegaService {
    BodegaRepositorio repositorio;

    public BodegaService() {
        repositorio = new BodegaRepositorio();
    }

    public List<Bodega> obtenerTodasLasBodegas() {
        return repositorio.findAll();
    }
}
