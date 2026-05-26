package Entidad;
public class Camion {
    private int id_camion;
    private String patente;
    private boolean esta_refrigerado;
    private double capacidad_kg;

    public Camion(int id_camion, String patente, boolean esta_refrigerado, double capacidad_kg) {
        this.id_camion = id_camion;
        this.patente = patente;
        this.esta_refrigerado = esta_refrigerado;
        this.capacidad_kg = capacidad_kg;
    }

    public int getId_camion() {
        return id_camion;
    }

    public String getPatente() {
        return patente;
    }

    public boolean isEsta_refrigerado() {
        return esta_refrigerado;
    }

    public double getCapacidad_kg() {
        return capacidad_kg;
    }

    @Override
    public String toString() {
        return "Camion{" +
                "id_camion=" + id_camion +
                ", patente='" + patente + '\'' +
                ", capacidad_kg=" + capacidad_kg +
                ", esta_refrigerado=" + esta_refrigerado +
                '}';
    }
}
   
