package ppai.cu5.importarActualizacionesBodega.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Vino {
    private int añada;
    private String nombre;
    private String notaDeCataBodega;
    private double precioARS;
    private LocalDate fechaActualizacion;

    private List<Varietal> varietales;
    private List<Maridaje> maridajes;

    public Vino(int añada, String nombre, String notaDeCataBodega, double precioARS, LocalDate fechaActualizacion, List<TipoUva> tiposUvas,String infoVarietales, List<Maridaje> maridajes) {
        this.añada = añada;
        this.nombre = nombre;
        this.notaDeCataBodega = notaDeCataBodega;
        this.precioARS = precioARS;
        this.fechaActualizacion = fechaActualizacion;
        this.maridajes = maridajes;
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
                varietalesCreados.add(new Varietal(descripcion, porcentajeComposicion, tipoUva));
            }
            return varietalesCreados;
        }
}
