package ppai.cu5.importarActualizacionesBodega.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ppai.cu5.importarActualizacionesBodega.entidades.Bodega;
import ppai.cu5.importarActualizacionesBodega.repositorios.BodegaRepository;

import java.util.List;

@Service
public class BodegaService {

    @Autowired
    BodegaRepository repositorio;

    @Autowired
    VinoService vinoService;

    public List<Bodega> obtenerTodasLasBodegas() {
        return (List<Bodega>) repositorio.findAll();
    }

    public void guardarBodegas(List<Bodega> bodegas) {
        for (Bodega bodega : bodegas) {
            bodega.getVinos().forEach(vino -> vinoService.guardarVino(vino));
            repositorio.save(bodega);
        }
    }

}
