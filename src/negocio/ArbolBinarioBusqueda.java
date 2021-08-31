
package negocio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class ArbolBinarioBusqueda <K extends Comparable<K>,V,P>{
        protected Producto<K,V,P> raiz;//punto de acceso
        public ArbolBinarioBusqueda(){
               
        }
    
    public void insertar(K claveAInsertar, V valorAInsertar,P precio) {
        if (valorAInsertar == null) {
            throw new IllegalArgumentException("Valor no puede ser nula");
        }
        if (precio == null) {
            throw new IllegalArgumentException("Precio no puede ser nula");
        }
        if (this.esArbolVacio()) {
            this.raiz = new Producto<>(claveAInsertar,valorAInsertar,precio);
            return;
        }
        Producto<K,V,P> nodoActual = this.raiz;//empeiza en la raiz
        Producto<K,V,P> nodoAnterior = (Producto<K,V,P>) Producto.nodoVacio();//empieza en el vacio        
        while(!Producto.esNodoVacio(nodoActual)){// mientras nodo actual sea diferente de vacio
            K claveActual = nodoActual.getClave();//se crea la variable y se asigna la clave del nodo actual
            nodoAnterior = nodoActual;
            if (claveAInsertar.compareTo(claveActual)<0) { //si es menor
                nodoActual = nodoActual.getHijoIzquierdo();
            }else if(claveAInsertar.compareTo(claveActual)>0){//si es mayor
                nodoActual = nodoActual.getHijoDrecho();
            }else{ //si es igual, es decir la clave ya existe, entonces remplazo su valor solamente
                nodoActual.setNombre(valorAInsertar);
                nodoActual.setPrecio(precio);
                return;
            }
        }
        //si llego hasta este punto, quiere decir que no existe en el arbo
        //la clave a insertar, entonces debo crear un nodo, con la clave y valor a insertar
        //y el nodo anterior es el padre de este aux nodo
        Producto<K,V,P> nodoNuevo = new Producto<>(claveAInsertar,valorAInsertar,precio);
        K clavePadre = nodoAnterior.getClave();//se crea la variable y se asigna la clave del nodo actual
        if (claveAInsertar.compareTo(clavePadre)<0) { //si es menor
            nodoAnterior.setHijoIzquierdo(nodoNuevo);
        }else{
            nodoAnterior.setHijoDrecho(nodoNuevo);
        }   

    }
    /* public int altura(){
        if (this.esArbolVacio()) {
            return 0;
        }
        int alturaDelArbol = 0;
        Queue<NodoBinario<K,V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);//si no es vacio empieza con el nodo raiz--offer inserta un elemento a la cola
        while(!colaDeNodos.isEmpty()){//iteramos sobre la cola
            int cantidadDeNodosEnLaCola = colaDeNodos.size();// para controlar la cantidad de nodos 
            int i =0;//que habia con el bucle principal
            while(i<cantidadDeNodosEnLaCola){
                NodoBinario<K,V> nodoActual = colaDeNodos.poll();
                if (!nodoActual.esVacioHijoIzquierdo()) {
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esVacioHijoDerecho()) {
                colaDeNodos.offer(nodoActual.getHijoDrecho());
                }
                i++;
            }//con este while se que estoy procesando todo un nivel
            alturaDelArbol++;
        }
        return alturaDelArbol;
    }
    */
   public boolean esArbolVacio() {
        return Producto.esNodoVacio(this.raiz);
    }
  
    public int nivel() {//El nivel es el nivel mas profundo en el arbol
        return nivel(this.raiz);
    }
    private int nivel(Producto<K,V,P> nodoActual){
        if (Producto.esNodoVacio(nodoActual)) {
            return -1;
        }
        int nivelPorIzquierda = nivel(nodoActual.getHijoIzquierdo());
        int nivelPorDerecha = nivel(nodoActual.getHijoDrecho());
        if (nivelPorIzquierda > nivelPorDerecha ) {
            return nivelPorIzquierda + 1;
        }else{
            return nivelPorDerecha + 1;
        }
        //return alturaPorIzquierda > alturaPorDerecha? alturaPorIzquierda + 1: alturaPorDerecha + 1;
    }
    public int size() {//cuantos nodos tiene el arbol
        if (this.esArbolVacio()) {
            return 0;
        }
        int cantidadDeNodos = 0;
        Stack<Producto<K,V,P>> pilaDeNodos = new Stack<>();
        pilaDeNodos.push(this.raiz);//si no es vacio empieza con el nodo raiz--offer inserta un elemento a la cola
        while(!pilaDeNodos.isEmpty()){//iteramos sobre la cola
            Producto<K,V,P> nodoActual = pilaDeNodos.pop();
            cantidadDeNodos++; //cuenta antes de meter a la lista
            if (!nodoActual.esVacioHijoDerecho()) {
                pilaDeNodos.push(nodoActual.getHijoDrecho());
            }
            if (!nodoActual.esVacioHijoIzquierdo()) {
                pilaDeNodos.push(nodoActual.getHijoIzquierdo());
            }
        }
        return cantidadDeNodos;
    }
    public K minimo() {//La menor clave posible que hay en el arbol
        if (this.esArbolVacio()) {
            return null;
        }
        Producto<K,V,P> nodoActual = this.raiz;
        Producto<K,V,P> nodoAnterior = (Producto<K,V,P>) Producto.nodoVacio();//empieza en el vacio
        while(!Producto.esNodoVacio(nodoActual)){
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijoIzquierdo();
        }
        return nodoAnterior.getClave();
    }
    public K maximo() {//tarea  --- en vez de hijo izquierdo hacer por el derecho
        if (this.esArbolVacio()) {
            return null;
        }
        Producto<K,V,P> nodoActual = this.raiz;
        Producto<K,V,P> nodoAnterior = (Producto<K,V,P>) Producto.nodoVacio();//empieza en el vacio
        while(!Producto.esNodoVacio(nodoActual)){
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijoDrecho();
        }
        return nodoAnterior.getClave();
    }
    public void vaciarArbol() {
        this.raiz =(Producto<K,V,P>) Producto.nodoVacio();
    }
    public boolean eliminarNodo(K clave){
        try {
            this.raiz = eliminarNodo(raiz, clave);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    private Producto<K,V,P> eliminarNodo(Producto<K,V,P> nodoActual, K datoAEliminar) throws Exception {
        if (Producto.esNodoVacio(nodoActual)) {
            throw new Exception();
        }       
        K datoDelNodoActual = nodoActual.getClave();
        
        //llamada recursiva
        if (datoAEliminar.compareTo(datoDelNodoActual) > 0) { //mando a eliminar por el lado izquierdo
            Producto<K,V,P> nuevoHijoDerecho = eliminarNodo(nodoActual.getHijoDrecho(), datoAEliminar);
            nodoActual.setHijoDrecho(nuevoHijoDerecho);
            return nodoActual;
        }
        if (datoAEliminar.compareTo(datoDelNodoActual) < 0) {//mando a eliminar por el lado derecha
            Producto<K,V,P> nuevoHijoIzquierdo = eliminarNodo(nodoActual.getHijoIzquierdo(), datoAEliminar);
            nodoActual.setHijoIzquierdo(nuevoHijoIzquierdo);
            return nodoActual;
        }
        
        // si llego a este punto encontro al datoAEliminar 
        //caso 1
        if (nodoActual.esHoja()) {
            return (Producto<K, V,P>) Producto.nodoVacio();
        }
        //caso 2
        if (nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()) {
            return nodoActual.getHijoDrecho();
        }
        if (!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()) {
            return nodoActual.getHijoIzquierdo();
        }
        //caso 3
        Producto<K,V,P> datoSucesor = buscarDatoSucesor(nodoActual.getHijoDrecho());  //  dato sucesor 
        Producto<K,V,P> nuevoNodoHijoDerecho = eliminarNodo(nodoActual.getHijoDrecho(), datoSucesor.getClave()); // cae un caso base 1 o 2 antes de usarlo hay q eliminarlo
        nodoActual.setHijoDrecho(nuevoNodoHijoDerecho);
        nodoActual.setClave(datoSucesor.getClave());
        nodoActual.setNombre(datoSucesor.getNombre());
        nodoActual.setPrecio(datoSucesor.getPrecio());
        return nodoActual;

    }
    protected Producto<K,V,P> buscarDatoSucesor(Producto<K,V,P> nodoActual) {
        Producto<K,V,P> nodoAnterior = nodoActual;
        while (!Producto.esNodoVacio(nodoActual)) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijoIzquierdo();
        }
        return nodoAnterior;
    }
   
    public V valorDelNodo(K claveABuscar) {// para saber que valor tiene la llave a buscar
        if (claveABuscar == null) {
            throw new IllegalArgumentException("Clave no puede ser nula");
        }
        if (this.esArbolVacio()) {
            return null;
        }
         Producto<K,V,P> nodoActual = this.raiz;
         while(!Producto.esNodoVacio(nodoActual)){// mientras nodo actual sea diferente de vacio
            K claveActual = nodoActual.getClave();//se crea la variable y se asigna la clave del nodo actual
      
            if (claveABuscar.compareTo(claveActual)== 0) {
                return nodoActual.getNombre(); //retorna el valor que contiene esa clave
   
            }else if (claveABuscar.compareTo(claveActual)< 0) {
                nodoActual = nodoActual.getHijoIzquierdo();
            }else{
               nodoActual = nodoActual.getHijoDrecho();                
            }
         }
         //si llego a este punto quiere decir que no se ecnuentra la clave a buscar
         //en el arbol
        return null;
    }
    public boolean existeClave(K claveABuscar) {// para saber que valor tiene la llave a buscar
        if (claveABuscar == null) {
            return false;
        }
        if (this.esArbolVacio()) {
            return false;
        }
         Producto<K,V,P> nodoActual = this.raiz;
         while(!Producto.esNodoVacio(nodoActual)){// mientras nodo actual sea diferente de vacio
            K claveActual = nodoActual.getClave();//se crea la variable y se asigna la clave del nodo actual
      
            if (claveABuscar.compareTo(claveActual)== 0) {
                return true; //retorna el valor que contiene esa clave
   
            }else if (claveABuscar.compareTo(claveActual)< 0) {
                nodoActual = nodoActual.getHijoIzquierdo();
            }else{
               nodoActual = nodoActual.getHijoDrecho();                
            }
         }
         //si llego a este punto quiere decir que no se ecnuentra la clave a buscar
         //en el arbol
        return false;
    }
    public Producto getProducto(K claveABuscar) {// para saber que valor tiene la llave a buscar
        if (claveABuscar == null) {
            throw new IllegalArgumentException("Clave no puede ser nula");
        }
        if (this.esArbolVacio()) {
            return null;
        }
         Producto<K,V,P> nodoActual = this.raiz;
         while(!Producto.esNodoVacio(nodoActual)){// mientras nodo actual sea diferente de vacio
            K claveActual = nodoActual.getClave();//se crea la variable y se asigna la clave del nodo actual
      
            if (claveABuscar.compareTo(claveActual)== 0) {
                return nodoActual; //retorna el valor que contiene esa clave
   
            }else if (claveABuscar.compareTo(claveActual)< 0) {
                nodoActual = nodoActual.getHijoIzquierdo();
            }else{
               nodoActual = nodoActual.getHijoDrecho();                
            }
         }
         //si llego a este punto quiere decir que no se ecnuentra la clave a buscar
         //en el arbol
        return null;
    }
  
     public String recorridoEnPorNiveles() {
        List<Producto> recorrido = new ArrayList<>();
        if (this.esArbolVacio()) {
            return " ";
        }
        Queue<Producto<K,V,P>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);//si no es vacio empieza con el nodo raiz--offer inserta un elemento a la cola
        while(!colaDeNodos.isEmpty()){//iteramos sobre la cola
            Producto<K,V,P> nodoActual = colaDeNodos.poll();
            recorrido.add(nodoActual);
            if (!nodoActual.esVacioHijoIzquierdo()) {
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }
            if (!nodoActual.esVacioHijoDerecho()) {
                colaDeNodos.offer(nodoActual.getHijoDrecho());
            }
        }
        String s = "";
         for (int i = 0; i < recorrido.size(); i++) {
             s = s+"* Clave: " + recorrido.get(i).getClave() +" * Nombre: "+ recorrido.get(i).getNombre()+" * Precio: "+recorrido.get(i).getPrecio() + "\n" ;
         }
        return s;
    }
      public List<Producto> recorridoEnPorNivelesP() {
        List<Producto> recorrido = new ArrayList<>();
        if (this.esArbolVacio()) {
            return null;
        }
        Queue<Producto<K,V,P>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);//si no es vacio empieza con el nodo raiz--offer inserta un elemento a la cola
        while(!colaDeNodos.isEmpty()){//iteramos sobre la cola
            Producto<K,V,P> nodoActual = colaDeNodos.poll();
            recorrido.add(nodoActual);
            if (!nodoActual.esVacioHijoIzquierdo()) {
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }
            if (!nodoActual.esVacioHijoDerecho()) {
                colaDeNodos.offer(nodoActual.getHijoDrecho());
            }
        }
        return recorrido;
    }
    
      public int alturaRec() {//La altura es el nivel + 1 del nodo mas profundo en el arbol
        return alturaR(this.raiz);
    }
    protected int alturaR(Producto<K,V,P> nodoActual){
        if (Producto.esNodoVacio(nodoActual)) {
            return 0;
        }
        int alturaPorIzquierda = alturaR(nodoActual.getHijoIzquierdo());
        int alturaPorDerecha = alturaR(nodoActual.getHijoDrecho());
        return alturaPorIzquierda > alturaPorDerecha? alturaPorIzquierda + 1: alturaPorDerecha + 1;
    }
    
    /*
    11. Implementar un método que retorne verdadero 
    si un árbol binario esta lleno.
    */
     public boolean lleno(){
        int h = alturaR(this.raiz);
        int ch = (int) (Math.pow(2, h) - 1);
        return  ch == size();    
    }
    
    
    
}
