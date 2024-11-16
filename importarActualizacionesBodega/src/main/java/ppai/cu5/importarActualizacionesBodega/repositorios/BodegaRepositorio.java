package ppai.cu5.importarActualizacionesBodega.repositorios;

import ppai.cu5.importarActualizacionesBodega.entidades.Bodega;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BodegaRepositorio {
    public List<Bodega> findAll() {
        LocalDate dia1 = LocalDate.of(2024, 10, 10);
        LocalDate dia2 = LocalDate.of(2023, 9, 10);
        Bodega bodega1 = new Bodega(1L,"a", dia1, "bodega1", 2);
        Bodega bodega2 = new Bodega(2L,"b", dia2, "bodega2", 2);
        Bodega bodega3 = new Bodega(3L, "c", dia1, "bodega3", 2);
        Bodega bodega4 = new Bodega(4L,"d", dia2, "bodega4", 2);
        Bodega bodega5 = new Bodega(5L, "e", dia1, "bodega5", 2);
        Bodega bodega6 = new Bodega(6L, "f", dia2, "bodega5", 2);

        return new ArrayList<>(List.of(bodega1, bodega2, bodega3, bodega4, bodega5));
    }
}
