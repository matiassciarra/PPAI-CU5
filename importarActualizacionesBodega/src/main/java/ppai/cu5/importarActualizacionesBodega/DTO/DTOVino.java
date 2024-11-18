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
    private String nombre;
    private int añada;
    private String notaDeCataBodega;
    private double precio;
    private LocalDate fechaActualizacion;
    private List<DTOVarietal> varietales;
    //todo-> 0 actualizado || 1 creado
    private boolean creado;

    public DTOVino(Vino vino, boolean accion) {
        this.nombre = vino.getNombre();
        this.añada = vino.getAñada();
        this.nombreBodega = vino.getBodega().getNombre();
        this.notaDeCataBodega = vino.getNotaDeCataBodega();
        this.precio = vino.getPrecioARS();
        this.fechaActualizacion = vino.getFechaActualizacion();
        this.varietales = vino.getVarietales().stream().map(DTOVarietal::new).toList();
        this.creado = accion;
    }

    @Override
    public String toString() {
        return "🍷 DTOVino {" +
                "\n   🏛️ Bodega: '" + nombreBodega + '\'' +
                "\n   🍇 Nombre del Vino: '" + nombre + '\'' +
                "\n   📅 Añada: " + añada +
                "\n   📝 Nota de Cata: '" + notaDeCataBodega + '\'' +
                "\n   🍷 Varietales: " + (varietales != null && !varietales.isEmpty() ? varietales : "No especificados") +
                "\n   💰 Precio: $" + String.format("%.2f", precio) +
                "\n}";
    }

}
