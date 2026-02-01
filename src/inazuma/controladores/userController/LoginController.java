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
import java.util.List;

/**
 *
 * @author alumno
 */
public class LoginController {

    private final LoginDialog view;
    private final MainFrame mainFrame;
    private List<Usuario> listaUsuarios;

    public LoginController(LoginDialog view, MainFrame mainFrame) {
        this.view = view;
        this.mainFrame = mainFrame;
        this.view.addRegisterButtonActionListener(loginListener());
        actualizarListaUsuarios();
    }

    private ActionListener loginListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = view.getUsername();
                String passwd = view.getPasswd();
                Usuario usuario = new Usuario(listaUsuarios.size(), username, passwd);
                if (OperacionBD.usuarioEsCorrecto(usuario) == true) {
                    OperacionBD.actualizarUsuarioActual(usuario);
                    mainFrame.pantallaLogueada();
                    mainFrame.setUsuarioLogueado(usuario.getUsuario());
                    mainFrame.setVisible(true);
                    view.dispose();
                }

            }
        };
        return al;
    }

    private void actualizarListaUsuarios() {
        listaUsuarios = OperacionBD.getUsuarios();
    }
}
