/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package creacionusuarios.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class Users implements Serializable{
    private List<User> users;
    private String currentUsers;

    public Users() {
        this.users = new ArrayList();
    }

    public List<User> getUsers() {
        return users;
    }
    
    public void addUsers(User user) {
        this.users.add(user);
    }
    
    public int getSize() {
        return this.users.size();
    }
    
    public User getUsuario(int n) {
        return this.users.get(n);
    }
}