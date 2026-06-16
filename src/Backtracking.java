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

    private void asignarPaquetes(int cantEstados, double pesoTotalNoAsignado) {

        if(this.camiones.size() == this.camionesUsados.size()){
            this.solucionActual.setPesoNoAsignado(pesoTotalNoAsignado);
            this.solucionActual.setcantMetricaUtilizada(cantEstados);

            if(this.solucionActual.getPesoNoAsignado() < this.solucionFinal.getPesoNoAsignado()){
                this.solucionFinal = this.solucionActual;
            }
        }

        else {

            for (Camion camionActual : this.camiones) {

                if (!this.camionesUsados.contains(camionActual)) {

                    this.camionesUsados.add(camionActual);

                    for (Paquete paqueteActual : this.paquetes) {

                        if (!this.paquetesUsados.contains(paqueteActual)
                                && this.solucionActual.puedeSerAsignado(camionActual, paqueteActual)) {
                            this.paquetesUsados.add(paqueteActual);
                            this.solucionActual.asignar(camionActual, paqueteActual);

                            cantEstados++;
                            pesoTotalNoAsignado -= paqueteActual.getPeso_kg();

                            this.asignarPaquetes(cantEstados, pesoTotalNoAsignado);

                            cantEstados--;
                            pesoTotalNoAsignado += paqueteActual.getPeso_kg();
                            //this.paquetesUsados.remove(paqueteActual);
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
