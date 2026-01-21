package inazuma.controladores;

import inazuma.vistas.MainFrame;
import inazuma.vistas.PersonajesTableDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameController {
    
    private final MainFrame view;

    public MainFrameController(MainFrame view) {
        this.view = view;
        this.view.setPersonajesTableMenuItemActionListener(this.getPersonajesTableMenuItemActionListener());
        this.view.setEquiposTableMenuItemActionListener(this.getEquiposTableMenuItemActionListener());
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
    
}
