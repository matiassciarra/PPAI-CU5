package ppai.cu5.importarActualizacionesBodega;

import ppai.cu5.importarActualizacionesBodega.gestor.GestorActualizacion;
import ppai.cu5.importarActualizacionesBodega.boundary.PantallaNovedades;

//@SpringBootApplication
public class ImportarActualizacionesBodegaApplication {
	public static void main(String[] args) {
		//SpringApplication.run(ImportarActualizacionesBodegaApplication.class, args);
		GestorActualizacion gestor = new GestorActualizacion();
		PantallaNovedades pantalla = new PantallaNovedades(gestor);
		pantalla.opcImportarActualizaciones();
		pantalla.tomarSeleccionBodega();
	}

}
