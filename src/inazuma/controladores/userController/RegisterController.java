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
    private List<Usuario> listaUsuarios;

    public RegisterController(RegisterDialog view, MainFrame mainFrame) {
        this.view = view;
        this.mainFrame = mainFrame;
        this.view.addRegisterButtonActionListener(registerListener());
        actualizarListaUsuarios();
    }

    private void actualizarListaUsuarios() {
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
                
                boolean existe = false;
                for (Usuario u : listaUsuarios) {
                    if (u.getUsuario().toLowerCase().equals(username.toLowerCase())) {
                        JOptionPane.showMessageDialog(mainFrame, "Datos erróneos");
                        existe = true;
                    }
                }
                if (existe == false) {
                    Usuario usuario = new Usuario(listaUsuarios.size(),username,passwd);
                    JOptionPane.showMessageDialog(view, "Registro exitoso");
                    OperacionBD.anhadirUsuario(usuario);
                    OperacionBD.actualizarUsuarioActual(usuario);
                    actualizarListaUsuarios();
                    

                    mainFrame.pantallaLogueada();
                    mainFrame.setUsuarioLogueado(usuario.getUsuario());
                    mainFrame.setVisible(true);
                    view.dispose();
                }

            }
        };
        return al;
    }
}
