import java.util.ArrayList;
import java.util.HashMap;
import Controller.CamionController;
import Controller.PaqueteController;
import Entidad.Camion;
import Entidad.Paquete;
import java.util.List;

public class Servicios {
    private HashMap<String, Paquete> paquetes;
    //binaryTree de paquetes ordenados por nivel de urgencia
    private CamionController camionController;
    private PaqueteController paqueteController;

    public Servicios(String pathCamiones, String pathPaquetes) {
        //this.paquetes = new HashMap<>();
        //this.camionController = new CamionController(pathCamiones);
        this.paqueteController = new PaqueteController(pathPaquetes);
        this.paquetes = new HashMap<>();
        inicializarDatos();
    }

    private void inicializarDatos() {
        List<Paquete> paquetes = paqueteController.getDatos();
        for (Paquete paquete : paquetes) {
            this.paquetes.put(paquete.getCodigo_paquete(), paquete);
        }
    }
    
    /*
    * Método para obtener un paquete por su código de paquete. 
    * La complejidad computacional de este metodo es O(1) debido a que se utiliza un HashMap para almacenar los paquetes,
    *  lo que permite acceder a ellos en tiempo constante.
    */
    public Paquete servicio1(String codigoPaquete) {
        return this.paquetes.get(codigoPaquete);
    }

    /*
    *Este metodo filtra los paquetes por si contienen alimentos o no, dependiendo del valor del parámetro contieneAlimentos.
    * La complejidad computacional de este metodo es O(n), donde n es el número de paquetes almacenados.
    */
    public List<Paquete> servicio2(Boolean contieneAlimentos) {
        List<Paquete> paquetesFiltrados = new ArrayList<>();
        for (Paquete paquete : this.paquetes.values()) {
            if (paquete.isContiene_alimentos() == contieneAlimentos) {
                paquetesFiltrados.add(paquete);
            }
        }
        return paquetesFiltrados;
    }



}
