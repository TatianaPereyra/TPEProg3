package Controller;

import Entidad.Paquete;
import java.util.ArrayList;
import java.util.List;

public class PaqueteController extends Controller<List<Paquete>> {
    private List<Paquete> paquetes;

    public PaqueteController(String path) {
        this.paquetes = new ArrayList<>(); 
        inicializarDatos(path); 
    }

    @Override
    public void inicializarDatos(String path) {
        // Aquí se implementaría la lógica para leer los datos de los paquetes desde un archivo o fuente de datos
        // Por ejemplo, podríamos usar un BufferedReader para leer un archivo CSV y crear objetos Paquete
        // Luego, esos objetos se agregarían a la lista 'paquetes'
    }

    @Override
    public List<Paquete> getDatos() {
        return paquetes;
    }

}
