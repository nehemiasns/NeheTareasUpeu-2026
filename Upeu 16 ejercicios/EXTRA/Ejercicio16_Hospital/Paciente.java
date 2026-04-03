package EXTRA.Ejercicio16_Hospital;

import java.util.ArrayList;

public class Paciente extends Persona {
    private ArrayList<String> historialClinico;
    private String enfermedad;

    public Paciente(String nombre, String cedula, String enfermedad) {
        super(nombre, cedula);
        this.enfermedad = enfermedad;
        this.historialClinico = new ArrayList<>();
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public ArrayList<String> getHistorialClinico() {
        return historialClinico;
    }

    public void agregarRegistroHistorial(String registro) {
        historialClinico.add(registro);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", enfermedad='" + enfermedad + '\'' +
                ", historial=" + historialClinico +
                '}';
    }
}
