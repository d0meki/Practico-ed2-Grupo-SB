/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadeproductoscrudconarboles;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.AVL;
import negocio.MostrarArbol;
import negocio.Producto;

public class SistemaDeProductosCrudConArboles{

    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        AVL <Double, String, Double> edArbol = new AVL<>();
        
        String json = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("productos.json"));
            
            String linea;
            while((linea = br.readLine()) != null){
              json += linea;
            }
            
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SistemaDeProductosCrudConArboles.class.getName()).log(Level.SEVERE, null, ex);
        }      
        Gson gson = new Gson();
        
        Producto<Integer,String,Double>[] producto = gson.fromJson(json, Producto[].class);
        
        for (Producto p: producto) {       
            edArbol.insertar((Double)p.getClave(), (String)p.getNombre(),(Double) p.getPrecio());
        }
        MostrarArbol m = new MostrarArbol(edArbol);
        System.out.println(m.ImprimirArbol()); 
        System.out.println(edArbol.recorridoEnPorNiveles());
        
        
        
       
      
//        edArbol.insertar(40, "Honda CR-V 2021", 6000.0);
//        edArbol.insertar(50, "Honda HR-V 2021", 7000.0);
//        edArbol.insertar(30, "Honda Civic Type R", 8000.0);
//        edArbol.insertar(28, "Honda CR-V Hybrid", 9000.0);
//        edArbol.insertar(35, "Honda Pilot", 10000.0);
//        edArbol.insertar(48, "Honda Passport", 11000.0);
//        edArbol.insertar(51, "Honda Civic Sedan", 12000.0);       
////        System.out.println(edArbol.recorridoEnPorNiveles()); 
//        List<Producto> p = edArbol.recorridoEnPorNivelesP();
//        for (Producto producto: p) {
//            System.out.println("ID: "+ producto.getClave() + " Producto: "+producto.getNombre()+" Precio: " + producto.getPrecio());
//        }
    }
    
}
