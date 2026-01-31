package inazuma;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import inazuma.modelo.Atributo;
import inazuma.modelo.CurrentUser;
import inazuma.modelo.Equipo;
import inazuma.modelo.Personaje;
import inazuma.modelo.Supertecnica;
import inazuma.modelo.Usuario;
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
    private static Usuario usuarioActual = new Usuario(2, "invitado", "abc123.");
    //private static int invitado;
    private static int admin;

    public static Connection abrirConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Cambiar url por nosa base de datos
            String url = "jdbc:mysql://localhost:3306/inazumaeleven";
            conexion = DriverManager.getConnection(url, "root", "abc123.");
            getAllAtributos();
            getUsuarios();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OperacionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OperacionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexion;

    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void borrarPersonaje(int idPersonaje){

        try {
            String sentencia = "DELETE FROM personaje WHERE id = ?";
            PreparedStatement ps = conexion.prepareStatement(sentencia);
            ps.setInt(1, idPersonaje);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(OperacionBD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }
    
    public static Atributo getAtributo (String nombre){
        Atributo atributo = null;
        for(Atributo a : listaAtributos){
            if(a.getNombre().equals(nombre)){
                atributo = a;
            }
        }
        return atributo;
    }
    
    public static void addPersonaje(String icono, String nombre, String alias, String genero, String posicion, String atributo, String descripcion){
        String sentencia = "INSERT INTO personaje (nombre,alias,descripcion,posicion,id_atributo,genero,id_usuario) VALUES (?,?,?,?,?,?,?)";
        Atributo atrib = null;
        for(Atributo a : listaAtributos){
            if(a.getNombre().equals(atributo)){
                atrib = a;
            }
        }
        try {
            PreparedStatement ps = conexion.prepareStatement(sentencia);
            ps.setString(1, nombre);
            ps.setString(2,alias);
            ps.setString(3, descripcion);
            ps.setString(4, posicion);
            ps.setInt(5, atrib.getId());
            ps.setString(6, genero);
            ps.setInt(7, usuarioActual.getId());
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(OperacionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(OperacionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Personaje> getPersonajes(String busqueda, String genero, String posicion, String atributoEntrante) {

        List<Personaje> listaPersonajes = new ArrayList<>();
        String consultaSQL = "SELECT * FROM personaje WHERE Nombre LIKE '%" + busqueda + "%' ";
        StringBuilder sb = new StringBuilder();
        sb.append(consultaSQL);

        try {

            if (usuarioActual.getId() == admin) {

            } else {
                sb.append("AND ID_USUARIO = " + usuarioActual.getId() + " ");

            }
            if (!genero.isBlank() || !genero.isEmpty()) {
                sb.append("AND GENERO LIKE '" + genero + "' ");
            }
            if (!posicion.isBlank() || !posicion.isEmpty()) {
                sb.append("AND POSICION LIKE '" + posicion + "' ");
            }
            if (!atributoEntrante.isBlank() || !atributoEntrante.isEmpty()) {
                for (Atributo a : listaAtributos) {
                    if (a.getNombre().equals(atributoEntrante)) {
                        atributoEntrante = String.valueOf(a.getId());
                    }
                }
                sb.append("AND ID_ATRIBUTO LIKE '" + atributoEntrante + "' ");
            }
            sb.append(";");

            Statement st = conexion.createStatement();
            System.out.println(sb.toString());
            ResultSet rs = st.executeQuery(sb.toString());

            while (rs.next()) {

                Atributo atributo = null;
                Personaje p = crearPersonajeModelo(rs, atributo);
                listaPersonajes.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OperacionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPersonajes;
    }

    private static Personaje crearPersonajeModelo(ResultSet rs, Atributo atributo) throws SQLException {

        Personaje p = new Personaje(rs.getInt(1), rs.getString(2), atributo, usuarioActual);
        p.setAlias(rs.getString(3));
        p.setDescription(rs.getString(4));
        p.setPosicion(rs.getString(5));
        p.setGenero(rs.getString(6));
        p.setImage(rs.getString(7));
        int id_atributo = rs.getInt(8);
        for (Atributo a : listaAtributos) {
            if (a.getId() == id_atributo) {
                atributo = a;
            }
        }
        p.setAtributo(atributo);
        return p;
    }

    public static Personaje getPersonaje(int id) {
        Personaje personaje = null;
        try {
            String sentencia = "SELECT * FROM personaje WHERE id = " + id;
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sentencia);

            while (rs.next()) {
                Atributo atributo = null;
                personaje = crearPersonajeModelo(rs, atributo);
            }
        } catch (SQLException ex) {
            System.getLogger(OperacionBD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return personaje;
    }

    public static void addPersonaje(Personaje personaje) {
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

    public static void addSupertecnica(Supertecnica supertecnica) {

        try {
            String sentencia = "INSERT INTO supertecnica(nombre,coordinada,potencia,tipo,id_atributo) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conexion.prepareStatement(sentencia);
            ps.setString(1, supertecnica.getNombre());
            ps.setBoolean(2, supertecnica.isCoordinada());
            ps.setString(3, supertecnica.getPotencia());
            ps.setString(4, supertecnica.getTipo());
            ps.setInt(5, supertecnica.getAtributo().getId());
        } catch (SQLException ex) {

            System.out.println("Error al crear supertecnica en la base de datos");
        }

    }

    public static void addEquipo(Equipo equipo) {
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

    public static void modifyEquipo(Equipo equipo) {
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

    public static void modifyAtributo(String nombre, String atrib) {
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

    public static void modifyPersonaje(Personaje personaje) {

        String sentencia = "UPDATE personaje "
                + "SET descripcion = ?, posicion = ?, id_atributo = ?, genero = ?, imagen = ? "
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
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OperacionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void borrarTieneSupertecnicaPersonaje(int idPersonaje, int idSupertecnica){
        try {
            String sentencia = "DELETE FROM tiene WHERE ID_PERSONAJE = ? AND ID_SUPERTECNICA = ?";
            PreparedStatement ps = conexion.prepareStatement(sentencia);
            ps.setInt(1, idPersonaje);
            ps.setInt(2, idSupertecnica);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(OperacionBD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public static void insertarTieneSupertecnicaPersonaje(int idPersonaje, int idSupertecnica){

        try {
            String sentencia = "INSERT INTO tiene VALUES(?,?)";
            PreparedStatement ps = conexion.prepareStatement(sentencia);
            ps.setInt(1, idPersonaje);
            ps.setInt(2, idSupertecnica);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.getLogger(OperacionBD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }
    
    public static void borrarJuegaEquipo(int idPersonaje, int idEquipo){

        try {
            String sentencia = "DELETE FROM juega WHERE id_Personaje = ? AND id_Equipo = ?";
            PreparedStatement ps = conexion.prepareStatement(sentencia);
            ps.setInt(1, idPersonaje);
            ps.setInt(2, idEquipo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(OperacionBD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public static void insertJuegaEquipo(int personaje, int equipo){
        try {
            String sentencia = "INSERT INTO juega VALUES (?,?)";
            PreparedStatement ps = conexion.prepareStatement(sentencia);
            ps.setInt(1, personaje);
            ps.setInt(2, equipo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(OperacionBD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    private static void getAllAtributos() {

        listaAtributos = new ArrayList<>();
        try {
            String sentencia = "SELECT * FROM atributo";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sentencia);

            while (rs.next()) {
                Atributo atributo = new Atributo(rs.getInt(1), rs.getString(2), rs.getString(3));
                listaAtributos.add(atributo);
            }
        } catch (SQLException ex) {
            System.getLogger(OperacionBD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public static List<Atributo> getListaAtributos() {
        return listaAtributos;
    }

    public static List<Supertecnica> getListaSupertecnicas() {
        List<Supertecnica> listaSupertecnica = new ArrayList<>();
        try {

            String sentencia = "SELECT supertecnica.ID, supertecnica.NOMBRE, supertecnica.COORDINADA, supertecnica.POTENCIA, supertecnica.TIPO, supertecnica.ID_ATRIBUTO FROM supertecnica ;";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sentencia);

            while (rs.next()) {
                Atributo atributo = null;
                int id_atributo = rs.getInt(6);
                for (Atributo a : listaAtributos) {
                    if (a.getId() == id_atributo) {
                        atributo = a;
                    }
                }
                listaSupertecnica.add(new Supertecnica(rs.getInt(1),rs.getString(2),rs.getString(4),rs.getBoolean(3),atributo,rs.getString(5)));
            }

            return listaSupertecnica;
        } catch (SQLException ex) {
            System.getLogger(OperacionBD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return listaSupertecnica;

    }
    
    public static void actualizarUsuarioActual(Usuario usuario) {
        usuarioActual = usuario;
    }

    private static void getUsuariosYAdmin() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        try {
            String sentencia = "SELECT * FROM usuario";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sentencia);
            while (rs.next()) {
                listaUsuarios.add(new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            getInvitadoYAdmin(listaUsuarios);

        } catch (SQLException ex) {
            System.out.println("Error en getUsuarioInvitado()");
        }
    }

    public static List<Usuario> getUsuarios() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        try {
            String sentencia = "SELECT * FROM usuario";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sentencia);
            while (rs.next()) {
                listaUsuarios.add(new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }

        } catch (SQLException ex) {
            System.out.println("Error en getUsuarios()");
        }
        return listaUsuarios;
    }

    private static void getInvitadoYAdmin(List<Usuario> listaUsuarios) {
        for (Usuario u : listaUsuarios) {
            /*if (u.getUsuario().toLowerCase().equals("invitado")) {
            invitado = u.getId();
            }*/
            if (u.getUsuario().toLowerCase().equals("admin")) {
                admin = u.getId();
            }

        }
    }

    public static void delPersonaje(Personaje personaje) {
        try {
            String sentencia = "DELETE * FROM personaje WHERE id = " + personaje.getId() + " ;";
            Statement st = conexion.createStatement();
            st.execute(sentencia);
        } catch (SQLException ex) {
            System.getLogger(OperacionBD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public static void delEquipo(Equipo equipo) {
        try {
            String sentencia = "DELETE * FROM equipo WHERE id = " + equipo.getId() + " ;";
            Statement st = conexion.createStatement();
            st.execute(sentencia);
        } catch (SQLException ex) {
            System.getLogger(OperacionBD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public static void delSupertecnica(Supertecnica supertecnica) {
        try {
            String sentencia = "DELETE * FROM supertecnica WHERE id = " + supertecnica.getId() + " ;";
            Statement st = conexion.createStatement();
            st.execute(sentencia);
        } catch (SQLException ex) {
            System.getLogger(OperacionBD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public static List<Equipo> getEquiposDePersonaje(Personaje personaje){
        List<Equipo> listaEquipos = new ArrayList<>();
        String sentencia = "SELECT Equipo.id, Equipo.nombre, Equipo.region, Equipo.escudo, Equipo.id_capitan, Equipo.id_entrenador FROM personaje JOIN juega ON juega.ID_PERSONAJE = personaje.ID JOIN equipo ON Equipo.id = juega.ID_EQUIPO WHERE juega.id_personaje = "+String.valueOf(personaje.getId())+";";
        try { 
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sentencia);
            while(rs.next()){
                crearEquipo(rs, listaEquipos);
            }
        } catch (SQLException ex) {
            System.getLogger(OperacionBD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        return listaEquipos;
    }

    public static List<Supertecnica> getSupertecnicasPersonaje(int idPersonaje){
        List<Supertecnica> listaSupertecnicas = new ArrayList<>();
        try {

            String sentencia = "SELECT supertecnica.id, supertecnica.nombre, supertecnica.coordinada, supertecnica.potencia, supertecnica.tipo, supertecnica.id_atributo FROM supertecnica JOIN tiene ON supertecnica.id = tiene.id_supertecnica WHERE tiene.id_Personaje = ?";
            PreparedStatement ps = conexion.prepareStatement(sentencia);
            ps.setInt(1, idPersonaje);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Atributo atributo = null;
                for(Atributo a : listaAtributos){
                    if(a.getId() == rs.getInt(6)){
                        atributo = a;
                    }
                }

                listaSupertecnicas.add(new Supertecnica(rs.getInt(1),rs.getString(2),rs.getString(4),rs.getBoolean(3),atributo,rs.getString(5)));
            }
        } catch (SQLException ex) {
            System.getLogger(OperacionBD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return listaSupertecnicas;

    }
    
    private static void crearEquipo(ResultSet rs, List<Equipo> listaEquipos) throws SQLException {
        Personaje capitan = getPersonaje(rs.getInt(5));
        Personaje entrenador = getPersonaje(rs.getInt(6));
        listaEquipos.add(new Equipo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), capitan, entrenador));
    }

    public static List<Equipo> getEquipos() {
        String sentencia = "SELECT * FROM equipo;";
        List<Equipo> listaEquipo = new ArrayList<>();
        try {

            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sentencia);

            while (rs.next()) {
                crearEquipo(rs, listaEquipo);
            }
        } catch (SQLException ex) {
            System.getLogger(OperacionBD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        return listaEquipo;
    }

    public static void anhadirUsuario(Usuario usuario) {

        try {
            String sentencia = "INSERT INTO USUARIO (NOMBRE, CONTRASENHA) VALUES (?,?)";
            PreparedStatement ps = conexion.prepareStatement(sentencia);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContraseña());
            ps.execute();
        } catch (SQLException ex) {
            System.getLogger(OperacionBD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public static boolean usuarioEsCorrecto(Usuario usuario) {

        String sentencia = "SELECT * FROM USUARIO WHERE NOMBRE=? AND CONTRASENHA=?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sentencia);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContraseña());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true; 
            } 
        } catch (SQLException ex) {
            System.getLogger(OperacionBD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        return false;

    }

    public static List<Equipo> getListaEquipos(){
        String sentencia = "SELECT * FROM equipo;";
        List<Equipo> listaEquipo = new ArrayList<>();
        try {

            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sentencia);

            while(rs.next()){
                crearEquipo(rs, listaEquipo);
            }
        } catch (SQLException ex) {
            System.getLogger(OperacionBD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        //for (Equipo e : listaEquipo) {
        //    System.out.println(e.getNombre());
        //}
        return listaEquipo;
    }
    
}
