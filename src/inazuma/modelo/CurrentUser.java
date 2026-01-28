/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inazuma.modelo;

/**
 *
 * @author alumno
 */
public class CurrentUser {
    private static Usuario currentUser;
    
    public static Usuario getCurrentUser() {
        return currentUser;
    }
    
    public static void setCurrentUser(Usuario user) {
        currentUser = user;
    }
}