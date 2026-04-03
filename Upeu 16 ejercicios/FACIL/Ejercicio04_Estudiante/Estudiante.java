package FACIL.Ejercicio04_Estudiante;

import java.util.ArrayList;
import java.util.Scanner;

public class Estudiante {
    private String nombre;
    private String codigo;
    private double nota;

    public Estudiante(String nombre, String codigo, double nota) {
        this.nombre = nombre;
        this.codigo = codigo;
        setNota(nota);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        if (nota >= 0 && nota <= 10) {
            this.nota = nota;
        }
    }

    public String getCalificacion() {
        return nota >= 6.0 ? "Aprobado" : "Reprobado";
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                ", nota=" + nota +
                ", calificacion='" + getCalificacion() + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        boolean salir = false;

        System.out.println("=== GESTIÓN DE ESTUDIANTES ===");

        while (!salir) {
            System.out.println("\n1. Registrar nuevo estudiante");
            System.out.println("2. Ver todos los estudiantes");
            System.out.println("0. Volver");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del estudiante: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Código del estudiante: ");
                    String codigo = scanner.nextLine();
                    System.out.print("Nota del estudiante (0-10): ");
                    double nota = scanner.nextDouble();
                    
                    Estudiante est = new Estudiante(nombre, codigo, nota);
                    estudiantes.add(est);
                    System.out.println("¡Estudiante registrado! " + est.getCalificacion());
                    break;
                case 2:
                    if (estudiantes.isEmpty()) {
                        System.out.println("No hay estudiantes registrados.");
                    } else {
                        System.out.println("\n--- Lista de Estudiantes ---");
                        for (Estudiante e : estudiantes) {
                            System.out.println(e);
                        }
                    }
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
