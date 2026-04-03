package MEDIO.Ejercicio09_Solidos;

import java.util.ArrayList;
import java.util.Scanner;

public class SolidosGeometricos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Solido3D> solidos = new ArrayList<>();
        boolean salir = false;

        System.out.println("=== CREADOR DE SÓLIDOS 3D ===");

        while (!salir) {
            System.out.println("\n1. Crear Esfera");
            System.out.println("2. Crear Cubo");
            System.out.println("3. Crear Cilindro");
            System.out.println("4. Ver todos los sólidos calculados");
            System.out.println("0. Volver");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion >= 1 && opcion <= 3) {
                System.out.print("Color del sólido: ");
                String color = scanner.nextLine();
                
                if (opcion == 1) {
                    System.out.print("Radio de la esfera: ");
                    double radio = scanner.nextDouble();
                    solidos.add(new Esfera(color, radio));
                } else if (opcion == 2) {
                    System.out.print("Lado/Arista del cubo: ");
                    double lado = scanner.nextDouble();
                    solidos.add(new Cubo(color, lado));
                } else if (opcion == 3) {
                    System.out.print("Radio base del cilindro: ");
                    double radio = scanner.nextDouble();
                    System.out.print("Altura del cilindro: ");
                    double altura = scanner.nextDouble();
                    solidos.add(new Cilindro(color, radio, altura));
                }
                
                System.out.println("¡Sólido agregado al sistema!");
            } else if (opcion == 4) {
                if (solidos.isEmpty()) {
                    System.out.println("No hay sólidos registrados.");
                } else {
                    System.out.println("\n=== Sólidos Geométricos ===");
                    for (Solido3D s : solidos) {
                        System.out.println(s);
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
