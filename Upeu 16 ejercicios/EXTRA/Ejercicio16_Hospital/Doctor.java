package EXTRA.Ejercicio16_Hospital;

import java.util.ArrayList;

public class Doctor extends Persona {
    private String especialidad;
    private ArrayList<Paciente> pacientesACargo;

    public Doctor(String nombre, String cedula, String especialidad) {
        super(nombre, cedula);
        this.especialidad = especialidad;
        this.pacientesACargo = new ArrayList<>();
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public ArrayList<Paciente> getPacientesACargo() {
        return pacientesACargo;
    }

    public void agregarPaciente(Paciente p) {
        pacientesACargo.add(p);
    }

    public int cantidadPacientes() {
        return pacientesACargo.size();
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", pacientes=" + cantidadPacientes() +
                '}';
    }
}
