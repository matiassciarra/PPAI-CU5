package ppai.cu5.importarActualizacionesBodega.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Siguiendo {

    private LocalDate fechaFin;
    private LocalDate fechaInicio;
    private Bodega bodega;

    public Boolean sosDeBodega(List<Bodega> seleccionBodega) {
        for (Bodega bodega : seleccionBodega) {
            if (this.bodega.equals(bodega)) {
                return true;
            }
        }
        return false;
    }
}
