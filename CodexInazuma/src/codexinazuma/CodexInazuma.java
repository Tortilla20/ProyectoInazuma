/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package codexinazuma;

import codexinazuma.controladores.MainFrameController;
import codexinazuma.vistas.MainFrame;
import java.sql.*;

/**
 *
 * @author aol
 */
public class CodexInazuma {

    public static void main(String[] args) {
        
        Connection conexion = OperacionBD.abrirConexion();
        MainFrame mainView = new MainFrame();
        MainFrameController fc = new MainFrameController(mainView);
        mainView.setVisible(true);
        
    }
    
}
