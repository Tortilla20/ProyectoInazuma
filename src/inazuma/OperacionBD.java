/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inazuma;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dam2_alu02@inf.ald
 */
public class OperacionBD {
    private static Connection conexion;
    
    public static void abrirConexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Cambiar url por nosa base de datos
            String url = "jdbc:mysql://localhost:3306/bdempresa";
            conexion = DriverManager.getConnection(url, "root", "abc123.");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OperacionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OperacionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void cerrarConexion(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(OperacionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void crearPersonaje(){
        
    }
    
}
