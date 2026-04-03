package EXTRA.Ejercicio16_Hospital;

import java.util.ArrayList;

public class Hospital {
    private ArrayList<Doctor> doctores;
    private ArrayList<Paciente> pacientes;

    public Hospital() {
        doctores = new ArrayList<>();
        pacientes = new ArrayList<>();
    }

    public void agregarDoctor(Doctor d) {
        doctores.add(d);
    }

    public void agregarPaciente(Paciente p) {
        pacientes.add(p);
    }

    public void asignarPaciente(Doctor doctor, Paciente paciente) {
        doctor.agregarPaciente(paciente);
        paciente.agregarRegistroHistorial("Asignado al Dr. " + doctor.getNombre() + " - " + doctor.getEspecialidad());
    }

    public Paciente buscarPaciente(String nombre) {
        for (Paciente p : pacientes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    public void reporteGeneral() {
        System.out.println("=== REPORTE GENERAL DEL HOSPITAL ===\n");
        
        for (Doctor d : doctores) {
            System.out.println("Doctor: " + d.getNombre());
            System.out.println("Especialidad: " + d.getEspecialidad());
            System.out.println("Cantidad de Pacientes: " + d.cantidadPacientes());
            
            if (d.cantidadPacientes() > 0) {
                System.out.println("Pacientes:");
                for (Paciente p : d.getPacientesACargo()) {
                    System.out.println("  - " + p.getNombre() + " (" + p.getEnfermedad() + ")");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Hospital hospital = new Hospital();

        Doctor dr1 = new Doctor("Dr. Rodriguez", "12345", "CardiologÃ­a");
        Doctor dr2 = new Doctor("Dra. GarcÃ­a", "12346", "PediatrÃ­a");

        Paciente p1 = new Paciente("Juan PÃ©rez", "98765", "HipertensiÃ³n");
        Paciente p2 = new Paciente("MarÃ­a LÃ³pez", "98766", "Diabetes");
        Paciente p3 = new Paciente("Carlos Ruiz", "98767", "Resfriado");

        hospital.agregarDoctor(dr1);
        hospital.agregarDoctor(dr2);

        hospital.agregarPaciente(p1);
        hospital.agregarPaciente(p2);
        hospital.agregarPaciente(p3);

        hospital.asignarPaciente(dr1, p1);
        hospital.asignarPaciente(dr1, p2);
        hospital.asignarPaciente(dr2, p3);

        hospital.reporteGeneral();

        System.out.println("=== BÃºsqueda de Paciente ===");
        Paciente encontrado = hospital.buscarPaciente("Juan PÃ©rez");
        if (encontrado != null) {
            System.out.println(encontrado);
        }
    }
}
