package ppai.cu5.importarActualizacionesBodega.entidades;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class TipoUva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descripcion;
    private String nombre;
    @OneToMany(mappedBy="tipoUva", cascade = CascadeType.ALL)
    private List<Varietal> varietal;

    public TipoUva(Long id,String descripcion, String nombre) {
        this.id = id;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }
    
}
