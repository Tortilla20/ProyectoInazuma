/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inazuma.controladores.userController;

import inazuma.OperacionBD;
import inazuma.modelo.CurrentUser;
import inazuma.modelo.Usuario;
import inazuma.vistas.users.MainFrame;
import inazuma.vistas.users.LoginDialog;
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
    private final MainFrame mainFrame;

    public LoginController(LoginDialog view, MainFrame mainFrame) {
        this.view = view;
        this.mainFrame = mainFrame;
        this.view.addRegisterButtonActionListener(loginListener());
    }
    
    private ActionListener loginListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = view.getUsername();
                String passwd = view.getPasswd();
                
                Connection conexion = OperacionBD.abrirConexion();
                try {
                    String select = "SELECT * FROM USUARIO WHERE NOMBRE=? AND CONTRASENHA=?";
                    PreparedStatement ps = conexion.prepareStatement(select);
                    ps.setString(1, username);
                    ps.setString(2, passwd);
                    
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()) {
                        int id = rs.getInt("ID");
                        Usuario usuario = new Usuario(id, username, passwd);
                        OperacionBD.actualizarUsuarioActual(usuario);
                        CurrentUser.setCurrentUser(usuario);
                        
                        mainFrame.updateForLogin();
                        mainFrame.setVisible(true);
                        view.dispose();
                        //OperacionBD.actualizarUsuarioActual(usuario);
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