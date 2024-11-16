package ppai.cu5.importarActualizacionesBodega.servicios;

import java.util.List;

import ppai.cu5.importarActualizacionesBodega.entidades.TipoUva;
import ppai.cu5.importarActualizacionesBodega.repositorios.TiposUvaRepositorio;

public class TipoUvaService {

    TiposUvaRepositorio repo;

    public TipoUvaService() {
        this.repo = new TiposUvaRepositorio();
    }

    public List<TipoUva> obtenerTiposUva() {
        return repo.findAll();
    }
}
