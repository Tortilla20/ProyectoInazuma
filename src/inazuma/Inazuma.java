/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inazuma;

import inazuma.controladores.MainFrameController;
import inazuma.vistas.MainFrame;
import java.sql.*;

/**
 *
 * @author aol
 */
public class Inazuma {

    public static void main(String[] args) {
        
        Connection conexion = OperacionBD.abrirConexion();
        MainFrame mainView = new MainFrame();
        MainFrameController fc = new MainFrameController(mainView);
        mainView.setVisible(true);
        
    }
    
}
