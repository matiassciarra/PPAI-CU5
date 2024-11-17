package ppai.cu5.importarActualizacionesBodega.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ppai.cu5.importarActualizacionesBodega.entidades.Enofilo;
import ppai.cu5.importarActualizacionesBodega.entidades.Maridaje;
import ppai.cu5.importarActualizacionesBodega.repositorios.EnofiloRepository;
import ppai.cu5.importarActualizacionesBodega.repositorios.MaridajeRepository;

import java.util.List;

@Service
public class EnofiloService {

    @Autowired
    EnofiloRepository enofiloRepository;

    public List<Enofilo> obtenerTodosLosEnofilos(){
        return (List<Enofilo>) enofiloRepository.findAll();
    }
}
