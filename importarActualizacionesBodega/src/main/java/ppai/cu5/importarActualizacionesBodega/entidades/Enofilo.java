package ppai.cu5.importarActualizacionesBodega.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enofilo {

    private String nombre;
    private String apellido;
    private String imagenPerfil;
    private List<Siguiendo> siguiendo;
    private Usuario usuario;


    public Boolean seguisABodega(List<Bodega> bodegas) {
        return siguiendo.stream()
                .anyMatch(s -> s.sosDeBodega(bodegas));
    }



}
