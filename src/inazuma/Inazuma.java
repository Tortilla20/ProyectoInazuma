/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inazuma;

import inazuma.model.Personaje;
import java.sql.*;
import javax.swing.ImageIcon;

/**
 *
 * @author dam2_alu02@inf.ald
 */
public class Inazuma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println("Bienvenido a la app de inazuma");
        
        Connection conexion = OperacionBD.abrirConexion();
        OperacionBD.getPersonajes();
        Personaje personaje = new Personaje(OperacionBD.getPersonajes().size(),"pepe");
        personaje.setAtributo(1);
   
        OperacionBD.addPersonaje(personaje);
        OperacionBD.getPersonajes();
        

                
                
        
    }
    
}
