/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.parbolpractico;

/**
 *
 * @author USUARIO
 */
public class AVL<K extends Comparable<K>,V>extends ArbolBinarioBusqueda<K,V> {
    private static final byte DIFERECIA_MAXIMA = 1;
    private int saltos;
    public String grafica;
    private double tiempo=0;
    public AVL(){
        this.saltos=0;
        this.grafica="";
        this.tiempo=0;
    }
    //esto es para Mostrar datos
    public int getSaltos() {
        return saltos;
    }

        public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }
    public void setSaltos(int saltos) {
        this.saltos = saltos;
    }
    private void iniciarT(){
        this.setTiempo(System.nanoTime());
    }
    private void finalizarT(){
        this.setTiempo((System.nanoTime()-this.getTiempo())/1000000);
    }
    //INSERTAR
    public void insertar(K claveAInsertar, V valorAInsertar) {
        if (claveAInsertar == null) {
            throw new IllegalArgumentException("Clave no puede ser nula");
        }
        if (valorAInsertar == null) {
            throw new IllegalArgumentException("Valor no puede ser nula");
        }
        super.raiz = this.insertar(this.raiz,claveAInsertar, valorAInsertar);
    }

    private NodoBinario<K, V> insertar(NodoBinario<K, V> nodoActual, K claveAInsertar, V valorAInsertar) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
         NodoBinario<K,V> nuevoNodo = new NodoBinario<>(claveAInsertar,valorAInsertar);   
         return nuevoNodo;
        }
        K claveActual = nodoActual.getClave();
        if (claveAInsertar.compareTo(claveActual)>0) {
            NodoBinario<K,V> supuestoNuevoHijoDerecho = insertar(nodoActual.getHijoDrecho(),
                                                                 claveAInsertar,valorAInsertar);
            nodoActual.setHijoDrecho(supuestoNuevoHijoDerecho);
            return balancear(nodoActual);
        }
        if (claveAInsertar.compareTo(claveActual)<0) {
            NodoBinario<K,V> supuestoNuevoHijoIzquierdo = insertar(nodoActual.getHijoIzquierdo(),
                                                                 claveAInsertar,valorAInsertar);
            nodoActual.setHijoIzquierdo(supuestoNuevoHijoIzquierdo);
            return balancear(nodoActual);
        }
        //si llego aca quiere decir que en el nodo actual esta la clave a insertar
        nodoActual.setValor(valorAInsertar);
        return nodoActual;
    }

    private NodoBinario<K, V> balancear(NodoBinario<K, V> nodoActual) {
        int alturaRamaIzq = alturaR(nodoActual.getHijoIzquierdo());
        int alturaRamaDer = alturaR(nodoActual.getHijoDrecho());
        int diferencia = alturaRamaIzq - alturaRamaDer;  
        if (diferencia > DIFERECIA_MAXIMA) {
            //si ay que valancear
            NodoBinario<K,V> hijoIzquierdo = nodoActual.getHijoIzquierdo();
            alturaRamaIzq = alturaR(nodoActual.getHijoIzquierdo());
            alturaRamaDer = alturaR(nodoActual.getHijoDrecho());
            if (alturaRamaDer>alturaRamaIzq) {
                return rotacionDobleADerecha(nodoActual);
            }else{
                return rotacionSimpleADerecha(nodoActual);
            }
            
        }else if(diferencia < -DIFERECIA_MAXIMA){ //desbalance por Der -> Rotacion Izquierda
                     NodoBinario<K,V> nodoHijoDer = nodoActual.getHijoDrecho();
                        //ver si hay rotacion doble por izquierda
                     alturaRamaIzq = alturaR(nodoHijoDer.getHijoIzquierdo());
                     alturaRamaDer = alturaR(nodoHijoDer.getHijoIzquierdo());
                       if(alturaRamaIzq > alturaRamaDer){
                          return rotacionDobleAIzquierda(nodoActual); 
                       }
                       // hace rotacion simple por izquierda
                     return rotacionSimpleAIzquierda(nodoActual);   
        } 
        return nodoActual;
    }
         private NodoBinario<K,V> rotacionDobleADerecha(NodoBinario<K,V> nodoProblema){
               // trabaja con 3 nodos hijo y nieto
             NodoBinario<K,V> nodoHijoIzq = nodoProblema.getHijoIzquierdo();
             NodoBinario<K,V> nodoNietoDer = rotacionSimpleAIzquierda(nodoHijoIzq);
             nodoProblema.setHijoIzquierdo(nodoNietoDer);
             
             return rotacionSimpleADerecha(nodoProblema);
       }
       private NodoBinario<K,V> rotacionDobleAIzquierda(NodoBinario<K,V> nodoProblema){
            // trabaja con 3 nodos hijo y nieto
           NodoBinario<K,V> nodoHijoDer = nodoProblema.getHijoDrecho();
           NodoBinario<K,V> nodoNietoIzq = rotacionSimpleADerecha(nodoHijoDer);
           nodoProblema.setHijoDrecho(nodoNietoIzq);
           return rotacionSimpleAIzquierda(nodoProblema);
           
       } 
       /*
       1. Implementar los métodos que no se implementaron en clases o que se 
       implementaron a medias de árboles binarios de búsqueda y AVL
       */
       private NodoBinario<K,V> rotacionSimpleADerecha(NodoBinario<K,V> nodoProblema){
          NodoBinario<K,V> nodoHijoIzq = nodoProblema.getHijoIzquierdo();
          NodoBinario<K,V> nodoNietoDer = nodoHijoIzq.getHijoDrecho();
          nodoProblema.setHijoIzquierdo(nodoNietoDer);
          nodoHijoIzq.setHijoDrecho(nodoProblema);
          
          return nodoHijoIzq;
      } 
      private NodoBinario<K,V> rotacionSimpleAIzquierda(NodoBinario<K,V> nodoProblema){
                 NodoBinario<K,V> nodoHijoDer = nodoProblema.getHijoDrecho();
                 NodoBinario<K,V> nodoNietoIzq = nodoHijoDer.getHijoIzquierdo();
                  nodoProblema.setHijoDrecho(nodoNietoIzq);
                  nodoHijoDer.setHijoIzquierdo(nodoProblema);
               
                 return nodoHijoDer;
      }  
      
      /*
      8. Implemente el método eliminar de un árbol AVL
      */
      public boolean eliminarNodo(K clave){
        try {
            this.raiz = eliminarR(raiz, clave);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    private NodoBinario<K,V> eliminarR(NodoBinario<K,V> nodoActual, K datoAEliminar) throws Exception {

        if (NodoBinario.esNodoVacio(nodoActual)) {
            throw new Exception();
        }
        K datoDelNodoActual = nodoActual.getClave();
        if (datoAEliminar.compareTo(datoDelNodoActual) > 0) {
            NodoBinario<K,V> nuevoHijoDerecho = eliminarR(nodoActual.getHijoDrecho(), datoAEliminar);
            nodoActual.setHijoDrecho(nuevoHijoDerecho);
            return balancear(nodoActual);
        }
        if (datoAEliminar.compareTo(datoDelNodoActual) < 0) {
            NodoBinario<K,V> nuevoHijoIzquierdo = eliminarR(nodoActual.getHijoIzquierdo(), datoAEliminar);
            nodoActual.setHijoIzquierdo(nuevoHijoIzquierdo);
            return balancear(nodoActual);
        }
        //  encontro al datoAEliminar 
        //caso 1
        if (nodoActual.esHoja()) {
            return (NodoBinario<K, V>) NodoBinario.nodoVacio();
        }
        //caso 2
        if (nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()) {
            return balancear(nodoActual.getHijoDrecho());
        }
        if (!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()) {
            return balancear(nodoActual.getHijoIzquierdo());
        }
        //caso 3
        NodoBinario<K,V> datoSucesor = buscarDatoSucesor(nodoActual.getHijoDrecho());  //  dato sucesor 
        NodoBinario<K,V> nuevoNodoHijoDerecho = eliminarR(nodoActual.getHijoDrecho(), datoSucesor.getClave()); // cae un caso base 1 o 2
        nodoActual.setHijoDrecho(nuevoNodoHijoDerecho);
        nodoActual.setClave(datoSucesor.getClave());
        nodoActual.setValor(datoSucesor.getValor());
        return balancear(nodoActual);

    }
      //METODO PARA IMPRIMIR ARBOLA VOLACADO A LA IZQUIERDA UNA VEZ
      public void ImprimirArbol(){
        int m=alturaR(raiz);
        this.setSaltos(0);
        System.out.println(ImprimirArbol(raiz,m,m));
    }   
    private String ImprimirArbol(NodoBinario<K,V> aux,int alt,int ele){
        if(NodoBinario.esNodoVacio(aux)){
            return "";
        }
        this.setSaltos(this.getSaltos()+1);
        String Der=ImprimirArbol(aux.getHijoDrecho(), alt, ele-1);
        String q="    ";
        String s="";
        int i=0;
        while((alt-ele)!=i){
            i++;
            s=s+q;
        }
        s=Der+s+"|"+String.valueOf(aux.getClave())+"|"+'\12';
        String Izq=ImprimirArbol(aux.getHijoIzquierdo(), alt, ele-1);
        s=s+Izq;
        return s;       
    }
      public static void main(String[] args) throws Exception {
      AVL<Integer,Integer> Arbavl = new AVL<>();
      Arbavl.insertar(2, 95);
      Arbavl.insertar(1, 34);
      Arbavl.insertar(3, 66);
      Arbavl.insertar(4, 67);
      Arbavl.insertar(5, 68);
      Arbavl.ImprimirArbol();
      System.out.println("----------------------");
      Arbavl.eliminarNodo(5);
      Arbavl.ImprimirArbol();
      }
}

