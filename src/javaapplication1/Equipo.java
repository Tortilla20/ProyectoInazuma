/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author dam2_alu02@inf.ald
 */
class Equipo {
    private int id;
    private String nombre;
    private String region;
    private String escudo;
    private Personaje capitan;
    private Personaje entrenador;

    public Equipo(int id, String nombre, String region, String escudo) {
        this.id = id;
        this.nombre = nombre;
        this.region = region;
        this.escudo = escudo;
    }

    public String getEscudo() {
        return escudo;
    }

    public void setEscudo(String escudo) {
        this.escudo = escudo;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
    
}
