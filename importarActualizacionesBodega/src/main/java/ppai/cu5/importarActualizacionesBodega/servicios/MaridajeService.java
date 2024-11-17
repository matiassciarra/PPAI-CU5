package ppai.cu5.importarActualizacionesBodega.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ppai.cu5.importarActualizacionesBodega.entidades.Maridaje;
import ppai.cu5.importarActualizacionesBodega.repositorios.MaridajeRepository;

import java.util.List;

@Service
public class MaridajeService {

    @Autowired
    MaridajeRepository maridajeRepository;

    public List<Maridaje> obtenerTodosLosMaridajes(){
        return (List<Maridaje>) maridajeRepository.findAll();
    }

}
