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
public class Productos {
    private int clave;
    private String nombre;
    private Double precio;

    public Productos() {
    }

    public Productos(int clave, String nombre, Double precio) {
        this.clave = clave;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Productos{" + "clave=" + clave + ", nombre=" + nombre + ", precio=" + precio + '}';
    }
    
}
