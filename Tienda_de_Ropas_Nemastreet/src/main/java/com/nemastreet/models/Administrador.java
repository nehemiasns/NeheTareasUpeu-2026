package com.nemastreet.models;

public class Administrador extends Persona {
    private String nivelAcceso;

    public Administrador(String id, String nombre, String email, String password, String nivelAcceso) {
        super(id, nombre, email, password, "Admin");
        this.nivelAcceso = nivelAcceso;
    }

    public String getNivelAcceso() { return nivelAcceso; }
    public void setNivelAcceso(String nivelAcceso) { this.nivelAcceso = nivelAcceso; }
}
