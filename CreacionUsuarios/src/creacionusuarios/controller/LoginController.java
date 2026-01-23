/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package creacionusuarios.controller;

import creacionusuarios.OperacionesBD;
import creacionusuarios.model.User;
import creacionusuarios.model.CurrentUser;
import creacionusuarios.view.LoginDialog;
import creacionusuarios.view.PantallaInicioFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.sql.*;

/**
 *
 * @author alumno
 */
public class LoginController {
    
    private final LoginDialog view;

    public LoginController(LoginDialog view) {
        this.view = view;
        this.view.addRegisterButtonActionListener(loginListener());
    }
    
    private ActionListener loginListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = view.getUsername();
                String passwd = view.getPasswd();
                
                try(Connection conexion = OperacionesBD.abrirConexion()) {
                    String select = "SELECT * FROM USUARIO WHERE NOMBRE=? AND CONTRASENHA=?";
                    PreparedStatement ps = conexion.prepareStatement(select);
                    ps.setString(1, username);
                    ps.setString(2, passwd);
                    
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()) {
                        int id = rs.getInt("ID");
                        User user = new User(username, passwd);
                        CurrentUser.setCurrentUser(user);
                        
                        PantallaInicioFrame pif = new PantallaInicioFrame();
                        pif.updateForLogin();
                        pif.setVisible(true);
                        view.dispose();
                    } else {
                        JOptionPane.showMessageDialog(view, "Credenciales incorrectas");                        
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(view, "Error al conectar: " + ex.getMessage());
                }
            }
        };
        return al;
    }
}