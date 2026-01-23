/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package creacionusuarios.controller;

import creacionusuarios.OperacionesBD;
import creacionusuarios.model.CurrentUser;
import creacionusuarios.model.User;
import creacionusuarios.view.PantallaInicioFrame;
import creacionusuarios.view.RegisterDialog;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author alumno
 */
public class RegisterController {
    
    private final RegisterDialog view;

    public RegisterController(RegisterDialog view) {
        this.view = view;
        this.view.addRegisterButtonActionListener(registerListener());
    }
    
    private ActionListener registerListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = view.getUsername();
                String passwd = view.getPasswd();
                
                if(username.isEmpty() || passwd.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Rellena todos los campos");
                    return;
                }
                
                try(Connection conexion = OperacionesBD.abrirConexion()) {
                    String insert = "INSERT INTO USUARIO (NOMBRE, CONTRASENHA) VALUES (?,?)";
                    PreparedStatement ps = conexion.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, username);
                    ps.setString(2, passwd);
                    int filas = ps.executeUpdate();
                    
                     if(filas > 0) {
                        ResultSet rs = ps.getGeneratedKeys();
                        if(rs.next()) {
                            int id = rs.getInt(1);

                            User user = new User(username, passwd);
                            CurrentUser.setCurrentUser(user);
                            JOptionPane.showMessageDialog(view, "Registro exitoso");
                            
                            PantallaInicioFrame pif = new PantallaInicioFrame();
                            pif.updateForLogin();
                            pif.setVisible(true);
                            view.dispose();
                        }
                    }
                } catch(SQLException ex) {
                    JOptionPane.showMessageDialog(view, "Error al registrar: " + ex.getMessage());
                }
            }
        };
        return al;
    }
}