package codexinazuma;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import codexinazuma.modelo.Atributo;
import codexinazuma.modelo.Equipo;
import codexinazuma.modelo.Personaje;
import codexinazuma.modelo.Supertecnica;
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
    private static List<Atributo> listaAtributos;
    
    public static Connection abrirConexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Cambiar url por nosa base de datos
            String url = "jdbc:mysql://localhost:3306/inazumaeleven";
            conexion = DriverManager.getConnection(url, "root", "abc123.");
            getAllAtributos();
            
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
                Atributo atributo = null;
                Personaje p = new Personaje(rs.getInt(1),rs.getString(2),atributo);
                p.setAlias(rs.getString(3));
                p.setDescription(rs.getString(4));
                p.setPosicion(rs.getString(5));
                p.setGenero(rs.getString(6));
                p.setImage(rs.getString(7));
                int id_atributo = rs.getInt(8);
                System.out.println(id_atributo);
                for(Atributo a : listaAtributos){
                    if(a.getId() == id_atributo){
                        atributo = a;
                    }
                }
                System.out.println(atributo);
                p.setAtributo(atributo);
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
            ps.setInt(5, personaje.getAtributo().getId());
            ps.setString(6, personaje.getGenero());
            ps.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(OperacionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public static void addSupertecnica(Supertecnica supertecnica){
        
        try {
            String sentencia = "INSERT INTO supertecnica(nombre,coordinada,potencia,tipo,id_atributo) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conexion.prepareStatement(sentencia);
            ps.setString(1, supertecnica.getNombre());
            ps.setBoolean(2, supertecnica.isCoordinada());
            ps.setInt(3, supertecnica.getPotencia());
            ps.setString(4, supertecnica.getTipo());
            ps.setInt(5, supertecnica.getAtributo().getId());
        } catch (SQLException ex) {

            System.out.println("Error al crear supertecnica en la base de datos");
        }
    
    }
    public static void addEquipo(Equipo equipo){
        try {
            String sentencia = "INSERT INTO equipo (nombre,region,escudo,id_capitan,id_entrenador) VALUES (?,?,?,?,?)";
            
            PreparedStatement ps = conexion.prepareStatement(sentencia);
            ps.setString(1, equipo.getNombre());
            ps.setString(2, equipo.getRegion());
            ps.setString(3, equipo.getEscudo());
            ps.setInt(4, equipo.getCapitan().getId());
            ps.setInt(5, equipo.getEntrenador().getId());
        } catch (SQLException ex) {
            System.out.println("Error al añadir el equipo a la base de datos");
        }
        
    }
    public static void modifyEquipo(Equipo equipo){
        try {
            String sentencia = "UPDATE equipo SET nombre = ? "
                    + "region = ? "
                    + "escudo = ? "
                    + "id_capitan = ? "
                    + "id_entrenador = ? "
                    + "WHERE id = ?";
            
            PreparedStatement ps = conexion.prepareStatement(sentencia);
            ps.setString(1, equipo.getNombre());
            ps.setString(2, equipo.getRegion());
            ps.setString(3, equipo.getEscudo());
            ps.setInt(4, equipo.getCapitan().getId());
            ps.setInt(5, equipo.getEntrenador().getId());
            ps.setInt(6, equipo.getId());
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el equipo a la base de datos");
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
            ps.setInt(3, personaje.getAtributo().getId());
            ps.setString(4, personaje.getGenero());
            ps.setString(5, personaje.getImage());
            ps.setInt(6, personaje.getId());
            ps.setString(7, personaje.getNombre());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OperacionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static void getAllAtributos(){
    
        listaAtributos = new ArrayList<>();
        try {
            String sentencia = "SELECT * FROM atributo";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sentencia);
            
            while(rs.next()){
                Atributo atributo = new Atributo(rs.getInt(1), rs.getString(2),rs.getString(3));
                listaAtributos.add(atributo);
            }
        } catch (SQLException ex) {
            System.getLogger(OperacionBD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    public static List<Atributo> getListaAtributos(){
        return listaAtributos;
    }
    public static List<Supertecnica> getListaSupertecnicas(){
        List<Supertecnica> listaSupertecnica = new ArrayList<>();
        try {
            
            String sentencia = "SELECT * FROM supertecnia";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sentencia);
            
            while(rs.next()){
                Atributo atributo = null;
                int id_atributo = rs.getInt(5);
                for(Atributo a : listaAtributos){
                    if(a.getId() == id_atributo){
                        atributo = a;
                    }
                }
                listaSupertecnica.add(new Supertecnica(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4), atributo, rs.getString(6)));
            }
            
            return listaSupertecnica;
        } catch (SQLException ex) {
            System.getLogger(OperacionBD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return listaSupertecnica;
    
    }
    
    
}