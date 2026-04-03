package FACIL.Ejercicio03_Rectangulo;

import java.util.ArrayList;
import java.util.Scanner;

public class Rectangulo {
    private double base;
    private double altura;

    public Rectangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    public double calcularArea() {
        return base * altura;
    }

    public double calcularPerimetro() {
        return 2 * (base + altura);
    }

    public boolean esCuadrado() {
        return base == altura;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Rectangulo> rectangulos = new ArrayList<>();
        boolean salir = false;

        System.out.println("=== SISTEMA DE RECTÁNGULOS ===");

        while (!salir) {
            System.out.println("\n1. Crear y evaluar nuevo rectángulo");
            System.out.println("2. Ver historial de rectángulos");
            System.out.println("0. Volver");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa la base: ");
                    double base = scanner.nextDouble();
                    System.out.print("Ingresa la altura: ");
                    double altura = scanner.nextDouble();
                    
                    Rectangulo r = new Rectangulo(base, altura);
                    rectangulos.add(r);
                    System.out.println("\n--- Resultados ---");
                    System.out.println("Area: " + r.calcularArea());
                    System.out.println("Perimetro: " + r.calcularPerimetro());
                    System.out.println("¿Es cuadrado?: " + (r.esCuadrado() ? "Si" : "No"));
                    break;
                case 2:
                    if (rectangulos.isEmpty()) {
                        System.out.println("No hay rectángulos registrados.");
                    } else {
                        System.out.println("\n--- Historial de Rectángulos ---");
                        for (int i = 0; i < rectangulos.size(); i++) {
                            Rectangulo rec = rectangulos.get(i);
                            System.out.println("Rectángulo #" + (i + 1) + " (Base: " + rec.base + ", Altura: " + rec.altura + ")");
                            System.out.println("  Area: " + rec.calcularArea());
                            System.out.println("  Perimetro: " + rec.calcularPerimetro());
                            System.out.println("  ¿Es cuadrado?: " + (rec.esCuadrado() ? "Si" : "No"));
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
