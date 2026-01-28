/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inazuma.controladores.userController;

import inazuma.OperacionBD;
import inazuma.modelo.CurrentUser;
import inazuma.modelo.Usuario;
import inazuma.vistas.users.MainFrame;
import inazuma.vistas.users.RegisterDialog;
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
    private final MainFrame mainFrame;

    public RegisterController(RegisterDialog view, MainFrame mainFrame) {
        this.view = view;
        this.mainFrame = mainFrame;
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
                
                Connection conexion = OperacionBD.abrirConexion();
                try {
                    String insert = "INSERT INTO USUARIO (NOMBRE, CONTRASENHA) VALUES (?,?)";
                    PreparedStatement ps = conexion.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, username);
                    ps.setString(2, passwd);
                    int filas = ps.executeUpdate();
                    
                     if(filas > 0) {
                        ResultSet rs = ps.getGeneratedKeys();
                        if(rs.next()) {
                            int id = rs.getInt(1);

                            Usuario usuario = new Usuario(id, username, passwd);
                            CurrentUser.setCurrentUser(usuario);
                            JOptionPane.showMessageDialog(view, "Registro exitoso");
                            
                            mainFrame.updateForLogin();
                            mainFrame.setVisible(true);
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