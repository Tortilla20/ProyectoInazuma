package inazuma.controladores;

import inazuma.OperacionBD;
import inazuma.modelo.Equipo;
import inazuma.modelo.Personaje;
import inazuma.modelo.Supertecnica;
import inazuma.vistas.PersonajeDialog;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author aol
 */
public class PersonajeDialogController {

    PersonajeDialog view;
    private final int id;
    PersonajesTableDialogController parentController;

    public PersonajeDialogController(PersonajeDialog view, int idPersonaje, boolean editable, PersonajesTableDialogController parentController) {
        this.view = view;
        this.id = idPersonaje;
        this.view.setAnhadirEquipoButtonActionListener(this.getAnhadirEquipoButtonActionListener());
        this.view.setAnhadirSupertecnicaButtonActionListener(this.getAnhadirSupertecnicaButtonActionListener());
        this.view.setEliminarEquipoButtonActionListener(this.getEliminarEquipoButtonActionListener());
        this.view.setEliminarSupertecnicaButtonActionListener(this.getEliminarSupertecnicaButtonActionListener());
        this.view.setConfirmarButtonActionListener(this.getConfirmarButtonActionListener());
        this.parentController = parentController;
        if (id == -1) {
            view.modoAgregar();
        } else {
            cargarDatos();
            this.view.activarFields(editable);
            popularComboBoxes();
        }
    }
    
    private void popularComboBoxes() {
        List<Supertecnica> supertecnicas = OperacionBD.getListaSupertecnicas();
        for (Supertecnica s : supertecnicas) {
            this.view.setSupertecnicaComboBoxContent(s.getNombre());
        }
        List<Equipo> equipos = OperacionBD.getListaEquipos();
        for (Equipo e : equipos) {
            this.view.setEquipoComboBoxContent(e.getNombre());
        }
    }
    
    private void cargarDatos() {
    try {
        Personaje personaje = OperacionBD.getPersonaje(id);
        String enlaceImagen = personaje.getImage();


        if (enlaceImagen == null || enlaceImagen.isBlank()) {
            this.view.setIconoLabel(new ImageIcon("ruta/a/imagen_defecto.png"));
        } else {
            try {
                URL url = new URL(enlaceImagen);
                BufferedImage img = ImageIO.read(url);

                if (img != null) {
                    int rowHeight = 134;
                    int newWidth = img.getWidth() * rowHeight / img.getHeight();
                    Image scaled = img.getScaledInstance(newWidth, rowHeight, Image.SCALE_SMOOTH);
                    this.view.setIconoLabel(new ImageIcon(scaled));
                } else {
                    this.view.setIconoLabel(new ImageIcon());
                }
            } catch (Exception e) {
                this.view.setIconoLabel(new ImageIcon());
            }
        }

        this.view.setNombreTextField(personaje.getNombre());
        this.view.setAliasTextField(personaje.getAlias());
        this.view.setGeneroComboBoxSelectedItem(personaje.getGenero());
        this.view.setPosicionComboBoxSelectedItem(personaje.getPosicion());
        this.view.setAtributoComboBoxSelectedItem(personaje.getAtributo().getNombre());
        this.view.setDescripcionTextArea(personaje.getDescription());
        this.view.setIconoTextField(personaje.getImage());

        this.recargarTablaEquipos();
        this.recargarTablaSupertecnicas();
    } catch (Exception e) {
       
    }
}
    
    private void recargarTablaSupertecnicas(){
        this.view.clearSupertecnicasTable();
        Personaje personaje = OperacionBD.getPersonaje(id);
        List<Supertecnica> supertecnicas = OperacionBD.getSupertecnicasPersonaje(id);
        for (Supertecnica s : supertecnicas) {

                Vector row = new Vector();
                row.add(s.getNombre());
                row.add(s.getPotencia());
                row.add(s.getTipo());
                row.add(s.isCoordinada());
                row.add(getImageIcon(s.getAtributo().getImagen()));
                view.addRowSupertecnicasTable(row);

        }
    }
    
    private void agregarSupertecnica(){
        String supertecnica = view.getSupertecnicaComboBoxSelected();
        List<Supertecnica> supertecnicas = OperacionBD.getListaSupertecnicas();
        Personaje personaje = OperacionBD.getPersonaje(id);
        int flag = -1;
        int idSupertecnica = 0;
        for (Supertecnica s : supertecnicas) {
            if (supertecnica.equals(s.getNombre())) {
                List<Supertecnica> supertecnicasDePersonaje = OperacionBD.getSupertecnicasPersonaje(id);
                idSupertecnica = s.getId();
                for (Supertecnica sdp : supertecnicasDePersonaje) {
                    if (sdp.getNombre().equals(supertecnica)) {
                        flag = 1;
                    }
                }
            }
        }
        if (flag == -1) {
            OperacionBD.insertarTieneSupertecnicaPersonaje(id, idSupertecnica);
        }
    }
    
