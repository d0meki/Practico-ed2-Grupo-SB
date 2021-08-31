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
public class Producto<K,V,P>{
    private K clave;
    private V nombre;
    private P precio;
    private Producto<K,V,P> hijoIzquierdo;
    private Producto<K,V,P> hijoDrecho;

    public Producto(K clave, V nombre, P precio) {
        this.clave = clave;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Producto() {
       
    }

    public K getClave() {
        return clave;
    }

    public V getNombre() {
        return nombre;
    }

    public void setHijoIzquierdo(Producto<K, V, P> hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public void setHijoDrecho(Producto<K, V, P> hijoDrecho) {
        this.hijoDrecho = hijoDrecho;
    }

    public P getPrecio() {
        return precio;
    }
    public void setClave(K clave) {
        this.clave = clave;
    }

    public void setNombre(V nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(P precio) {
        this.precio = precio;
    }

    public Producto<K, V, P> getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public Producto<K, V, P> getHijoDrecho() {
        return hijoDrecho;
    }

 
    public boolean esVacioHijoIzquierdo(){
    return Producto.esNodoVacio(this.getHijoIzquierdo());// manera correcta  
    }
    
    public boolean esVacioHijoDerecho(){
    return Producto.esNodoVacio(this.getHijoDrecho());// manera correcata
    }
    public boolean esHoja(){
    return this.esVacioHijoDerecho() && this.esVacioHijoIzquierdo();
    }
    
    public boolean esNodoCompleto(){
    return !this.esVacioHijoDerecho() && !this.esVacioHijoIzquierdo();
    }
    
   public static boolean esNodoVacio(Producto nodo){ //se utiliza apra que devuelva una isntacia de clase
        return nodo == Producto.nodoVacio();// ya sea falso o verdadero pero no nunca error
    }
   public static Producto<?,?,?> nodoVacio(){// y va acompañado de este metodo
       return null;
   }
   
   
   
   
    @Override
    public String toString() {
        return "Producto{" + "clave=" + clave + ", nombre=" + nombre + ", precio=" + precio+ '}';
    }
}
