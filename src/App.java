import ArbolBinario.ArbolBinario;
import Entidad.Paquete;

public class App {
    public static void main(String[] args) throws Exception {
        
        String pathPaquetes = "prueba.csv";

        Servicios servicio = new Servicios(null, pathPaquetes);
        System.out.println(servicio.servicio3(3, 50));

        /* 
        // Prueba del servicio 1
        String codigoPaquete = "P001";
        
        Paquete paquete = servicio.servicio1(codigoPaquete);
        if (paquete != null) {
            System.out.println("Servicio 1 - Paquete encontrado: " + paquete);
       
        } else {
            System.out.println("Servicio 1 - Paquete no encontrado con código: " + codigoPaquete);
        }
        */
    }




}
