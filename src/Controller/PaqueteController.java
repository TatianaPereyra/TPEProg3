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

        List<String[]> datos = readPath(path); 
        for (String[] linea : datos) {
           
            int id = Integer.parseInt(linea[0]);
            String codigoPaquete = linea[1];
            double peso = Double.parseDouble(linea[2]);
            boolean contieneAlimentos = linea[3].equals("1");
            int nivelUrgencia = Integer.parseInt(linea[4]);

            Paquete paquete = new Paquete(id, codigoPaquete, peso, contieneAlimentos, nivelUrgencia);
            this.paquetes.add(paquete);
        }
    }

    @Override
    public List<Paquete> getDatos() {
        return paquetes;
    }

}
