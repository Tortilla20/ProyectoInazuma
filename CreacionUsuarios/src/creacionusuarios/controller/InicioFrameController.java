/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package creacionusuarios.controller;

import creacionusuarios.view.LoginFrame;
import creacionusuarios.view.PantallaInicioFrame;
import creacionusuarios.view.RegisterFrame;
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
                RegisterFrame rf = new RegisterFrame();
                RegisterController rc = new RegisterController(rf);
                rf.setVisible(true);
                view.dispose();
            }
        };
        return al;
    }
    
    private ActionListener getLoginButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginFrame lf  = new LoginFrame();
                LoginController lc = new LoginController(lf);
                lf.setVisible(true);
                view.dispose();
            }
        };
        return al;
    }
}