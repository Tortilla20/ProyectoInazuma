/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package creacionusuarios.controller;

import creacionusuarios.view.LoginDialog;
import creacionusuarios.view.PantallaInicioFrame;
import creacionusuarios.view.RegisterDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author alumno
 */
public class InicioFrameController {
    private final PantallaInicioFrame view;

    public InicioFrameController(PantallaInicioFrame view) {
        this.view = view;
        this.view.addRegisterButtonActionListener(this.getRegisterButtonListener());
        this.view.addLoginButtonActionListener(this.getLoginButtonListener());
    }
    
    private ActionListener getRegisterButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterDialog rd = new RegisterDialog(view, true);
                RegisterController rc = new RegisterController(rd);
                rd.setVisible(true);
                view.dispose();
            }
        };
        return al;
    }
    
    private ActionListener getLoginButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginDialog ld  = new LoginDialog(view, true);
                LoginController lc = new LoginController(ld);
                ld.setVisible(true);
                view.dispose();
            }
        };
        return al;
    }
}