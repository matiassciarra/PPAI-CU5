package ppai.cu5.importarActualizacionesBodega.gestor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ppai.cu5.importarActualizacionesBodega.boundary.ConfigAPI;
import ppai.cu5.importarActualizacionesBodega.entidades.Bodega;
import ppai.cu5.importarActualizacionesBodega.entidades.Maridaje;
import ppai.cu5.importarActualizacionesBodega.entidades.TipoUva;
import ppai.cu5.importarActualizacionesBodega.entidades.Varietal;
import ppai.cu5.importarActualizacionesBodega.servicios.BodegaService;
import ppai.cu5.importarActualizacionesBodega.servicios.MaridajeService;
import ppai.cu5.importarActualizacionesBodega.servicios.TipoUvaService;
import ppai.cu5.importarActualizacionesBodega.boundary.PantallaNovedades;
import ppai.cu5.importarActualizacionesBodega.entidades.Vino;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class GestorActualizacion {

    private BodegaService servicioBodega;
    private MaridajeService servicioMaridaje;
    private TipoUvaService tipoUvaService;
    private PantallaNovedades pantalla;
    private ConfigAPI configAPI;
    private List<Bodega> bodegas;
    private List<TipoUva> tiposUvas;
    private List<Maridaje> maridajes;
    private List<Bodega> bodegasActualizables;
    private List<Bodega> bodegasSeleccionadas;
    private LocalDate fechaActual;

    public GestorActualizacion() {
        servicioBodega = new BodegaService();
        servicioMaridaje = new MaridajeService();
        tipoUvaService = new TipoUvaService();
        configAPI = new ConfigAPI();
        bodegas = servicioBodega.obtenerTodasLasBodegas();
        maridajes = servicioMaridaje.obtenerTodosLosMaridajes();
        tiposUvas = tipoUvaService.obtenerTiposUva();
    }

    public void opcImportarActualizaciones() {
        buscarBodegasActualizables();
    }

    private void buscarBodegasActualizables() {
        bodegasActualizables = bodegas.stream().filter(Bodega::sosActualizable).toList();

        //Aca podriamos hacer convertir las bodegas actualizables a objetos BodegaDTO y mandarlas a la pantalla.
        pantalla.solicitarSeleccionBodega(bodegasActualizables);
    }

    public void tomarSeleccionBodega(List<Long> idsSeleccionadosBodega) {
        bodegasSeleccionadas = bodegasActualizables.stream().filter(bodega -> idsSeleccionadosBodega.contains(bodega.getId())).toList();
        obtenerActualizaciones(idsSeleccionadosBodega);
    }

    public void obtenerActualizaciones(List<Long> idsSeleccionadosBodega) {

        List<String> actualizacionesVinos = new ArrayList<>(List.of());

        for (Long idBodega : idsSeleccionadosBodega) {
            actualizacionesVinos.addAll(configAPI.obtenerActualizacionesVinos(String.valueOf(idBodega)));
        }
        actualizarVinosBodega(actualizacionesVinos);
        actualizacionesVinos.forEach(System.out::println);
    }

    private void actualizarVinosBodega(List<String> actualizacionesVinos) {
        obtenerFechaActual();
        
        for (String vinoTraido : actualizacionesVinos) {
            String[] camposVinoTraido = vinoTraido.split(";");

            //Extraigo los datos de los vinos
            int añada = Integer.parseInt(camposVinoTraido[0]);
            String nombre = camposVinoTraido[1];
            String notaCata = camposVinoTraido[3];
            double precio = Double.parseDouble(camposVinoTraido[4]);
            Long idBodegaVinoTraido = Long.parseLong(camposVinoTraido[2]);
            String infoVarietales = camposVinoTraido[5];
            //Ahora recorro las bodegas seleccionadas para actualizarle los vinos o agreguen nuevos.
            for (Bodega bodegaSeleccionada : bodegasSeleccionadas) {
                if (bodegaSeleccionada.existeVino(añada, nombre, idBodegaVinoTraido)) {
                    bodegaSeleccionada.actualizarVino(añada, nombre, precio, notaCata);
                }
                else {
                    List<Maridaje> maridajes = buscarMaridajes(camposVinoTraido[6]);
                    List<TipoUva> tiposUva = buscarTiposUva(camposVinoTraido[5]);
                    Vino vinoCreado = new Vino(añada, nombre, notaCata, precio, fechaActual, tiposUva, infoVarietales, maridajes);
                    bodegaSeleccionada.getVinos().add(vinoCreado);
                }
            }
        }
        setearFechaUltimaActualizacionABodegas(fechaActual);

    }
    private List<Maridaje> buscarMaridajes(String maridajesString) {
        List<Maridaje> maridajesEncontrados = new ArrayList<>();

        String[] idsMaridajes = maridajesString.split("\\|");

        for (String idMaridajeString : idsMaridajes) {
            Long idMaridaje = Long.parseLong(idMaridajeString);
            Maridaje maridajeEncontrado = maridajes.stream()
                    .filter(maridaje -> maridaje.getId() == idMaridaje)
                    .findFirst()
                    .orElse(null);
            maridajesEncontrados.add(maridajeEncontrado);
        }
        return maridajesEncontrados;
    }
    private List<TipoUva> buscarTiposUva(String infoVarietales) {
        List<TipoUva> tiposUvasEncontradas = new ArrayList<>();
        String[] varietalesString = infoVarietales.split("\\|");
        for (String varietalString : varietalesString) {
            String[] camposVarietal = varietalString.split("&");
            Long idTipoUva = Long.parseLong(camposVarietal[0]);
            TipoUva tipoUvaEncontrada = tiposUvas.stream().filter(t -> t.getId() == idTipoUva).findFirst().orElse(null);
            tiposUvasEncontradas.add(tipoUvaEncontrada);
        }
        return tiposUvasEncontradas;
    }

    private void setearFechaUltimaActualizacionABodegas(fecha) {
        bodegasSeleccionadas.forEach(bodega -> {
            bodega.setFechaUltimaActualizacion(fecha);
        });
    }

    private LocalDate obtenerFechaActual() {
        return LocalDate.now();
    }
}