    private void eliminarSupertecnica(int selected){
        //Personaje personaje = OperacionBD.getPersonaje(id);
        List<Supertecnica> supertecnicas = OperacionBD.getSupertecnicasPersonaje(id);
        OperacionBD.borrarTieneSupertecnicaPersonaje(id, supertecnicas.get(selected).getId());
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
    
    private void recargarTablaEquipos(){
        this.view.clearEquiposTable();
        Personaje personaje = OperacionBD.getPersonaje(id);
        List<Equipo> equipos = OperacionBD.getEquiposDePersonaje(personaje);
        for (Equipo e : equipos) {
            
                Vector row = new Vector();
                row.add(getImageIcon(e.getEscudo()));
                row.add(e.getNombre());
                row.add(e.getRegion());
                if (e.getCapitan()!=null) {
                    row.add(getImageIcon(e.getCapitan().getImage()));
                } else {
                    row.add("");
                }
                if (e.getEntrenador()!=null) {
                    row.add(getImageIcon(e.getEntrenador().getImage()));
                } else {
                    row.add("");
                }
                view.addRowEquiposTable(row);

        }
    }
    
    private void agregarEquipo() {
        String equipo = this.view.getEquipoComboBoxSelected();
        List<Equipo> equipos = OperacionBD.getListaEquipos();
        Personaje personaje = OperacionBD.getPersonaje(id);
        int flag = -1;
        int idEquipo = 0;
        for (Equipo e : equipos) {
            if (equipo.equals(e.getNombre())) {
                List<Equipo> equiposDePersonaje = OperacionBD.getEquiposDePersonaje(personaje);
                idEquipo = e.getId();
                for (Equipo edp : equiposDePersonaje) {
                    if (edp.getNombre().equals(equipo)) {
                        flag = 1;
                    }
                }
            }
        }
        if (flag == -1) {
            OperacionBD.insertJuegaEquipo(id, idEquipo);
        }
    }
    
    private void eliminarEquipo(int selected) {
        Personaje personaje = OperacionBD.getPersonaje(id);
        List<Equipo> equiposDePersonaje = OperacionBD.getEquiposDePersonaje(personaje);
        OperacionBD.borrarJuegaEquipo(id, equiposDePersonaje.get(selected).getId());
    }
    
    private ActionListener getAnhadirEquipoButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarEquipo();
                recargarTablaEquipos();
            }
        };
        return al;
    }
    
    private ActionListener getAnhadirSupertecnicaButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarSupertecnica();
                recargarTablaSupertecnicas();
            }
        };
        return al;
    }
    
    private ActionListener getEliminarEquipoButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = view.getEquiposTableSelectedRow();
                if (selected != -1) {
                    eliminarEquipo(selected);
                    recargarTablaEquipos();
                } else {
                    JOptionPane.showMessageDialog(view, "Selecciona un equipo de la tabla para eliminar");
                }
            }
        };
        return al;
    }
    
    private ActionListener getEliminarSupertecnicaButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = view.getSupertecnicasTableSelectedRow();
                if (selected != -1) {
                    eliminarSupertecnica(selected);
                    recargarTablaSupertecnicas();
                } else {
                    JOptionPane.showMessageDialog(view, "Selecciona una supertecnica de la tabla para eliminar");
                }
            }
        };
        return al;
    }
    
    private ActionListener getConfirmarButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String icono = view.getIconoTextField();
                String nombre = view.getNombreTextField();
                String alias = view.getAliasTextField();
                String genero = view.getGenero();
                String posicion = view.getPosicion();
                String atributo = view.getAtributo();
                String descripcion = view.getDescripcionTextArea();
                System.out.println(icono);
                System.out.println(nombre);
                System.out.println(alias);
                System.out.println(genero);
                System.out.println(posicion);
                System.out.println(atributo);
                System.out.println(descripcion);
                int confirmacion = JOptionPane.showConfirmDialog(view, "Esta usted seguro?");
                System.out.println(confirmacion);
                if (confirmacion == 0){
                    if (id != -1) {
                    Personaje personaje = OperacionBD.getPersonaje(id);
                    personaje.setImage(icono);
                    personaje.setNombre(nombre);
                    personaje.setAlias(alias);
                    personaje.setGenero(genero);
                    personaje.setPosicion(posicion);
                    personaje.setAtributo(OperacionBD.getAtributo(atributo));
                    OperacionBD.modifyPersonaje(personaje);
                } else {
                OperacionBD.addPersonaje(icono,nombre,alias,genero,posicion,atributo,descripcion);
                }
                view.dispose();
                parentController.cargarDatos();
                }
            }
        };
        return al;
    }
    
}
