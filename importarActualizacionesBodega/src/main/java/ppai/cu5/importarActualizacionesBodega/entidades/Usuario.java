package ppai.cu5.importarActualizacionesBodega.entidades;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private String nombre;
    private boolean premium;
    private String password;

}
