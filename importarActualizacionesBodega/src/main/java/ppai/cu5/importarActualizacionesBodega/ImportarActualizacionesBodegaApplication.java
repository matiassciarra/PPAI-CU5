package ppai.cu5.importarActualizacionesBodega;

import ppai.cu5.importarActualizacionesBodega.gestor.GestorActualizacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ppai.cu5.importarActualizacionesBodega.boundary.PantallaNovedades;

@SpringBootApplication
public class ImportarActualizacionesBodegaApplication {
	public static void main(String[] args) {
		GestorActualizacion gestor = new GestorActualizacion();
		PantallaNovedades pantalla = new PantallaNovedades(gestor);
		SpringApplication.run(ImportarActualizacionesBodegaApplication.class, args);

		//pantalla.opcImportarActualizaciones();
		//pantalla.tomarSeleccionBodega();

	}

}
