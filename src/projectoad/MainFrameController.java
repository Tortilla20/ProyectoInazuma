/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectoad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author dam2_alu09@inf.ald
 */
public class MainFrameController {
    
    private final MainFrame view;

    public MainFrameController(MainFrame view) {
        this.view = view;
        this.view.setPersonajesTableMenuItemActionListener(this.getPersonajesTableMenuItemActionListener());
        //TODO otros menuItems
    }
    
    public ActionListener getPersonajesTableMenuItemActionListener(){
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
