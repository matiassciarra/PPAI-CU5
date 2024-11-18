package ppai.cu5.importarActualizacionesBodega.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ppai.cu5.importarActualizacionesBodega.entidades.Vino;


@Repository
public interface VinoRepository extends CrudRepository<Vino, Long> {
}
