package ppai.cu5.importarActualizacionesBodega.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vino {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int añada;
    private String nombre;

    @Column(name = "nota_cata_bodega")
    private String notaDeCataBodega;
    private double precioARS;
    @Column(name = "fecha_actualizacion")
    private LocalDate fechaActualizacion;
    
    @ManyToOne
    @JoinColumn(name="id_bodega")
    private Bodega bodega;

    @OneToMany(mappedBy = "vino", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Varietal> varietales;
    
    @ManyToMany
    @JoinTable(
        name="maridajesXvinos",
        joinColumns = @JoinColumn(name = "vino_id"),
        inverseJoinColumns = @JoinColumn(name = "maridaje_id")
    )
    private List<Maridaje> maridajes;
    
    public Vino(int añada, String nombre, String notaDeCataBodega, double precioARS, LocalDate fechaActualizacion, List<TipoUva> tiposUvas,String infoVarietales, List<Maridaje> maridajes, Bodega bodega) {
        this.añada = añada;
        this.nombre = nombre;
        this.notaDeCataBodega = notaDeCataBodega;
        this.precioARS = precioARS;
        this.fechaActualizacion = fechaActualizacion;
        this.maridajes = maridajes;
        this.bodega = bodega;
        this.varietales = crearVarietales(tiposUvas, infoVarietales);
        }
        
        private List<Varietal> crearVarietales(List<TipoUva> tiposUvas, String infoVarietales) {
            List<Varietal> varietalesCreados = new ArrayList<>();
            String[] varietalesString = infoVarietales.split("\\|");

            for (String varietalString : varietalesString) {
                String[] camposVarietal = varietalString.split("&");
                Long idTipoUva = Long.parseLong(camposVarietal[0]);
                TipoUva tipoUva = tiposUvas.stream().filter(t -> t.getId() == idTipoUva).findFirst().orElse(null);
                String descripcion = camposVarietal[1];
                int porcentajeComposicion = Integer.parseInt(camposVarietal[2]);
                Varietal varietal = new Varietal(descripcion, porcentajeComposicion, tipoUva);
                //Este cambio de mierda teniamos que hacer para que solucione la base de datos diossss
                varietal.setVino(this); // Establece la relación bidireccional
                varietalesCreados.add(varietal);
            }
            return varietalesCreados;
        }

    @Override
    public String toString() {
        return String.format("Vino{nombre='%s', bodegaId=%d}",
                nombre,
                bodega != null ? bodega.getId() : null
        );
    }
}
