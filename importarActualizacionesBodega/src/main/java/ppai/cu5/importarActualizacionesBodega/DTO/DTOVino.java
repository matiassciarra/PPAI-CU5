package ppai.cu5.importarActualizacionesBodega.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ppai.cu5.importarActualizacionesBodega.entidades.Vino;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
        String varietalStr = varietales != null && !varietales.isEmpty()
                ? varietales.stream()
                .map(varietal -> "  - Nombre de la uva: " + varietal.getNombreUva() + ", Porcentaje de composición: " + varietal.getPorcentajeComposicion() + "%")
                .collect(Collectors.joining("\n "))
                : "No especificados";

        return "🍷 Vino " + nombre +
                "\n   🏛️ Bodega: '" + nombreBodega + '\'' +
                "\n   📅 Añada: " + añada +
                "\n   📝 Nota de Cata: '" + notaDeCataBodega + '\'' +
                "\n   🍇 Varietales: \n" + varietalStr +
                "\n   💰 Precio: $" + String.format("%.2f", precio) +
                "\n";
    }


}
