import java.util.HashMap;
import Controller.CamionController;
import Controller.PaqueteController;
import Entidad.Camion;
import Entidad.Paquete;

public class Servicios {
    //private HashMap<String, Paquete> paquetes;
    //binaryTree de paquetes ordenados por nivel de urgencia
    private CamionController camionController;
    private PaqueteController paqueteController;

    public Servicios(String pathCamiones, String pathPaquetes) {
        //this.paquetes = new HashMap<>();
        this.camionController = new CamionController(pathCamiones);
        this.paqueteController = new PaqueteController(pathPaquetes);
    }



}
