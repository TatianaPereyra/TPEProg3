import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import Entidad.Camion;
import Entidad.Paquete;

public class Greedy {

    private List<Camion> candidatosCamiones;
    private ArrayList<Paquete> candidatosPaquetes;
    private Solucion solucion;

    public Greedy(List<Camion> candidatosCamiones, ArrayList<Paquete> candidatosPaquetes) {
        this.candidatosCamiones = candidatosCamiones;
        this.candidatosPaquetes = candidatosPaquetes;
        this.solucion = new Solucion();
    }

    /**
     * Estrategia Greedy: se ordenan camiones y paquetes de mayor a menor peso.
     * La función de selección elige siempre el paquete más pesado disponible para
     * asignarlo al camión actual, con el objetivo de minimizar el peso no asignado
     * aprovechando al máximo la capacidad de cada camión.
     * Por cada par (camion-paquete) se verifica si el paquete puede ser asignado.
     * Al finalizar los camiones, los paquetes restantes quedan sin asignar y su
     * peso total forma parte de la solución.
     */

    public Solucion asignarPaquetes(){
        Collections.sort(this.candidatosCamiones);
        Collections.sort(this.candidatosPaquetes);

        int cantCamiones = 0;
        int cantPaquetes = 0;

        for(Camion camionActual : this.candidatosCamiones){
            cantCamiones ++;

            Iterator<Paquete> itPaquIterator = this.candidatosPaquetes.iterator();

            while(itPaquIterator.hasNext()){
                Paquete paqueteActual = itPaquIterator.next();
                cantPaquetes++;

                if(this.solucion.puedeSerAsignado(camionActual, paqueteActual)){
                    this.solucion.asignar(camionActual, paqueteActual);
                    itPaquIterator.remove();
                }
            }
            
        }

        this.solucion.setcantMetricaUtilizada(cantCamiones * cantPaquetes);

        if(!this.candidatosPaquetes.isEmpty()){
            Double peso = 0.0;

            for(Paquete p : this.candidatosPaquetes){
                peso += p.getPeso_kg();
            }

            this.solucion.setPesoNoAsignado(peso);
        }
     
        return this.solucion;

    }

}
