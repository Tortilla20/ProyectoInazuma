/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package creacionusuarios;

import creacionusuarios.controller.InicioFrameController;
import creacionusuarios.controller.MusicController;
import creacionusuarios.view.PantallaInicioFrame;
/**
 *
 * @author alumno
 */
public class CreacionUsuarios {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PantallaInicioFrame view = new PantallaInicioFrame();
        InicioFrameController controller = new InicioFrameController(view);
        view.setVisible(true);
    }
}