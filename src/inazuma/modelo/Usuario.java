/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inazuma.modelo;

import java.io.Serializable;

/**
 *
 * @author dam2_alu02@inf.ald
 */
public class Usuario implements Serializable {

    private int id;
    private String name;
    private String passwd;
    

    public Usuario(int id, String usuario, String contraseña) {
        this.id = id;
        this.name = usuario;
        this.passwd = contraseña;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return name;
    }

    public void setUsuario(String usuario) {
        this.name = usuario;
    }

    public String getContraseña() {
        return passwd;
    }

    public void setContraseña(String contraseña) {
        this.passwd = contraseña;
    }
}