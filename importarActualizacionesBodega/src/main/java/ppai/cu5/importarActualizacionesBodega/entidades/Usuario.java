package ppai.cu5.importarActualizacionesBodega.entidades;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Usuario {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private boolean premium;
    private String password;
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Enofilo enofilo;

}
