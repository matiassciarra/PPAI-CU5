package ppai.cu5.importarActualizacionesBodega.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ppai.cu5.importarActualizacionesBodega.entidades.Varietal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOVarietal {
    private String nombreUva;
    private int porcentajeComposicion;

    public DTOVarietal(Varietal varietal) {
        nombreUva = varietal.getTipoUva().getNombre();
        porcentajeComposicion = varietal.getPorcentajeComposicion();
    }

    @Override
    public String toString() {
        return "Composición de Vino" +
                "Nombre de la uva: '" + nombreUva + '\'' +
                ", Porcentaje de composición: " + porcentajeComposicion + '%' +
                '\n';
    }

}
