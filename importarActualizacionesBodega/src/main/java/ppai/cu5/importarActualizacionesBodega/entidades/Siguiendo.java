package ppai.cu5.importarActualizacionesBodega.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Siguiendo {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate fechaFin;
    private LocalDate fechaInicio;
    //TODO: Es uno siguiendo a bodega o a otro enofilo
    @ManyToOne
    @JoinColumn(name = "id_bodega")
    private Bodega bodega;
    @ManyToOne
    @JoinColumn(name = "id_enofiloSeguidor")
    private Enofilo enofiloSeguidor;
    @ManyToOne
    @JoinColumn(name = "id_enofilo")
    private Enofilo enofilo;

    public Boolean sosDeBodega(List<Bodega> seleccionBodega) {
        for (Bodega bodega : seleccionBodega) {
            if (this.bodega.equals(bodega)) {
                return true;
            }
        }
        return false;
    }
}
