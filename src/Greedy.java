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

    public Solucion asignarPaquetes(){
        // Ordenar los camiones por capacidad de carga (de mayor a menor)
        Collections.sort(this.candidatosCamiones);

        // Ordenar los paquetes por nivel de urgencia (de mayor a menor)
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

        this.solucion.setCantidadCandidatosConsiderados(cantCamiones * cantPaquetes);

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
