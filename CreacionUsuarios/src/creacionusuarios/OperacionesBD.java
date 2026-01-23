/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package creacionusuarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alumno
 */
public class OperacionesBD {
     public static Connection abrirConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.getLogger(OperacionesBD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        String url = "jdbc:mysql://localhost:3306/InazumaEleven";
        Connection conexion  = DriverManager.getConnection(url,"root","abc123.");
        return conexion;
    }
    
    public static void cerrarConexion(Connection conexion) throws Exception {
        conexion.close();
    }
}