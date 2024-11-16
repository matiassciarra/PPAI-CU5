package ppai.cu5.importarActualizacionesBodega.boundary;

import ppai.cu5.importarActualizacionesBodega.entidades.Bodega;
import ppai.cu5.importarActualizacionesBodega.gestor.GestorActualizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PantallaNovedades {

    GestorActualizacion gestor;

    public PantallaNovedades(GestorActualizacion gestor) {
        this.gestor = gestor;
        gestor.setPantalla(this);
    }

    public void opcImportarActualizaciones() {
        this.habilitarVentana();
    }

    private void habilitarVentana() {
        gestor.opcImportarActualizaciones();
    }

    public void solicitarSeleccionBodega(List<Bodega> bodegasActualizables) {
        //Este metodo tenemos que reescribir, que seria el GetMapping.
        System.out.println("Lista de bodegas actualizables: ");
        bodegasActualizables.forEach(bodega -> System.out.println(bodega.getId() + " - " + bodega.getNombre()));
    }

    public void tomarSeleccionBodega() {
        //Este metodo seria el encargado de tomar la seleccion de las bodegas, que seria un metodo POST, osea del cuerpo del POST tendrian que llegar los IDs de las bodegas a actualizar.
        List<Long> idBodegasSeleccionadas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese los IDs de las bodegas a actualizar, separados por comas:");
        String input = scanner.nextLine();
        String[] ids = input.split(",");
        for (String id : ids) {
            idBodegasSeleccionadas.add(Long.parseLong(id.trim()));
        }
        gestor.tomarSeleccionBodega(idBodegasSeleccionadas);
    }

}
