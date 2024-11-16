package utn.frc.apiBodegas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GeneradorAleatorioVinosBodega {

    //Esta es la lista de opciones de varitales que pueden salir, cada varietal esta sepadado por &, el primer campo es id del tipo de varietal, el segundo es la descripcion, y el tercer campo es el porcentaje de composicion
    private static final List<String> OPCIONES_VARIETALES = Arrays.asList(
            "1&buen varietal muy bueno&20|2&varietal genial&40",
             "2&varietal decente&10",
             "3&varietal que deja mucho que desear&34", 
             "3&varietal uniforme&24|1&varietal sumiso&10",
             "4&varietal formal&13",
             "1&varietal mamon&38", 
             "4&varietal crack&44|2&varietal saliente&20" 
    );

    //Estas son las IDs de los maridajes
    private static final List<String> OPCIONES_MARIDAJES = Arrays.asList(
            "3", "1", "1|2","2","2|3"
    );
    private static final List<String> OPCIONES_NOMBRES = Arrays.asList(
            "Carmen", "Rosa", "Esperanza","Mouse","Toro"
    );
    private static final List<String> OPCIONES_NOTA_DE_CATA = Arrays.asList(
        "Muy buen vino",
        "Lo mejor que probe en años",
        "Nunca probe algo mejor",
        "Amo este vino",
        "La mejor creacion que tuvimos",
        "El dia nace y los pollos cacarean",
        "La venganza nunca es buena mata el alma y la envenena",
        "Este vino me despierta muchas cosas",
        "La verdad este vino me sorprendió"
    );

    public static List<String[]> crearListaVinosAleatorios(String idBodega) {
        List<String[]> listaVinos = new ArrayList<>();
        String[] header = {"añada", "nombre", "idBodega", "notaDeCataBodega", "precioARS", "infoVarietales", "idsMaridajes"};
        listaVinos.add(header);

        Random random = new Random();
        for (int i = 0; i < 10; i++) { // Generate 10 random wines
            String añada = String.valueOf(2020 + random.nextInt(2)); // Random year between 2000 and 2021
            String nombre = OPCIONES_NOMBRES.get(random.nextInt(OPCIONES_NOMBRES.size()));
            String notaDeCataBodega = OPCIONES_NOTA_DE_CATA.get(random.nextInt(OPCIONES_NOTA_DE_CATA.size()));
            String precioARS = String.valueOf(100 + random.nextInt(900)); // Random price between 100 and 999
            String varietales = OPCIONES_VARIETALES.get(random.nextInt(OPCIONES_VARIETALES.size()));
            String maridajes = OPCIONES_MARIDAJES.get(random.nextInt(OPCIONES_MARIDAJES.size()));
            String[] vino = {añada, nombre, idBodega, notaDeCataBodega, precioARS, varietales, maridajes};
            listaVinos.add(vino);
        }
        return listaVinos;
    }
}