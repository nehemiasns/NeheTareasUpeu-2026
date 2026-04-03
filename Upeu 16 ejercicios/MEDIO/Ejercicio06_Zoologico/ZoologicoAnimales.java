package MEDIO.Ejercicio06_Zoologico;

import java.util.ArrayList;
import java.util.Scanner;

public class ZoologicoAnimales {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Animal> animales = new ArrayList<>();
        boolean salir = false;

        System.out.println("=== ZOOLÓGICO INTERACTIVO ===");

        while (!salir) {
            System.out.println("\n1. Agregar Perro");
            System.out.println("2. Agregar Gato");
            System.out.println("3. Agregar Vaca");
            System.out.println("4. Escuchar a todos los animales (Polimorfismo)");
            System.out.println("0. Volver");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion >= 1 && opcion <= 3) {
                System.out.print("Nombre del animal: ");
                String nombre = scanner.nextLine();
                System.out.print("Edad del animal: ");
                int edad = scanner.nextInt();
                scanner.nextLine();

                if (opcion == 1) animales.add(new Perro(nombre, edad));
                else if (opcion == 2) animales.add(new Gato(nombre, edad));
                else if (opcion == 3) animales.add(new Vaca(nombre, edad));

                System.out.println("¡Animal agregado al zoológico!");
            } else if (opcion == 4) {
                if (animales.isEmpty()) {
                    System.out.println("El zoológico está vacío.");
                } else {
                    System.out.println("\n=== Sonidos de Animales ===");
                    for (Animal a : animales) {
                        a.hacerSonido();
                        a.comer();
                        System.out.println("-------------------------");
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
