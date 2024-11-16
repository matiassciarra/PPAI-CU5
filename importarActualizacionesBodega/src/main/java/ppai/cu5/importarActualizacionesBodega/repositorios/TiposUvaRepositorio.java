package ppai.cu5.importarActualizacionesBodega.repositorios;

import java.util.ArrayList;
import java.util.List;

import ppai.cu5.importarActualizacionesBodega.entidades.TipoUva;

public class TiposUvaRepositorio {

    public List<TipoUva> findAll() {
        TipoUva t1 = new TipoUva(1L, "muy buena", "Garnacha");
        TipoUva t2 = new TipoUva(2L, "muy mala", "Tempranillo");
        TipoUva t3 = new TipoUva(3L, "muy genial", "Cabernet Sauvignon");
        TipoUva t4 = new TipoUva(4L, "muy impresionante", "Pinot Syrah");
        TipoUva t5 = new TipoUva(5L, "muy deseable", "Malbec");
        return new ArrayList<>(List.of(t1,t2,t3,t4,t5));
    }

}
