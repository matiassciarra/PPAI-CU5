package ppai.cu5.importarActualizacionesBodega.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ppai.cu5.importarActualizacionesBodega.entidades.Vino;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOVino {
    private String nombreBodega;
    private int añada;
    private String notaDeCataBodega;
    private double precio;
    private LocalDate fechaActualizacion;
    private List<DTOVarietal> varietales;
    //todo-> 0 actualizado || 1 creado
    private boolean accion;

    public DTOVino(Vino vino, boolean accion) {
        this.nombreBodega = vino.getBodega().getNombre();
        this.añada = vino.getAñada();
        this.notaDeCataBodega = vino.getNotaDeCataBodega();
        this.precio = vino.getPrecioARS();
        this.fechaActualizacion = vino.getFechaActualizacion();
        this.varietales = vino.getVarietales().stream().map(DTOVarietal::new).toList();
        this.accion = accion;
    }
}
