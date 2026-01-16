package projectoad;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author dam2_alu09@inf.ald
 */
public class PersonajesTableDialogController {

    private PersonajesTableDialog view;

    public PersonajesTableDialogController(PersonajesTableDialog view) {
        this.view = view;
        cargarDatos();
        System.out.println("Hola");
    }
    
    private void cargarDatos(){
    
        try {
            Vector row = new Vector();
            URL url = (new URI("https://static.wikia.nocookie.net/inazuma-eleven/images/a/a7/Endou_Mamoru_%28Orion%29.png/revision/latest?cb=20210926134601").toURL());
            BufferedImage img = ImageIO.read(url);
            for (String a : ImageIO.getReaderFormatNames()) {
                System.out.println(a);
            }
            int rowHeight = 80;
            int newWidth = img.getWidth() * rowHeight / img.getHeight();
            Image scaled = img.getScaledInstance(newWidth, rowHeight, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaled);
            row.add(icon);
            view.addRowJugadoresTable(row);
        } catch (URISyntaxException ex) {
            java.util.logging.Logger.getLogger(PersonajesTableDialogController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            java.util.logging.Logger.getLogger(PersonajesTableDialogController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(PersonajesTableDialogController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    
    }
    
}
