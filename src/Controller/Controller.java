package Controller;

import com.opencsv.*;
import java.io.FileReader;
import java.util.List;
public abstract class Controller<T> {

    protected List<String[]> readPath(String path) {
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
    
    public abstract void inicializarDatos(String path);

    public abstract T getDatos();

}
