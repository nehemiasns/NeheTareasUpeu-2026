package DIFICIL.Ejercicio11_Voladores;

import java.util.ArrayList;
import java.util.Scanner;

public class EjemploVoladores {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Volador> voladores = new ArrayList<>();
        boolean salir = false;

        System.out.println("=== SISTEMA DE INTERFACES: VOLADORES ===");

        while (!salir) {
            System.out.println("\n1. Instanciar un Pato");
            System.out.println("2. Instanciar un Águila");
            System.out.println("3. Demostración de interfaces múltiples");
            System.out.println("0. Volver");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                System.out.print("Nombre del Pato: ");
                String nombre = scanner.nextLine();
                voladores.add(new Pato(nombre));
                System.out.println("¡Pato registrado!");
            } else if (opcion == 2) {
                System.out.print("Nombre del Águila: ");
                String nombre = scanner.nextLine();
                voladores.add(new Aguila(nombre));
                System.out.println("¡Águila registrada!");
            } else if (opcion == 3) {
                if (voladores.isEmpty()) {
                    System.out.println("No hay voladores registrados.");
                } else {
                    for (Volador v : voladores) {
                        System.out.println("\n--- Objeto: " + v.getClass().getSimpleName() + " ---");
                        v.volar();
                        
                        if (v instanceof Pato) {
                            System.out.println("Este objeto también implementa Nadador y AnimalInterfaz:");
                            ((Pato) v).nadar();
                            ((Pato) v).comer();
                        } else if (v instanceof Aguila) {
                            System.out.println("Este objeto también implementa AnimalInterfaz:");
                            ((Aguila) v).comer();
                        }
                    }
                }
            } else if (opcion == 0) {
                salir = true;
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }
}
