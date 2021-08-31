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
public class MostrarArbol <K extends Comparable<K>,V,P>{
    private int saltos;
    private ArbolBinarioBusqueda arbol;
    public MostrarArbol(ArbolBinarioBusqueda unArbol){
        this.arbol = unArbol;
        this.saltos=0;  
    }
    public int getSaltos() {
        return saltos;
    }
    public void setSaltos(int saltos) {
        this.saltos = saltos;
    }
    public String ImprimirArbol(){
        int m=this.arbol.altura();
        this.setSaltos(0);
        return ImprimirArbol(arbol.raiz,m,m);
    }   
    private String ImprimirArbol(Producto<K,V,P> aux,int alt,int ele){
        if(Producto.esNodoVacio(aux)){
            return "";
        }
        this.setSaltos(this.getSaltos()+1);
        String Der=ImprimirArbol(aux.getHijoDrecho(), alt, ele-1);
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
}
