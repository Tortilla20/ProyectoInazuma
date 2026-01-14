/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inazuma.model;

/**
 *
 * @author dam2_alu02@inf.ald
 */
class Atributo {
    private int id;
    private String nombre;
    private Atributo ventaja;
    private Atributo desventaja;

    public Atributo(int id, String nombre, Atributo ventaja, Atributo desventaja) {
        this.id = id;
        this.nombre = nombre;
        this.ventaja = ventaja;
        this.desventaja = desventaja;
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

    public Atributo getVentaja() {
        return ventaja;
    }

    public void setVentaja(Atributo ventaja) {
        this.ventaja = ventaja;
    }

    public Atributo getDesventaja() {
        return desventaja;
    }

    public void setDesventaja(Atributo desventaja) {
        this.desventaja = desventaja;
    }
    
    
}
