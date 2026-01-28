/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inazuma.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dam2_alu02@inf.ald
 */
public class Equipo {
    private int id;
    private String nombre;
    private String region;
    private String escudo;
    private Personaje capitan;
    private Personaje entrenador;
    private List<Personaje> plantilla;

    public Equipo(int id, String nombre, String region, String escudo, Personaje capitan, Personaje entrenador) {
        this.id = id;
        this.nombre = nombre;
        this.region = region;
        this.escudo = escudo;
        this.capitan = capitan;
        this.entrenador = entrenador;
        this.plantilla = new ArrayList<>();
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

    public String getEscudo() {
        return escudo;
    }

    public void setEscudo(String escudo) {
        this.escudo = escudo;
    }

    public Personaje getCapitan() {
        return capitan;
    }

    public void setCapitan(Personaje capitan) {
        this.capitan = capitan;
    }

    public Personaje getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Personaje entrenador) {
        this.entrenador = entrenador;
    }

    public List<Personaje> getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(List<Personaje> plantilla) {
        this.plantilla = plantilla;
    }
    
    
    
    
    
    
    
    
}
