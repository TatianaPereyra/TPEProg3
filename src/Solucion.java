import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Entidad.Camion;
import Entidad.Paquete;

public class Solucion {

    private HashMap<Camion, List<Paquete>> asignaciones;
    private HashMap<Camion, Double> capacidadUtilizada;
    private double pesoTotalNoAsignado;
    private int cantMetricaUtilizada;

    public Solucion() {
        this.asignaciones = new HashMap<>();
        this.capacidadUtilizada = new HashMap<>();
        this.pesoTotalNoAsignado = Integer.MAX_VALUE;
        this.cantMetricaUtilizada = 0;
    }


    public void asignar(Camion camion, Paquete paquete) {
        if(!this.asignaciones.containsKey(camion)){
            this.asignaciones.put(camion, new ArrayList<>());
            this.asignaciones.get(camion).add(paquete);

        }else{
            this.asignaciones.get(camion).add(paquete);
        }

        this.actualizarPeso(camion, paquete.getPeso_kg());
    }

    private void actualizarPeso(Camion camion, double peso) {
        if(!this.capacidadUtilizada.containsKey(camion)){
            this.capacidadUtilizada.put(camion, peso);
            
        }else{
            double pesoActual = this.capacidadUtilizada.get(camion);
            this.capacidadUtilizada.put(camion, pesoActual + peso);
        }
    }

    public boolean puedeSerAsignado(Camion camion, Paquete paquete){

        if(paquete.isContiene_alimentos() && !camion.isEsta_refrigerado()){
            return false;
        
        }else{
            if(!this.capacidadUtilizada.containsKey(camion)){
                return camion.getCapacidad_kg() >= paquete.getPeso_kg();
            
            }else{
                return this.capacidadUtilizada.get(camion) + paquete.getPeso_kg() <= camion.getCapacidad_kg();
            }
            
        }

    }

    public double getPesoNoAsignado(){
        return this.pesoTotalNoAsignado;
    }

    public void setPesoNoAsignado(Double peso){
        this.pesoTotalNoAsignado = peso;
    }

    public void setcantMetricaUtilizada(int cantidad){
        this.cantMetricaUtilizada = cantidad;
    }


    @Override
    public String toString() {
        String resultado = "\n"; 
        resultado += "Solución obtenida:\n";

        for (Camion camion : asignaciones.keySet()) {
            resultado += camion + ": " + asignaciones.get(camion) + "\n";
        }

        resultado += "Peso no asignado: " + pesoTotalNoAsignado + " kg.\n";
        resultado += "Métrica para analizar el costo de la solución: " + this.cantMetricaUtilizada;

        return resultado;
    }

}
