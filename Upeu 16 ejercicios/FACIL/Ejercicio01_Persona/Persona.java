package FACIL.Ejercicio01_Persona;

import java.util.ArrayList;
import java.util.Scanner;

public class Persona {
    private String nombre;
    private int edad;
    private String correo;

    public Persona(String nombre, int edad, String correo) {
        this.nombre = nombre;
        setEdad(edad);
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad > 0) {
            this.edad = edad;
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void presentarse() {
        System.out.printf("Nombre: %s%nEdad: %d%nCorreo: %s%n", nombre, edad, correo);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Persona> personas = new ArrayList<>();
        boolean salir = false;

        System.out.println("=== SISTEMA INTERACTIVO DE PERSONAS ===");

        while (!salir) {
            System.out.println("\n1. Agregar nueva persona");
            System.out.println("2. Listar personas");
            System.out.println("0. Volver");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingresa la edad: ");
                    int edad = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingresa el correo: ");
                    String correo = scanner.nextLine();
                    
                    personas.add(new Persona(nombre, edad, correo));
                    System.out.println("¡Persona agregada exitosamente!");
                    break;
                case 2:
                    if (personas.isEmpty()) {
                        System.out.println("No hay personas registradas.");
                    } else {
                        System.out.println("\n--- Lista de Personas ---");
                        for (int i = 0; i < personas.size(); i++) {
                            System.out.println("Persona #" + (i + 1));
                            personas.get(i).presentarse();
                            System.out.println("-------------------------");
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
