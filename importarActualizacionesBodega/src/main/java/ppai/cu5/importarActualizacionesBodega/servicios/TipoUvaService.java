package ppai.cu5.importarActualizacionesBodega.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ppai.cu5.importarActualizacionesBodega.entidades.TipoUva;
import ppai.cu5.importarActualizacionesBodega.repositorios.TipoUvaRepository;
@Service
public class TipoUvaService {
    @Autowired
    TipoUvaRepository repository;

    public List<TipoUva> obtenerTiposUva() {
        return (List<TipoUva>) repository.findAll();
    }
}
