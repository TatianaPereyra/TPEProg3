public class App {
    public static void main(String[] args) throws Exception {
        
        String pathPaquetes = "prueba.csv";
        String pathCamiones = "pruebaCamiones.csv";

        Servicios servicio = new Servicios(pathCamiones, pathPaquetes);

        servicio.servicioBacktracking();
    }




}
