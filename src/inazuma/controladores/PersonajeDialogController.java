package inazuma.controladores;

import inazuma.OperacionBD;
import inazuma.modelo.Personaje;
import inazuma.vistas.PersonajeDialog;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author aol
 */
public class PersonajeDialogController {

    PersonajeDialog view;
    private final int id;

    public PersonajeDialogController(PersonajeDialog view, int idPersonaje) {
        this.view = view;
        this.id = idPersonaje;
        cargarDatos();
    }
    
    private void cargarDatos(){
        
        try {
            Personaje personaje = OperacionBD.getPersonaje(id);
            URL url = (new URI(personaje.getImage()).toURL());
            BufferedImage img = ImageIO.read(url);
            int rowHeight = 134;
            int newWidth = img.getWidth() * rowHeight / img.getHeight();
            Image scaled = img.getScaledInstance(newWidth, rowHeight, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaled);
            this.view.setIconoLabel(icon);
            this.view.setNombreTextField(personaje.getNombre());
            this.view.setAliasTextField(personaje.getAlias());
            this.view.setGeneroComboBoxSelectedItem(personaje.getGenero());
            this.view.setPosicionComboBoxSelectedItem(personaje.getPosicion());
            this.view.setAtributoComboBoxSelectedItem(personaje.getAtributo().getNombre());
            this.view.setDescripcionTextArea(personaje.getDescription());
        } catch (URISyntaxException ex) {
            System.getLogger(PersonajeDialogController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (MalformedURLException ex) {
            System.getLogger(PersonajeDialogController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(PersonajeDialogController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    
}
