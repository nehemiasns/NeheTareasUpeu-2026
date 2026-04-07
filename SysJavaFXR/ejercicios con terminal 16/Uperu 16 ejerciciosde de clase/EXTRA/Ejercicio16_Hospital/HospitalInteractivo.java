package EXTRA.Ejercicio16_Hospital;

import java.util.ArrayList;
import java.util.Scanner;

public class HospitalInteractivo {
    private ArrayList<Doctor> doctores;
    private ArrayList<Paciente> pacientes;
    private Scanner scanner;

    public HospitalInteractivo() {
        doctores = new ArrayList<>();
        pacientes = new ArrayList<>();
        scanner = new Scanner(System.in);
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

    public Doctor buscarDoctor(String nombre) {
        for (Doctor d : doctores) {
            if (d.getNombre().equalsIgnoreCase(nombre)) {
                return d;
            }
        }
        return null;
    }

    public void reporteGeneral() {
        System.out.println("\n=============================================");
        System.out.println("||   REPORTE GENERAL DEL HOSPITAL          ||");
        System.out.println("=============================================\n");
        
        if (doctores.isEmpty()) {
            System.out.println("No hay doctores registrados");
            return;
        }

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

    public void menuInteractivo() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=============================================");
            System.out.println("||   SISTEMA DEL HOSPITAL                  ||");
            System.out.println("=============================================");
            System.out.println("1. Registrar nuevo doctor");
            System.out.println("2. Registrar nuevo paciente");
            System.out.println("3. Asignar paciente a doctor");
            System.out.println("4. Ver historial de paciente");
            System.out.println("5. Reporte general");
            System.out.println("6. Cargar datos de prueba");
            System.out.println("0. Salir");
            System.out.print("Selecciona opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del doctor: ");
                    String nombreDoc = scanner.nextLine();
                    System.out.print("Cédula: ");
                    String cedulaDoc = scanner.nextLine();
                    System.out.print("Especialidad: ");
                    String especialidad = scanner.nextLine();
                    agregarDoctor(new Doctor(nombreDoc, cedulaDoc, especialidad));
                    System.out.println("[✓] Doctor registrado");
                    break;

                case 2:
                    System.out.print("Nombre del paciente: ");
                    String nombrePac = scanner.nextLine();
                    System.out.print("Cédula: ");
                    String cedulaPac = scanner.nextLine();
                    System.out.print("Enfermedad/Diagnóstico: ");
                    String enfermedad = scanner.nextLine();
                    agregarPaciente(new Paciente(nombrePac, cedulaPac, enfermedad));
                    System.out.println("[✓] Paciente registrado");
                    break;

                case 3:
                    System.out.print("Nombre del doctor: ");
                    String nomDoc = scanner.nextLine();
                    Doctor doctor = buscarDoctor(nomDoc);
                    
                    if (doctor == null) {
                        System.out.println("[x] Doctor no encontrado");
                        break;
                    }

                    System.out.print("Nombre del paciente: ");
                    String nomPac = scanner.nextLine();
                    Paciente paciente = buscarPaciente(nomPac);
                    
                    if (paciente == null) {
                        System.out.println("[x] Paciente no encontrado");
                        break;
                    }

                    asignarPaciente(doctor, paciente);
                    System.out.println("[✓] Paciente asignado al doctor");
                    break;

                case 4:
                    System.out.print("Nombre del paciente: ");
                    String nomPaciente = scanner.nextLine();
                    Paciente p = buscarPaciente(nomPaciente);
                    
                    if (p == null) {
                        System.out.println("[x] Paciente no encontrado");
                    } else {
                        System.out.println("\n--- Historial de " + p.getNombre() + " ---");
                        System.out.println("Cédula: " + p.getCedula());
                        System.out.println("Enfermedad: " + p.getEnfermedad());
                        System.out.println("Historial Clínico:");
                        ArrayList<String> historial = p.getHistorialClinico();
                        if (historial.isEmpty()) {
                            System.out.println("  Sin registros");
                        } else {
                            for (int i = 0; i < historial.size(); i++) {
                                System.out.println("  " + (i + 1) + ". " + historial.get(i));
                            }
                        }
                    }
                    break;

                case 5:
                    reporteGeneral();
                    break;

                case 6:
                    Doctor dr1 = new Doctor("Dr. Rodriguez", "12345", "Cardiologia");
                    Doctor dr2 = new Doctor("Dra. Garcia", "12346", "Pediatria");
                    
                    Paciente p1 = new Paciente("Juan Perez", "98765", "Hipertension");
                    Paciente p2 = new Paciente("Maria Lopez", "98766", "Diabetes");
                    Paciente p3 = new Paciente("Carlos Ruiz", "98767", "Resfriado");
                    
                    agregarDoctor(dr1);
                    agregarDoctor(dr2);
                    agregarPaciente(p1);
                    agregarPaciente(p2);
                    agregarPaciente(p3);
                    
                    asignarPaciente(dr1, p1);
                    asignarPaciente(dr1, p2);
                    asignarPaciente(dr2, p3);
                    
                    System.out.println("[✓] Datos de prueba cargados");
                    break;

                case 0:
                    salir = true;
                    System.out.println("\n¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    public static void main(String[] args) {
        HospitalInteractivo hospital = new HospitalInteractivo();
        hospital.menuInteractivo();
    }
}
