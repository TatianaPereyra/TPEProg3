import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Entidad.Camion;
import Entidad.Paquete;

public class Solucion {
    private HashMap<Camion, List<Paquete>> asignaciones;
    private HashMap<Camion, Double> capacidadUtilizada;
    private double pesoTotalNoAsignado;
    private int cantidadCandidatosConsiderados;

    public Solucion() {
        this.asignaciones = new HashMap<>();
        this.capacidadUtilizada = new HashMap<>();
        this.pesoTotalNoAsignado = 0.0;
        this.cantidadCandidatosConsiderados = 0;
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
                return true;
            
            }else{
                return this.capacidadUtilizada.get(camion) + paquete.getPeso_kg() <= camion.getCapacidad_kg();
            }
            
        }

    }

    public void setPesoNoAsignado(Double peso){
        this.pesoTotalNoAsignado = peso;
    }

    public void setCantidadCandidatosConsiderados(int cantidad){
        this.cantidadCandidatosConsiderados = cantidad;
    }


    @Override
    public String toString() {
        String resultado = "\n"; 
        resultado += "Solución obtenida:\n";

        for (Camion camion : asignaciones.keySet()) {
            resultado += camion + ": " + asignaciones.get(camion) + "\n";
        }

        resultado += "Peso no asignado: " + pesoTotalNoAsignado + " kg.\n";
        resultado += "Métrica para analizar el costo de la solución: " + this.cantidadCandidatosConsiderados;

        return resultado;
    }

}
