package inazuma.controladores;

import inazuma.OperacionBD;
import inazuma.modelo.Personaje;
import inazuma.vistas.PersonajeDialog;
import inazuma.vistas.PersonajesTableDialog;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author dam2_alu09@inf.ald
 */
public class PersonajesTableDialogController {

    private final PersonajesTableDialog view;

    public PersonajesTableDialogController(PersonajesTableDialog view) {
        this.view = view;
        this.view.setAplicarButtonActionListener(this.getAplicarButtonActionListener());
        this.view.setMostrarButtonActionListener(this.getMostrarButtonActionListener());
        cargarDatos();
    }
    
    private void cargarDatos(){
        
        //System.out.println(OperacionBD.getListaAtributos());
        view.clearJugadoresTable();
        System.out.println(view.getNombreTextFieldValue()+" | "+view.getGeneroComboBoxSelectedValue()+" | "+view.getPosicionComboBoxSelectedValue()+" | "+view.getAtributoComboBoxSelectedValue());
        List<Personaje> lista = OperacionBD.getPersonajes(view.getNombreTextFieldValue(),view.getGeneroComboBoxSelectedValue(),view.getPosicionComboBoxSelectedValue(),view.getAtributoComboBoxSelectedValue());
        try {
            for( Personaje p : lista ){
                Vector row = new Vector();
                row.add(getImageIcon(p.getImage()));
                row.add(p.getNombre());
                row.add(p.getGenero());
                row.add(p.getPosicion());
                row.add(getImageIcon(p.getAtributo().getImagen()));
                view.addRowJugadoresTable(row);
            }
            //Vector row = new Vector();
            //row.add(getImageIcon("https://cdnb.artstation.com/p/assets/images/images/057/627/223/large/daniel-lopez-mark-camera-5.jpg"));
            //row.add("Mark Evans");
            //row.add("Hombre");
            //row.add("GK");
            //row.add(getImageIcon("https://static.wikia.nocookie.net/inazuma-eleven/images/6/61/Fire_icon_%28VR%29.png"));
            //view.addRowJugadoresTable(row);
        } catch (URISyntaxException ex) {
            java.util.logging.Logger.getLogger(PersonajesTableDialogController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            java.util.logging.Logger.getLogger(PersonajesTableDialogController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(PersonajesTableDialogController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
    
    }
    
    public int getSelectedPlayerID(int row){
        List<Personaje> lista = OperacionBD.getPersonajes(view.getNombreTextFieldValue(),view.getGeneroComboBoxSelectedValue(),view.getPosicionComboBoxSelectedValue(),view.getAtributoComboBoxSelectedValue());
        return lista.get(row).getId();
    }
    
    private ImageIcon getImageIcon(String enlace) throws URISyntaxException, MalformedURLException, IOException{
        if (enlace.equals(null)){
            return new ImageIcon();
        }
            URL url = (new URI(enlace).toURL());
            BufferedImage img = ImageIO.read(url);
            
            int rowHeight = 80;
            int newWidth = img.getWidth() * rowHeight / img.getHeight();
            Image scaled = img.getScaledInstance(newWidth, rowHeight, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaled);
            return icon;
    }
    
    private ActionListener getAplicarButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatos();
            }
        };
                return al;
    }
    
    private ActionListener getMostrarButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (view.getJugadoresTableSelectedItem()!=-1) {
                    PersonajeDialog newView = new PersonajeDialog(view,true);
                    PersonajeDialogController newController = new PersonajeDialogController(newView,getSelectedPlayerID(view.getJugadoresTableSelectedItem()));
                    newView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(view, "Porfavor selecciona un personaje en la tabla");
                }
                
            }
        };
                return al;
    }
    
}
