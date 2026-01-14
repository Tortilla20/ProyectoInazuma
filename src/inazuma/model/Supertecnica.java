/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inazuma.model;

/**
 *
 * @author dam2_alu02@inf.ald
 */
class Supertecnica {
    private int id;
    private String nombre;
    private int potencia;
    private boolean coordinada;
    private Atributo atributo;
    private String tipo;

    public Supertecnica(int id, String nombre, int potencia, boolean coordinada, Atributo atributo, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.potencia = potencia;
        this.coordinada = coordinada;
        this.atributo = atributo;
        this.tipo = tipo;
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

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public boolean isCoordinada() {
        return coordinada;
    }

    public void setCoordinada(boolean coordinada) {
        this.coordinada = coordinada;
    }

    public Atributo getAtributo() {
        return atributo;
    }

    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
}
