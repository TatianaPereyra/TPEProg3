import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import Entidad.Camion;
import Entidad.Paquete;
import java.util.List;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import ArbolBinario.ArbolBinario;

public class Servicios {
    private HashMap<String, Paquete> paquetes;
    private ArbolBinario arbolPaquetes;
    private List<Paquete> paquetesConAlimentos;
    private List<Paquete> paquetesSinAlimentos;
    private List<Camion> camiones;

    /**
     * El constructor llama a los métodos de inicializar datos, los cuales recorren todos los 
     * paquetes y camiones. 
     * Por lo tanto, la complejidad computacional es O(n + m) 
     * donde n son la cantidad de paquetes a recorrer y m la cantidad de camiones a recorrer.
     */
    
    public Servicios(String pathCamiones, String pathPaquetes) {
        this.paquetes = new HashMap<>();
        this.paquetesConAlimentos = new ArrayList<>();
        this.paquetesSinAlimentos = new ArrayList<>();
        this.arbolPaquetes = new ArbolBinario();

        this.camiones = new ArrayList<>();

        inicializarPaquetes(pathPaquetes);
        inicializarCamiones(pathCamiones);
    }

    private void inicializarPaquetes(String pathPaquetes) {
        List<String[]> datos = readPath(pathPaquetes); 

        for (String[] linea : datos) {
           
            int id = Integer.parseInt(linea[0]);
            String codigoPaquete = linea[1];
            double peso = Double.parseDouble(linea[2]);
            boolean contieneAlimentos = linea[3].equals("1");
            int nivelUrgencia = Integer.parseInt(linea[4]);

            Paquete paquete = new Paquete(id, codigoPaquete, peso, contieneAlimentos, nivelUrgencia);
            this.paquetes.put(codigoPaquete, paquete);

            if(contieneAlimentos){
                this.paquetesConAlimentos.add(paquete);
            }
            else {
                this.paquetesSinAlimentos.add(paquete);
            }

            this.arbolPaquetes.addElement(paquete);
        }
    }

    private void inicializarCamiones(String pathCamiones){
        List<String[]> datos = readPath(pathCamiones); 

        for (String[] linea : datos) {
            int id = Integer.parseInt(linea[0]);
            String patenteString = linea[1];
            boolean estaRefrigerado = linea[2].equals("1");
            double capacidadKg= Double.parseDouble(linea[3]);

            Camion camion = new Camion(id, patenteString, estaRefrigerado, capacidadKg);
            this.camiones.add(camion);
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
    *Este metodo devuelve una lista de paquetes que contienen alimentos o no, segun el parametro booleano.
    * La complejidad computacional de este metodo es O(1) debido a que se accede directamente a la lista 
    * correspondiente (paquetesConAlimentos o paquetesSinAlimentos) sin necesidad de recorrer todos los paquetes.
    */

    public List<Paquete> servicio2(Boolean contieneAlimentos) {
        if (contieneAlimentos) {
            return this.paquetesConAlimentos;
        } else {
            return this.paquetesSinAlimentos;
        }
    }


    /**
     * Metodo para devolver todos los paquetes que estan entre un rango de urgencia.
     * La complejidad computacional es O(n) ya que el peor de los casos es que pidan todos los niveles, y se deba recorrer
     * todo el arbol donde se almacenan los paquetes segun su nivel de urgencia.
     */

    public List<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima) {
        List<Paquete> paquetes = new ArrayList<>();

        if(urgenciaMinima > 0 && urgenciaMaxima > 0 && urgenciaMinima <= urgenciaMaxima){
            paquetes = this.arbolPaquetes.buscarPaquetesEnRango(urgenciaMinima, urgenciaMaxima);
        }

        return paquetes;
    }


    private List<String[]> readPath(String path) {
       try{
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader reader = new CSVReaderBuilder(new FileReader(path)).withCSVParser(parser).build();
            List<String[]> datos = reader.readAll();

            return datos;
       } catch(Exception e){
                System.out.println("Error al leer el archivo: " + e.getMessage());
                return null;
         }
    }
    

    public Solucion servicioGreedy(){
        ArrayList<Paquete> paquetes = new ArrayList<>();
        paquetes.addAll(this.paquetesConAlimentos);
        paquetes.addAll(this.paquetesSinAlimentos);

        Greedy servicioGreedy = new Greedy(camiones, paquetes);
        Solucion solucion = servicioGreedy.asignarPaquetes();

        return solucion;

    }

    public Solucion servicioBacktracking(){
        ArrayList<Paquete> paquetes = new ArrayList<>();
        paquetes.addAll(this.paquetesConAlimentos);
        paquetes.addAll(this.paquetesSinAlimentos);

        Backtracking servicioBacktracking = new Backtracking(camiones, paquetes);
        Solucion solucion = servicioBacktracking.asignarPaquetes();

        return solucion;
    }


}
