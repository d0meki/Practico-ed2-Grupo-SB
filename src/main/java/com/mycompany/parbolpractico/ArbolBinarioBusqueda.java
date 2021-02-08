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
public class ArbolBinarioBusqueda <K extends Comparable<K>,V>{
    
    protected NodoBinario<K,V> raiz;
    private int saltos;
    public String grafica;
    private double tiempo=0;
    public ArbolBinarioBusqueda(){
        this.raiz = null;
        this.saltos=0;
        this.grafica="";
        this.tiempo=0;
    }
    //para mostrar datos   ----   
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
    
    public boolean esArbolVacio() {
        return NodoBinario.esNodoVacio(this.raiz);
    }
    protected NodoBinario<K,V> buscarDatoSucesor(NodoBinario<K,V> nodoActual) {
        NodoBinario<K,V> nodoAnterior = nodoActual;
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijoIzquierdo();
        }
        return nodoAnterior;
    }
// PREGUNTAS PRACTICO
    /*
    2. Implemente un método recursivo que retorne la cantidad nodos 
    que tienen solo hijo izquierdo no vacío en un árbol binario 
    */
   
    public int cantNodHijIzq() {
        return cantNodHijIzqRec(this.raiz);
    }
    private int cantNodHijIzqRec(NodoBinario<K,V> nodoActual){
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int nodPorRamaIzq = cantNodHijIzqRec(nodoActual.getHijoIzquierdo());
        int nodPorRamaDer =cantNodHijIzqRec(nodoActual.getHijoDrecho());
        if (!nodoActual.esVacioHijoIzquierdo() 
            && nodoActual.esVacioHijoDerecho()) {
            return nodPorRamaIzq + nodPorRamaDer + 1;
        }
        return nodPorRamaIzq + nodPorRamaDer;
    }
    /*
    4. Implemente un método recursivo que retorne la cantidad nodos que tienen
    solo hijo izquierdo no vacío en un árbol binario, pero solo en el nivel N 
    */
    public int cantNodosSoloHijIzqEnNivel(int nivelObjetivo){
        return  cantNodosSoloHijIzqEnNivelR(this.raiz,nivelObjetivo,0);
    }
    
    private int cantNodosSoloHijIzqEnNivelR(NodoBinario<K,V> nodoActual,int nivelObjetivo,int nivelActual){
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int completoPorIzq = this.cantNodosSoloHijIzqEnNivelR(nodoActual.getHijoIzquierdo(),nivelObjetivo,nivelActual+1);
        int completoPorDer = this.cantNodosSoloHijIzqEnNivelR(nodoActual.getHijoDrecho(),nivelObjetivo,nivelActual+1);
        if (nivelActual == nivelObjetivo) { //encuentra que el nivel sea completo
            if (!nodoActual.esVacioHijoIzquierdo()&&
                    nodoActual.esVacioHijoDerecho()) {
                return completoPorIzq+completoPorDer+1;
            } 
        }
        return completoPorIzq + completoPorDer;
    }
    /*
    5. Implemente un método recursivo que retorne la cantidad nodos que tienen
    solo hijo izquierdo no vacío en un árbol binario, pero solo antes del nivel N 
    */
    public int cantNodosSoloHijIzqAntNivel(int nivelObjetivo){
        return  cantNodosSoloHijIzqAntNivelR(this.raiz,nivelObjetivo,0);
    }
    
    private int cantNodosSoloHijIzqAntNivelR(NodoBinario<K,V> nodoActual,int nivelObjetivo,int nivelActual){
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int completoPorIzq = this.cantNodosSoloHijIzqAntNivelR(nodoActual.getHijoIzquierdo(),nivelObjetivo,nivelActual+1);
        int completoPorDer = this.cantNodosSoloHijIzqAntNivelR(nodoActual.getHijoDrecho(),nivelObjetivo,nivelActual+1);
        if (nivelActual < nivelObjetivo) { //encuentra que el nivel sea completo
            if (!nodoActual.esVacioHijoIzquierdo()&&
                    nodoActual.esVacioHijoDerecho()) {
                return completoPorIzq+completoPorDer+1;
            } 
        }
        return completoPorIzq + completoPorDer;
    }
   /* 6. Implemente un método recursivo que retorne la cantidad nodos que tienen
    solo hijo izquierdo no vacío en un árbol binario, pero solo Desp del nivel N 
    */
    public int cantNodosSoloHijIzqDespNivel(int nivelObjetivo){
        return  cantNodosSoloHijIzqDespNivelR(this.raiz,nivelObjetivo,0);
    }
    
    private int cantNodosSoloHijIzqDespNivelR(NodoBinario<K,V> nodoActual,int nivelObjetivo,int nivelActual){
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int completoPorIzq = this.cantNodosSoloHijIzqDespNivelR(nodoActual.getHijoIzquierdo(),nivelObjetivo,nivelActual+1);
        int completoPorDer = this.cantNodosSoloHijIzqDespNivelR(nodoActual.getHijoDrecho(),nivelObjetivo,nivelActual+1);
        if (nivelActual > nivelObjetivo) { //encuentra que el nivel sea completo
            if (!nodoActual.esVacioHijoIzquierdo()&&
                    nodoActual.esVacioHijoDerecho()) {
                return completoPorIzq+completoPorDer+1;
            } 
        }
        return completoPorIzq + completoPorDer;
    }
    
    /*
    --------------------------------------------------------------------
    */
     public void insertarRec(K clave, V valor)throws Exception {
        raiz=insertarRecursivo(clave,valor,raiz);
    }
    private NodoBinario insertarRecursivo(K clave,V valor,NodoBinario<K,V> nodoActual)throws Exception{
        if(NodoBinario.esNodoVacio(nodoActual)){//estaVacio(nodoAct)
            return new NodoBinario<K,V>(clave,valor); 
        }       
        if(clave.compareTo(nodoActual.getClave())<0){
            nodoActual.setHijoIzquierdo(insertarRecursivo(clave,valor,nodoActual.getHijoIzquierdo())); //
            return nodoActual;
        }else{
            if(clave.compareTo(nodoActual.getClave())>0){
                nodoActual.setHijoDrecho(insertarRecursivo(clave,valor,nodoActual.getHijoDrecho())); 
                return nodoActual;
            }else{
                throw new Exception("Dato Duplicado");
            }
        }
    }

    public int alturaRec() {//La altura es el nivel + 1 del nodo mas profundo en el arbol
        return alturaR(this.raiz);
    }
    protected int alturaR(NodoBinario<K,V> nodoActual){
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int alturaPorIzquierda = alturaR(nodoActual.getHijoIzquierdo());
        int alturaPorDerecha = alturaR(nodoActual.getHijoDrecho());
//        if (alturaPorIzquierda > alturaPorDerecha ) {
//            return alturaPorIzquierda + 1;
//        }else{
//            return alturaPorDerecha + 1;
//        }
        return alturaPorIzquierda > alturaPorDerecha? alturaPorIzquierda + 1: alturaPorDerecha + 1;
    }
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
        //String m="";
        String q="    ";
        String s="";
        int i=0;
        while((alt-ele)!=i){
            i++;
            s=s/*+m*/+q;
        }
        s=Der+s+"|"+String.valueOf(aux.getClave())+"|"+'\12';
        String Izq=ImprimirArbol(aux.getHijoIzquierdo(), alt, ele-1);
        s=s+Izq;
        return s;       
    }  
    public static void main(String[] args) throws Exception {
      ArbolBinarioBusqueda <Integer, Integer> arbolb = new ArbolBinarioBusqueda<>();
              
        arbolb.insertarRec(40,1);
        arbolb.insertarRec(50,2);
        arbolb.insertarRec(30,3);
        arbolb.insertarRec(28,4);
        arbolb.insertarRec(25,5);
        arbolb.insertarRec(35,6);
        arbolb.insertarRec(33,7);
        arbolb.insertarRec(48,8);
        arbolb.insertarRec(45,9);
        System.out.println("------------ARBOL-------------");
        arbolb.ImprimirArbol();
        System.out.println("------------------------------");
        System.out.println("cantidad de nodos que tienen solo hijo izquierdo "+ arbolb.cantNodHijIzq());
        System.out.println("cantidad de nodos que tienen solo hijo izquierdo en el nivel requerido "+ arbolb.cantNodosSoloHijIzqEnNivel(2));
        System.out.println("cantidad de nodos que tienen solo hijo izquierdo Antes del nivel requerido "+ arbolb.cantNodosSoloHijIzqAntNivel(2));
        System.out.println("cantidad de nodos que tienen solo hijo izquierdo Desoues del nivel requerido "+ arbolb.cantNodosSoloHijIzqDespNivel(1));
      }
    
}
