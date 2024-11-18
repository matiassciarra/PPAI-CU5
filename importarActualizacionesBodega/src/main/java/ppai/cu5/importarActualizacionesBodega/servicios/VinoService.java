package ppai.cu5.importarActualizacionesBodega.servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ppai.cu5.importarActualizacionesBodega.entidades.Vino;
import ppai.cu5.importarActualizacionesBodega.repositorios.VinoRepository;

@Service
public class VinoService {

    @Autowired
    VinoRepository vinoRepository;

    public void guardarVino(Vino vino) {
        vinoRepository.save(vino);
    }
}
