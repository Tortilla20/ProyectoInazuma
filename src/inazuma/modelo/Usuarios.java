/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inazuma.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class Usuarios implements Serializable{
    private List<Usuario> users;
    private String currentUsers;

    public Usuarios() {
        this.users = new ArrayList();
    }

    public List<Usuario> getUsers() {
        return users;
    }
    
    public void addUsers(Usuario user) {
        this.users.add(user);
    }
    
    public int getSize() {
        return this.users.size();
    }
    
    public Usuario getUsuario(int n) {
        return this.users.get(n);
    }
}