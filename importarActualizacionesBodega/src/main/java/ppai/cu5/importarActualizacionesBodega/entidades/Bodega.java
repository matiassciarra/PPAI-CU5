package ppai.cu5.importarActualizacionesBodega.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bodega {

    private Long id;

    private String descripcion;

    private LocalDate fechaUltimaActualizacion;

    private String nombre;

    private int periodoActualizacion; //numero de meses la cual la bodega se actualiza

    public Bodega(Long id, String descripcion, LocalDate fechaUltimaActualizacion, String nombre, int periodoActualizacion) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
        this.nombre = nombre;
        this.periodoActualizacion = periodoActualizacion;
        this.vinos = new ArrayList<>();
    }

    private List<Vino> vinos;

    public boolean sosActualizable() {
        return validarPeriocidad();
    }

    private boolean validarPeriocidad() {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaAActualizar = fechaUltimaActualizacion.plusMonths(periodoActualizacion); //Plus Months suma meses
        return fechaActual.isAfter(fechaAActualizar) || fechaActual.isEqual(fechaAActualizar);
    }

    public void obtenerActualizacion() {

    }

    public boolean existeVino(int añada, String nombre, Long idBodegaVinoTraido) {
        
        for (Vino vinoBodega : vinos) {
           if (vinoBodega.getAñada() == añada && vinoBodega.getNombre().equals(nombre) && id.equals(idBodegaVinoTraido))
            return true;
        }
        return false;
    }

    public void actualizarVino(int añada, String nombre, double precioNuevo, String notaCataNueva) {
        for (Vino vinoBodega : vinos) {
            if (Objects.equals(vinoBodega.getAñada(), añada) || Objects.equals(vinoBodega.getNombre(), nombre)) {
                vinoBodega.setPrecioARS(precioNuevo);
                vinoBodega.setNotaDeCataBodega(notaCataNueva);
                vinoBodega.setFechaActualizacion(LocalDate.now());
            }
        }
    }

    public void agregarVino(Vino vino) {
        vinos.add(vino);
    }

    public void mostrarVinos() {
        System.out.println("vinos de la bodega: " + nombre);
        vinos.forEach(System.out::println);
    }

}
