package ppai.cu5.importarActualizacionesBodega.gestor;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ppai.cu5.importarActualizacionesBodega.DTO.DTOBodega;
import ppai.cu5.importarActualizacionesBodega.DTO.DTOVino;
import ppai.cu5.importarActualizacionesBodega.boundary.ConfigAPI;
import ppai.cu5.importarActualizacionesBodega.entidades.Bodega;
import ppai.cu5.importarActualizacionesBodega.entidades.Maridaje;
import ppai.cu5.importarActualizacionesBodega.entidades.TipoUva;

import ppai.cu5.importarActualizacionesBodega.servicios.BodegaService;
import ppai.cu5.importarActualizacionesBodega.servicios.MaridajeService;
import ppai.cu5.importarActualizacionesBodega.servicios.TipoUvaService;
import ppai.cu5.importarActualizacionesBodega.boundary.PantallaNovedades;
import ppai.cu5.importarActualizacionesBodega.entidades.Vino;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GestorActualizacion {

    @Autowired
    private BodegaService servicioBodega;
    @Autowired
    private MaridajeService servicioMaridaje;
    @Autowired
    private TipoUvaService tipoUvaService;
    @Autowired
    private ConfigAPI configAPI;

    private PantallaNovedades pantalla;

    private List<Bodega> bodegas;
    private List<TipoUva> tiposUvas;
    private List<Maridaje> maridajes;
    private List<Bodega> bodegasActualizables;
    private List<Bodega> bodegasSeleccionadas;
    private LocalDate fechaActual;

    @PostConstruct
    private void inicializardatos(){
        bodegas = servicioBodega.obtenerTodasLasBodegas();
        tiposUvas = tipoUvaService.obtenerTiposUva();
        maridajes = servicioMaridaje.obtenerTodosLosMaridajes();
    }

    public List<DTOBodega> opcImportarActualizaciones() {
        return buscarBodegasActualizables();
    }

    private List<DTOBodega> buscarBodegasActualizables() {
        bodegasActualizables = bodegas.stream().filter(Bodega::sosActualizable).toList();
        return bodegasActualizables.stream()
                                    .map(DTOBodega::new)
                                    .toList();

    }

    public List<DTOVino>  tomarSeleccionBodega(List<Long> idsSeleccionadosBodega) {
        //Se obtienen las bodegas seleccionadas para actualizar
        bodegasSeleccionadas = bodegas.stream().filter(bodega -> idsSeleccionadosBodega.contains(bodega.getId())).toList();
        return obtenerActualizaciones(idsSeleccionadosBodega);
    }

    public List<DTOVino> obtenerActualizaciones(List<Long> idsSeleccionadosBodega) {

        List<String> actualizacionesVinos = new ArrayList<>(List.of());

        for (Long idBodega : idsSeleccionadosBodega) {
            actualizacionesVinos.addAll(configAPI.obtenerActualizacionesVinos(String.valueOf(idBodega)));
        }
        return actualizarVinosBodega(actualizacionesVinos);
    }

    private List<DTOVino>  actualizarVinosBodega(List<String> actualizacionesVinos) {
        obtenerFechaActual();
        List<DTOVino> listaVinos = new ArrayList();
        for (String vinoTraido : actualizacionesVinos) {
            String[] camposVinoTraido = vinoTraido.split(";");
            List<Vino> resumenVinosActualizados = new ArrayList();

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
                    Vino vinoActualizado = bodegaSeleccionada.actualizarVino(añada, nombre, precio, notaCata);
                    listaVinos.add(new DTOVino(vinoActualizado,false));
                }
                else {
                    List<Maridaje> maridajes = buscarMaridajes(camposVinoTraido[6]);
                    List<TipoUva> tiposUva = buscarTiposUva(camposVinoTraido[5]);
                    Vino vinoCreado = new Vino(añada, nombre, notaCata, precio, fechaActual, tiposUva, infoVarietales, maridajes,bodegaSeleccionada);
                    bodegaSeleccionada.getVinos().add(vinoCreado);
                    listaVinos.add(new DTOVino(vinoCreado,true));
                }
            }
        }
        setearFechaUltimaActualizacionABodegas(fechaActual);
        servicioBodega.guardarBodegas(bodegasSeleccionadas);
        return listaVinos;
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

    private void setearFechaUltimaActualizacionABodegas(LocalDate fecha) {
        bodegasSeleccionadas.forEach(bodega -> {
            bodega.setFechaUltimaActualizacion(fecha);
        });
    }

    private void obtenerFechaActual() {
        fechaActual = LocalDate.now();
    }

    public void setPantalla(PantallaNovedades pantalla) {
        this.pantalla = pantalla;
    }
    
}
