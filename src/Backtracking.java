import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import Entidad.Camion;
import Entidad.Paquete;

public class Backtracking {
    private Solucion solucionActual;
    private Solucion solucionFinal;
    private HashSet<Camion> camionesUsados;
    private HashSet<Paquete> paquetesUsados;
    private ArrayList<Paquete> paquetes;
    private List<Camion> camiones;

    public Backtracking(List<Camion> camiones, ArrayList<Paquete> paquetes) {
        this.solucionActual = new Solucion();
        this.solucionFinal = new Solucion();
        this.camionesUsados = new HashSet<>();
        this.paquetesUsados = new HashSet<>();
        this.paquetes = paquetes;
        this.camiones = camiones;
    }

    public Solucion asignarPaquetes() {
        this.camionesUsados.clear();
        this.paquetesUsados.clear();

        Collections.sort(this.camiones);
        Collections.sort(this.paquetes);

        int cantEstados = 0;
        double pesoTotalNoAsignado = this.getTotalPesoPaquetes();

        this.asignarPaquetes(cantEstados, pesoTotalNoAsignado);

        return this.solucionFinal;
    }


    /**
     * Estrategia Backtracking: se recorren todos los camiones y por cada camion
     * se intenta asignar todos los paquetes posibles respetando las restricciones
     * (capacidad y refrigeración). Por cada asignacion valida se avanza recursivamente
     * y al volver se deshace la asignacion para explorar otras combinaciones.
     * Cuando no quedan mas camiones para recorrer se verifica si la solución actual
     * es mejor que la mejor encontrada hasta el momento, guardandola en ese caso.
     */

    private void asignarPaquetes(int cantEstados, double pesoTotalNoAsignado) {
        cantEstados++; 

        //Corto si ya procese todos los paquetes y/o todos los camiones
        if(this.paquetes.size() == this.paquetesUsados.size() || 
                    this.camiones.size() == this.camionesUsados.size()){

            this.solucionActual.setPesoNoAsignado(pesoTotalNoAsignado);
            this.solucionActual.setcantMetricaUtilizada(cantEstados);

            //Si es solucion
            if(this.solucionActual.getPesoNoAsignado() < this.solucionFinal.getPesoNoAsignado()){
                //Guardo una copia
                this.solucionFinal = this.solucionActual.copiar();
            }
        
        }else {
            //Recorre cada camion y verifica que no se haya usado
            for (Camion camionActual : this.camiones) {
                if (!this.camionesUsados.contains(camionActual)) {

                    this.camionesUsados.add(camionActual);

                    //Recorre cada paquete, si no esta usado y se puede asignar, se genera un nuevo estado
                    for (Paquete paqueteActual : this.paquetes) {

                        if (!this.paquetesUsados.contains(paqueteActual)
                                && this.solucionActual.puedeSerAsignado(camionActual, paqueteActual)) {
                            this.paquetesUsados.add(paqueteActual);
                            this.solucionActual.asignar(camionActual, paqueteActual);

                            pesoTotalNoAsignado -= paqueteActual.getPeso_kg();

                            this.asignarPaquetes(cantEstados, pesoTotalNoAsignado);

                            pesoTotalNoAsignado += paqueteActual.getPeso_kg();
                            this.paquetesUsados.remove(paqueteActual);
                            this.solucionActual.desasignar(camionActual, paqueteActual);

                        }
                    }

                    this.camionesUsados.remove(camionActual);
                }
            }
        }

    }

    private double getTotalPesoPaquetes() {
        Double peso = 0.0;

        for (Paquete p : this.paquetes) {
            peso += p.getPeso_kg();
        }

        return peso;
    }

}
