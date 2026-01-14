/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inazuma.model;

import inazuma.model.Supertecnica;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dam2_alu02@inf.ald
 */
public class Personaje {
    private int id;
    private String nombre;
    private String alias;
    private Supertecnica tecnicaCaracteristica;
    private String description;
    private String posicion;
    private List<Supertecnica> listaSupertecnicas;
    private List<Equipo> listaEquipos;
    private String genero;
    private String atributo;
    private String image;

    public Personaje(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.listaEquipos = new ArrayList<>();
        this.listaSupertecnicas = new ArrayList<>();
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Supertecnica getTecnicaCaracteristica() {
        return tecnicaCaracteristica;
    }

    public void setTecnicaCaracteristica(Supertecnica tecnicaCaracteristica) {
        this.tecnicaCaracteristica = tecnicaCaracteristica;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public List<Supertecnica> getListaSupertecnicas() {
        return listaSupertecnicas;
    }

    public void setListaSupertecnicas(List<Supertecnica> listaSupertecnicas) {
        this.listaSupertecnicas = listaSupertecnicas;
    }
    public void addSupertecnica(Supertecnica supertecnica){
        this.listaSupertecnicas.add(supertecnica);
    }

    public List<Equipo> getListaEquipos() {
        return listaEquipos;
    }

    public void setListaEquipos(List<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }
    public void addEquipo(Equipo equipo){
        this.listaEquipos.add(equipo);
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    
    
}