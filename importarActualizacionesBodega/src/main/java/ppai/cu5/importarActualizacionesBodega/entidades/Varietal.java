package ppai.cu5.importarActualizacionesBodega.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Varietal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descripcion;
    @Column(name = "porcentaje_composion")
    private int porcentajeComposicion;
    @ManyToOne
    @JoinColumn(name="tipo_uva_id")
    private TipoUva tipoUva;

    @ManyToOne
    @JoinColumn(name = "vino_id")
    private Vino vino;

    public Varietal(String descripcion, int porcentajeComposicion, TipoUva tipoUva) {
        this.descripcion = descripcion;
        this.porcentajeComposicion = porcentajeComposicion;
        this.tipoUva = tipoUva;
    }


}
