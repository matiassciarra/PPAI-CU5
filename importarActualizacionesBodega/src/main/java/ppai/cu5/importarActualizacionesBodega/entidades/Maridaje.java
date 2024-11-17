package ppai.cu5.importarActualizacionesBodega.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Maridaje {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descripcion;
    private String nombre;

    @ManyToMany(mappedBy="maridajes", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Vino> vino;

    public Maridaje(Long id, String descripcion, String nombre) {
        this.id = id;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

}
