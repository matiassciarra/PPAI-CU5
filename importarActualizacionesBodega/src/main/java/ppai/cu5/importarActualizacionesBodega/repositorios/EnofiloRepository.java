package ppai.cu5.importarActualizacionesBodega.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ppai.cu5.importarActualizacionesBodega.entidades.Enofilo;

@Repository
public interface EnofiloRepository extends CrudRepository <Enofilo,Long> {


}
