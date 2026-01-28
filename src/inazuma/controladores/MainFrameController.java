package inazuma.controladores;

import inazuma.OperacionBD;
import inazuma.controladores.userController.LoginController;
import inazuma.controladores.userController.RegisterController;
import inazuma.modelo.Usuario;
import inazuma.vistas.users.MainFrame;
import inazuma.vistas.PersonajesTableDialog;
import inazuma.vistas.users.LoginDialog;
import inazuma.vistas.users.RegisterDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameController {
    
    private final MainFrame view;
    

    public MainFrameController(MainFrame view) {
        this.view = view;
        this.view.setPersonajesTableMenuItemActionListener(this.getPersonajesTableMenuItemActionListener());
        this.view.setEquiposTableMenuItemActionListener(this.getEquiposTableMenuItemActionListener());
        this.view.addRegisterButtonActionListener(this.getRegisterButtonListener());
        this.view.addLoginButtonActionListener(this.getLoginButtonListener());
        this.view.invitadoActionListener(this.getInvitadoActionListener());
        //TODO otros menuItems
    }
    
    private ActionListener getEquiposTableMenuItemActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                PersonajesTableDialog dialog = new PersonajesTableDialog(view,true);
                PersonajesTableDialogController controller = new PersonajesTableDialogController(dialog);
                dialog.setVisible(true);
            }
        };
        return al;
    }
    
    private ActionListener getPersonajesTableMenuItemActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                PersonajesTableDialog dialog = new PersonajesTableDialog(view,true);
                PersonajesTableDialogController controller = new PersonajesTableDialogController(dialog);
                dialog.setVisible(true);
            }
        };
        return al;
    }
    
    private ActionListener getRegisterButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterDialog rd = new RegisterDialog(view, true);
                RegisterController rc = new RegisterController(rd, view);
                rd.setVisible(true);
            }
        };
        return al;
    }
    
    private ActionListener getLoginButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginDialog ld  = new LoginDialog(view, true);
                LoginController lc = new LoginController(ld, view);
                ld.setVisible(true);
            }
        };
        return al;
    }
    private ActionListener getInvitadoActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {           
                view.enableDisableBotonesLogin(false);
            }
        };
        return al;
    }
}