import java.util.ArrayList;
import java.util.List;
import Entidad.Paquete;

public class App {
    public static void main(String[] args) throws Exception {
        
        String pathPaquetes = "prueba.csv";
        String pathCamiones = "pruebaCamiones.csv";

        Servicios servicio = new Servicios(pathCamiones, pathPaquetes);

        //Servicio 1
        Paquete servicio1 = servicio.servicio1("P002");
        System.out.println("Servicio 1: " + servicio1 + "\n");
        System.out.println();

        //Servicio 2
        List<Paquete> servicio2 = servicio.servicio2(true);
        System.out.println("Servicio 2: " + servicio2 + "\n");
        System.out.println();

        //Servicio 3
        List<Paquete> servicio3 = servicio.servicio3(5, 75);
        System.out.println("Servicio 3: " + servicio3 + "\n");
        System.out.println();

        //Servicio Greedy
        Solucion solucionGreedy = servicio.servicioGreedy();
        System.out.println("Greedy");
        System.out.println(solucionGreedy);
        System.out.println();

        //Servicio BackTracking
        Solucion solucionBack = servicio.servicioBacktracking();
        System.out.println("BackTracking");
        System.out.println(solucionBack);
        System.out.println();
        
    }




}
