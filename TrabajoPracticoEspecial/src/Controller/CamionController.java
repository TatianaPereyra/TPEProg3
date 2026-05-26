package Controller;

import Entidad.Camion;
import Entidad.Paquete;

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

        List<String[]> datos = readPath(path); 
        for (String[] linea : datos) {

            int id = Integer.parseInt(linea[0]);
            String patente = linea[1];
            boolean estaRefrigerado = linea[2].equals("1");
            Double capacidad = Double.parseDouble(linea[3]);

            Camion camion = new Camion(id, patente, estaRefrigerado, capacidad);
            this.camiones.add(camion);
        }
    }

    @Override
    public List<Camion> getDatos() {
        return camiones;
    }

}
