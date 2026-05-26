package Controller;

import Entidad.Camion;
import java.util.ArrayList;
import java.util.List;

public class CamionController extends Controller<List<Camion>> {
    private List<Camion> camiones;

    public CamionController(String path) {
        this.camiones = new ArrayList<>();
        inicializarDatos(path);
    }

    @Override
    public void inicializarDatos(String path) {
        // Aquí se implementaría la lógica para leer los datos de los camiones desde un archivo
        // y llenar la lista camiones. Por ahora, se puede simular con datos de ejemplo.
    }

    @Override
    public List<Camion> getDatos() {
        return camiones;
    }

}
