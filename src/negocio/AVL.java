/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author user
 */
public class AVL<K extends Comparable<K>,V,P>extends ArbolBinarioBusqueda<K,V,P>{
    private static final byte DIFERECIA_MAXIMA = 1;
    public AVL(){
        
    }
    
    public void insertar(K claveAInsertar, V valorAInsertar,P precio) {
        if (claveAInsertar == null) {
            throw new IllegalArgumentException("Clave no puede ser nula");
        }
        if (valorAInsertar == null) {
            throw new IllegalArgumentException("Valor no puede ser nula");
        }
        super.raiz = this.insertar(this.raiz,claveAInsertar, valorAInsertar, precio);
    }

    private Producto<K, V,P> insertar(Producto<K, V,P> nodoActual, K claveAInsertar, V valorAInsertar, P precio) {
        if (Producto.esNodoVacio(nodoActual)) {
         Producto<K,V,P> nuevoNodo = new Producto<>(claveAInsertar,valorAInsertar,precio);   
         return nuevoNodo;
        }
        K claveActual = nodoActual.getClave();
        if (claveAInsertar.compareTo(claveActual)>0) {
            Producto<K,V,P> supuestoNuevoHijoDerecho = insertar(nodoActual.getHijoDrecho(),
                                                                 claveAInsertar,valorAInsertar,precio);
            nodoActual.setHijoDrecho(supuestoNuevoHijoDerecho);
            return balancear(nodoActual);
        }
        if (claveAInsertar.compareTo(claveActual)<0) {
            Producto<K,V,P> supuestoNuevoHijoIzquierdo = insertar(nodoActual.getHijoIzquierdo(),
                                                                 claveAInsertar,valorAInsertar,precio);
            nodoActual.setHijoIzquierdo(supuestoNuevoHijoIzquierdo);
            return balancear(nodoActual);
        }
        //si llego aca quiere decir que en el nodo actual esta la clave a insertar
        nodoActual.setNombre(valorAInsertar);
        nodoActual.setPrecio(precio);
        return nodoActual;
    }

    private Producto<K, V,P> balancear(Producto<K, V,P> nodoActual){
        int alturaRamaIzq = alturaR(nodoActual.getHijoIzquierdo());
        int alturaRamaDer = alturaR(nodoActual.getHijoDrecho());
        int diferencia = alturaRamaIzq - alturaRamaDer;  
        if (diferencia > DIFERECIA_MAXIMA) {
            //si ay que valancear
            Producto<K,V,P> hijoIzquierdo = nodoActual.getHijoIzquierdo();
            alturaRamaIzq = alturaR(nodoActual.getHijoIzquierdo());
            alturaRamaDer = alturaR(nodoActual.getHijoDrecho());
            if (alturaRamaDer>alturaRamaIzq) {
                return rotacionDobleADerecha(nodoActual);
            }else{
                return rotacionSimpleADerecha(nodoActual);
            }
            
        }else if(diferencia < -DIFERECIA_MAXIMA){ //desbalance por Der -> Rotacion Izquierda
                     Producto<K,V,P> nodoHijoDer = nodoActual.getHijoDrecho();
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
         private Producto<K,V,P> rotacionDobleADerecha(Producto<K,V,P> nodoProblema){
               // trabaja con 3 nodos hijo y nieto
             Producto<K,V,P> nodoHijoIzq = nodoProblema.getHijoIzquierdo();
             Producto<K,V,P> nodoNietoDer = rotacionSimpleAIzquierda(nodoHijoIzq);
             nodoProblema.setHijoIzquierdo(nodoNietoDer);
             
             return rotacionSimpleADerecha(nodoProblema);
       }
       private Producto<K,V,P> rotacionDobleAIzquierda(Producto<K,V,P> nodoProblema){
            // trabaja con 3 nodos hijo y nieto
           Producto<K,V,P> nodoHijoDer = nodoProblema.getHijoDrecho();
           Producto<K,V,P> nodoNietoIzq = rotacionSimpleADerecha(nodoHijoDer);
           nodoProblema.setHijoDrecho(nodoNietoIzq);
           return rotacionSimpleAIzquierda(nodoProblema);
           
       } 
        private Producto<K,V,P> rotacionSimpleADerecha(Producto<K,V,P> nodoProblema){
          Producto<K,V,P> nodoHijoIzq = nodoProblema.getHijoIzquierdo();
          Producto<K,V,P> nodoNietoDer = nodoHijoIzq.getHijoDrecho();
          nodoProblema.setHijoIzquierdo(nodoNietoDer);
          nodoHijoIzq.setHijoDrecho(nodoProblema);
          
          return nodoHijoIzq;
      } 
      private Producto<K,V,P> rotacionSimpleAIzquierda(Producto<K,V,P> nodoProblema){
                 Producto<K,V,P> nodoHijoDer = nodoProblema.getHijoDrecho();
                 Producto<K,V,P> nodoNietoIzq = nodoHijoDer.getHijoIzquierdo();
                  nodoProblema.setHijoDrecho(nodoNietoIzq);
                  nodoHijoDer.setHijoIzquierdo(nodoProblema);
               
                 return nodoHijoDer;
      }  
      
//    8. Implemente el método eliminar de un árbol AVL
    public boolean eliminarNodo(K clave){
        try {
            this.raiz = eliminarR(raiz, clave);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    private Producto<K,V,P> eliminarR(Producto<K,V,P> nodoActual, K datoAEliminar) throws Exception {

        if (Producto.esNodoVacio(nodoActual)) {
            throw new Exception();
        }
        K datoDelNodoActual = nodoActual.getClave();
        if (datoAEliminar.compareTo(datoDelNodoActual) > 0) {
            Producto<K,V,P> nuevoHijoDerecho = eliminarR(nodoActual.getHijoDrecho(), datoAEliminar);
            nodoActual.setHijoDrecho(nuevoHijoDerecho);
            return balancear(nodoActual);
        }
        if (datoAEliminar.compareTo(datoDelNodoActual) < 0) {
            Producto<K,V,P> nuevoHijoIzquierdo = eliminarR(nodoActual.getHijoIzquierdo(), datoAEliminar);
            nodoActual.setHijoIzquierdo(nuevoHijoIzquierdo);
            return balancear(nodoActual);
        }
        //  encontro al datoAEliminar 
        //caso 1
        if (nodoActual.esHoja()) {
            return (Producto<K, V,P>) Producto.nodoVacio();
        }
        //caso 2
        if (nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()) {
            return balancear(nodoActual.getHijoDrecho());
        }
        if (!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()) {
            return balancear(nodoActual.getHijoIzquierdo());
        }
        //caso 3
        Producto<K,V,P> datoSucesor = buscarDatoSucesor(nodoActual.getHijoDrecho());  //  dato sucesor 
        Producto<K,V,P> nuevoNodoHijoDerecho = eliminarR(nodoActual.getHijoDrecho(), datoSucesor.getClave()); // cae un caso base 1 o 2
        nodoActual.setHijoDrecho(nuevoNodoHijoDerecho);
        nodoActual.setClave(datoSucesor.getClave());
        nodoActual.setNombre(datoSucesor.getNombre());
        nodoActual.setPrecio(datoSucesor.getPrecio());
        return balancear(nodoActual);

    }
}
