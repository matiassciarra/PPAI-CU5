package ppai.cu5.importarActualizacionesBodega.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ppai.cu5.importarActualizacionesBodega.DTO.DTOBodega;
import ppai.cu5.importarActualizacionesBodega.entidades.Bodega;
import ppai.cu5.importarActualizacionesBodega.gestor.GestorActualizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/actualizacion")
public class PantallaNovedades {

    GestorActualizacion gestor;

    public PantallaNovedades(GestorActualizacion gestor) {
        this.gestor = gestor;
        gestor.setPantalla(this);
    }

    @GetMapping("/bodegas")
    public List<DTOBodega> opcImportarActualizaciones() {
        List<DTOBodega> res = this.habilitarVentana();
        return res;
    }

    private List<DTOBodega> habilitarVentana() {
        return gestor.opcImportarActualizaciones();
    }


    /*TODO: aplicando este MVC, no es necesario este metodo
    public void solicitarSeleccionBodega(List<DTOBodegaBodega> bodegasActualizables) {
        //Este metodo tenemos que reescribir, que seria el GetMapping.
        System.out.println("Lista de bodegas actualizables: ");
        bodegasActualizables.forEach(bodega -> System.out.println(bodega.getId() + " - " + bodega.getNombre()));
    }
    */
    @PostMapping("/bodegas")
    //Este seria un POST donde se envian las bodegas seleccionadas.
    public void tomarSeleccionBodega(@RequestBody List<Long> idBodegasSeleccionada) {
        //Este metodo seria el encargado de tomar la seleccion de las bodegas, que seria un metodo POST, osea del cuerpo del POST tendrian que llegar los IDs de las bodegas a actualizar.
        gestor.tomarSeleccionBodega(idBodegasSeleccionada);
    }

}
