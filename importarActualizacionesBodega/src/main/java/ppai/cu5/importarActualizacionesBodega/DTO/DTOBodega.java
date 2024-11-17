package ppai.cu5.importarActualizacionesBodega.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ppai.cu5.importarActualizacionesBodega.entidades.Bodega;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DTOBodega {
    private Long id;
    private String nombre;

    public DTOBodega(Bodega bodega){
        id = bodega.getId();
        nombre = bodega.getNombre();
    }
}
