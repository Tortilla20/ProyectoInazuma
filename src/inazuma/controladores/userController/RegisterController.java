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
import java.util.List;

/**
 *
 * @author alumno
 */
public class RegisterController {

    private final RegisterDialog view;
    private final MainFrame mainFrame;
    private final List<Usuario> listaUsuarios;

    public RegisterController(RegisterDialog view, MainFrame mainFrame) {
        this.view = view;
        this.mainFrame = mainFrame;
        this.view.addRegisterButtonActionListener(registerListener());
        listaUsuarios = OperacionBD.getUsuarios();
    }

    private ActionListener registerListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = view.getUsername();
                String passwd = view.getPasswd();

                if (username.isEmpty() || passwd.isEmpty()) {
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

                    if (filas > 0) {
                        ResultSet rs = ps.getGeneratedKeys();
                        if (rs.next()) {
                            int id = rs.getInt(1);

                            Usuario usuario = new Usuario(id, username, passwd);
                            boolean existe = false;
                            for (Usuario u : listaUsuarios) {
                                if (u.getUsuario().toLowerCase().equals(usuario.getUsuario().toLowerCase())) {
                                    JOptionPane.showMessageDialog(mainFrame, "Usuario ya existe");
                                    existe = true;
                                }
                            }
                            if (existe == false) {
                                OperacionBD.actualizarUsuarioActual(usuario);
                                CurrentUser.setCurrentUser(usuario);
                                JOptionPane.showMessageDialog(view, "Registro exitoso");

                                mainFrame.updateForLogin();
                                mainFrame.setVisible(true);
                                view.dispose();
                            }

                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(view, "Error al registrar: " + ex.getMessage());
                }
            }
        };
        return al;
    }
}
