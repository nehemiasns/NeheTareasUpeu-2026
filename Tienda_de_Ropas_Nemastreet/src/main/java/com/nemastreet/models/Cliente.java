package com.nemastreet.models;

public class Cliente extends Persona {
    private String direccionEnvio;

    public Cliente(String id, String nombre, String email, String password, String direccionEnvio) {
        super(id, nombre, email, password, "Cliente");
        this.direccionEnvio = direccionEnvio;
    }

    public String getDireccionEnvio() { return direccionEnvio; }
    public void setDireccionEnvio(String direccionEnvio) { this.direccionEnvio = direccionEnvio; }
}
