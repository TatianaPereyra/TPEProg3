package Controller;
public abstract class Controller<T> {
    
    public abstract void inicializarDatos(String path);

    public abstract T getDatos();

}
