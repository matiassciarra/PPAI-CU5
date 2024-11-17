package ppai.cu5.importarActualizacionesBodega.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import ppai.cu5.importarActualizacionesBodega.DTO.DTOVino;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Bodega {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String descripcion;

    @Column(name = "fecha_ultima_actualizacion")
    private LocalDate fechaUltimaActualizacion;

    private String nombre;

    @Column(name = "periodo_actualizacion")
    private int periodoActualizacion; //numero de meses la cual la bodega se actualiza

    @OneToMany(mappedBy = "bodega", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Vino> vinos;


    public Bodega(Long id, String descripcion, LocalDate fechaUltimaActualizacion, String nombre, int periodoActualizacion) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
        this.nombre = nombre;
        this.periodoActualizacion = periodoActualizacion;
    }

    public boolean sosActualizable() {
        return validarPeriocidad();
    }

    private boolean validarPeriocidad() {
        //TODO
        //Se debe enviar la fecha actual por parametro
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

    public Vino actualizarVino(int añada, String nombre, double precioNuevo, String notaCataNueva) {
        for (Vino vinoBodega : vinos) {
            if (Objects.equals(vinoBodega.getAñada(), añada) || Objects.equals(vinoBodega.getNombre(), nombre)) {
                vinoBodega.setPrecioARS(precioNuevo);
                vinoBodega.setNotaDeCataBodega(notaCataNueva);
                vinoBodega.setFechaActualizacion(LocalDate.now());
                return vinoBodega;
            }
        }
        return null;
    }

    public void agregarVino(Vino vino) {
        vinos.add(vino);
    }

    public void mostrarVinos() {
        System.out.println("vinos de la bodega: " + nombre);
        vinos.forEach(System.out::println);
    }

}
