package ppai.cu5.importarActualizacionesBodega.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Enofilo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String apellido;
    private String imagenPerfil;
    @OneToMany(mappedBy = "enofilo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Siguiendo> siguiendo;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    public Boolean seguisABodega(List<Bodega> bodegas) {
        
        return siguiendo.stream()
                .anyMatch(s -> s.sosDeBodega(bodegas));
    }



}
