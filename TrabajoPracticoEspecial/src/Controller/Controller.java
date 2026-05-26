package Controller;


public abstract class Controller<T> {

    protected T readPath(String path) {
        // Aquí se implementaría la lógica para leer un archivo CSV utilizando OpenCSV
        // y devolver los datos en el formato adecuado (por ejemplo, una lista de objetos).
        return null; // Placeholder, se debe reemplazar con la implementación real.
    }
    
    public abstract void inicializarDatos(String path);

    public abstract T getDatos();

}
