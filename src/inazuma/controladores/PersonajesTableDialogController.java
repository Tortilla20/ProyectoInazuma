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
    private PersonajesTableDialogController esto = this;

    public PersonajesTableDialogController(PersonajesTableDialog view) {
        this.view = view;
        this.view.setAplicarButtonActionListener(this.getAplicarButtonActionListener());
        this.view.setMostrarButtonActionListener(this.getMostrarButtonActionListener());
        this.view.setAnhadirButtonActionListener(this.getAnhadirButtonActionListener());
        this.view.setModificarButtonActionListener(this.getModificarButtonActionListener());
        this.view.setBorrarButtonActionListener(this.getBorrarButtonActionListener());
        cargarDatos();
    }

    public void cargarDatos() {

        //System.out.println(OperacionBD.getListaAtributos());
        view.clearJugadoresTable();
        System.out.println(view.getNombreTextFieldValue() + " | " + view.getGeneroComboBoxSelectedValue() + " | " + view.getPosicionComboBoxSelectedValue() + " | " + view.getAtributoComboBoxSelectedValue());
        List<Personaje> lista = OperacionBD.getPersonajes(view.getNombreTextFieldValue(), view.getGeneroComboBoxSelectedValue(), view.getPosicionComboBoxSelectedValue(), view.getAtributoComboBoxSelectedValue());

        for (Personaje p : lista) {
            Vector row = new Vector();
            row.add(getImageIcon(p.getImage()));
            row.add(p.getNombre());
            row.add(p.getGenero());
            row.add(p.getPosicion());
            row.add(getImageIcon(p.getAtributo().getImagen()));
            view.addRowJugadoresTable(row);
        }

    }

    public int getSelectedPlayerID(int row) {
        List<Personaje> lista = OperacionBD.getPersonajes(view.getNombreTextFieldValue(), view.getGeneroComboBoxSelectedValue(), view.getPosicionComboBoxSelectedValue(), view.getAtributoComboBoxSelectedValue());
        return lista.get(row).getId();
    }

    private ImageIcon getImageIcon(String enlace) {

        if (enlace == null || enlace.isBlank()) {

            return new ImageIcon();
        }

        try {

            URL url = new URL(enlace);

            BufferedImage img = ImageIO.read(url);

            if (img == null) {

                return new ImageIcon();
            }

            int rowHeight = 80;
            int newWidth = img.getWidth() * rowHeight / img.getHeight();
            Image scaled = img.getScaledInstance(newWidth, rowHeight, Image.SCALE_SMOOTH);

            return new ImageIcon(scaled);

        } catch (MalformedURLException e) {

            return new ImageIcon();
        } catch (IOException e) {

            return new ImageIcon();
        } catch (Exception e) {

            return new ImageIcon();
        }
    }

    private ActionListener getAplicarButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatos();
            }
        };
        return al;
    }

    private ActionListener getMostrarButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getJugadoresTableSelectedItem() != -1) {
                    PersonajeDialog newView = new PersonajeDialog(view, true);
                    PersonajeDialogController newController = new PersonajeDialogController(newView, getSelectedPlayerID(view.getJugadoresTableSelectedItem()), false, esto);
                    newView.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(view, "Porfavor selecciona un personaje en la tabla");
                }

            }
        };
        return al;
    }

    private ActionListener getModificarButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getJugadoresTableSelectedItem() != -1) {
                    PersonajeDialog newView = new PersonajeDialog(view, true);
                    PersonajeDialogController newController = new PersonajeDialogController(newView, getSelectedPlayerID(view.getJugadoresTableSelectedItem()), true, esto);
                    newView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(view, "Porfavor selecciona un personaje en la tabla");
                }
            }
        };
        return al;
    }

    private ActionListener getAnhadirButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desactivarSeleccion();
                PersonajeDialog newView = new PersonajeDialog(view, true);
                PersonajeDialogController newController = new PersonajeDialogController(newView, -1, true, esto);
                newView.setVisible(true);
            }
        };
        return al;
    }

    private void desactivarSeleccion() {
        view.desactivarSeleccion();
    }

    private ActionListener getBorrarButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getJugadoresTableSelectedItem() != -1) {
                    int confirmar = JOptionPane.showConfirmDialog(view, "Esta usted seguro?");
                    if (confirmar == 0) {
                        System.out.println("Se hace");
                        OperacionBD.borrarPersonaje(getSelectedPlayerID(view.getJugadoresTableSelectedItem()));
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Selecciona un personaje que borrar");
                }
                cargarDatos();
            }
        };
        return al;
    }

}
