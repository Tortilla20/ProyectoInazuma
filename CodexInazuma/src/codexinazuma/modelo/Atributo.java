/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codexinazuma.modelo;

/**
 *
 * @author dam2_alu02@inf.ald
 */
public class Atributo {
    private int id;
    private String nombre;
    private String imagen;

    public Atributo(int id, String nombre,String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        if (this.imagen.equals(null)) {
            return "";
        }
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Atributo{" + "id=" + id + ", nombre=" + nombre + ", imagen=" + imagen + '}';
    }

    
    
}
