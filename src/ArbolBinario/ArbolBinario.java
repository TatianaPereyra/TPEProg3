package ArbolBinario;

import java.util.ArrayList;
import java.util.List;

import Entidad.Paquete;

public class ArbolBinario {

    private Nodo root;

    public ArbolBinario() {
        this.root = null;
    }

    public Nodo getRoot() {
        return root;
    }
    
    public void addElement(Paquete paquete) {
        if (root == null) {
            root = new Nodo(paquete.getNivel_urgencia());
            root.addPaquete(paquete);

        } else {
            Nodo tmp = buscarNodo(root, paquete.getNivel_urgencia());
            if (tmp != null) {
                tmp.addPaquete(paquete);
            } else {
                addElement(root, paquete);
            }
        }
    }

    /*Este metodo busca un nodo por una clase, que representa el nivel de urgencia. 
    Si lo encuentra, devuelve el nodo. De lo contrario, devuelve null. */

    private Nodo buscarNodo(Nodo nodo, int key) {
        if (nodo == null) {
            return null;
        }

        if (nodo.getKey() == key) {
            return nodo;
        }
        
        else if (key < nodo.getKey()) {
            return buscarNodo(nodo.getLeft(), key);
        }
        else {
            return buscarNodo(nodo.getRight(), key);
        }
    }

    /*Este metodo agrega un elemento al arbol, segun el nivel de urgencia. */

    private void addElement(Nodo actual, Paquete paquete) {
        if (paquete.getNivel_urgencia() < actual.getKey()) {
            if (actual.getLeft() == null) {
                Nodo nuevo = new Nodo(paquete.getNivel_urgencia());
                nuevo.addPaquete(paquete);
                actual.setLeft(nuevo);
            } else {
                addElement(actual.getLeft(), paquete);
            }
        } else {
            if (actual.getRight() == null) {
                Nodo nuevo = new Nodo(paquete.getNivel_urgencia());
                nuevo.addPaquete(paquete);
                actual.setRight(nuevo);
            } else {
                addElement(actual.getRight(), paquete);
            }
        }
    }

    public List<Paquete> buscarPaquetesEnRango(int urgenciaMinima, int urgenciaMaxima) {
        List<Paquete> paquetes = new ArrayList<>();
        Nodo actual = this.buscarNodo(root, urgenciaMinima);
        if(actual == null) {
            this.buscarPaquetesEnRango(this.root, urgenciaMinima, urgenciaMaxima, paquetes);
        }
        else {
            this.buscarPaquetesEnRango(actual, urgenciaMinima, urgenciaMaxima, paquetes);
        }

        return paquetes;
    }

    private void buscarPaquetesEnRango(Nodo actual, int urgenciaMinima, int urgenciaMaxima, List<Paquete> paquetes){

        if(actual == null) {
            return;
        }
        
        if(actual.getKey() >= urgenciaMinima && actual.getKey() <= urgenciaMaxima ) {
            paquetes.addAll(actual.getPaquetes());
        }
       
        buscarPaquetesEnRango(actual.getLeft(), urgenciaMinima, urgenciaMaxima, paquetes);
        buscarPaquetesEnRango(actual.getRight(), urgenciaMinima, urgenciaMaxima, paquetes);
    }


    private class Nodo {
        private int key;
        private List<Paquete> paquetes;
        private Nodo left;
        private Nodo right;

        public Nodo (int k) {
            this.key = k;
            this.paquetes = new ArrayList<>();
            this.left = null;
            this.right = null;  
        }

        public int getKey() {
            return key;
        }

        public List<Paquete> getPaquetes() {
            return paquetes;
        }
        
        public Nodo getLeft() {
            return left;
        }
        
        public Nodo getRight() {
            return right;
        }

        public void addPaquete(Paquete paquete) {
            this.paquetes.add(paquete);
        }

        public void setLeft(Nodo left) {
            this.left = left;
        }

        public void setRight(Nodo right) {
            this.right = right;
        }

    }
}
