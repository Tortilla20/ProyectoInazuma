/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inazuma;


import inazuma.model.Personaje;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dam2_alu02@inf.ald
 */
public class OperacionBD {
    private static Connection conexion;
    
    public static Connection abrirConexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Cambiar url por nosa base de datos
            String url = "jdbc:mysql://localhost:3306/inazumaeleven";
            conexion = DriverManager.getConnection(url, "root", "abc123.");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OperacionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OperacionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexion;
        
    }
    
    public static void cerrarConexion(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(OperacionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<Personaje> getPersonajes(){
        List<Personaje> listaPersonajes = new ArrayList<>();
        
        try {
            String consultaSQL = "SELECT * FROM personaje";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(consultaSQL);
            
            while(rs.next()){
                /*System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                System.out.println(rs.getString(4));
                System.out.println(rs.getString(5));
                System.out.println(rs.getString(6));
                System.out.println(rs.getString(7));
                System.out.println(rs.getInt(8));*/
                
                Personaje p = new Personaje(rs.getInt(1),rs.getString(2));
                p.setAlias(rs.getString(3));
                p.setDescription(rs.getString(4));
                p.setPosicion(rs.getString(5));
                p.setGenero(rs.getString(6));
                p.setImage(rs.getString(7));
                p.setAtributo(rs.getInt(8));
                listaPersonajes.add(p);
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(OperacionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPersonajes;
    }
    
    public static void addPersonaje(Personaje personaje){
        String sentencia = "INSERT INTO personaje (nombre,alias,descripcion,posicion,id_atributo,genero) VALUES (?,?,?,?,?,?)";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(sentencia);

            ps.setString(1, personaje.getNombre());
            ps.setString(2, personaje.getAlias());
            ps.setString(3, personaje.getDescription());
            ps.setString(4, personaje.getPosicion());
            ps.setInt(5, personaje.getAtributo());
            ps.setString(6, personaje.getGenero());
            ps.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(OperacionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public static void modifyAtributo(String nombre, String atrib){
        String sentencia = "UPDATE personaje "
                + "SET atributo = ? "
                + "WHERE nombre = ?";
        String nombrePersonaje = nombre;
        String atributo = atrib;
        
        try {
            PreparedStatement ps = conexion.prepareStatement(sentencia);
            ps.setString(1, nombrePersonaje);
            ps.setString(2, atributo);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(OperacionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void modifyPersonaje(Personaje personaje){
    
        String sentencia = "UPDATE personaje "
                + "SET description = ?, posicion = ?, atributo = ?, genero = ?, imagen = ? "
                + "WHERE id = ? AND nombre = ?";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(sentencia);
            ps.setString(1, personaje.getDescription());
            ps.setString(2, personaje.getPosicion());
            ps.setInt(3, personaje.getAtributo());
            ps.setString(4, personaje.getGenero());
            ps.setString(5, personaje.getImage());
            ps.setInt(6, personaje.getId());
            ps.setString(7, personaje.getNombre());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OperacionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void addSupertecnica(){}
    
}
