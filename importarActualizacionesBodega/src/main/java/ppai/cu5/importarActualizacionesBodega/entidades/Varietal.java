package ppai.cu5.importarActualizacionesBodega.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Varietal {
    
    private String descripcion;
    private int porcentajeComposicion;
    private TipoUva tipoUva;


}
