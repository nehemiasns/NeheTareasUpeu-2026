package FACIL.Ejercicio02_Automovil;

import java.util.Scanner;

public class Automovil {
    private String marca;
    private String modelo;
    private int anio;
    private int velocidadActual;

    public Automovil(String marca, String modelo, int anio) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.velocidadActual = 0;
    }

    public void acelerar(int km) {
        velocidadActual += km;
    }

    public void frenar(int km) {
        if (velocidadActual - km < 0) {
            velocidadActual = 0;
        } else {
            velocidadActual -= km;
        }
    }

    public void getEstado() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Anio: " + anio);
        System.out.println("Velocidad Actual: " + velocidadActual + " km/h");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== CONFIGURACIÓN DE TU AUTOMÓVIL ===");
        System.out.print("Ingresa la marca: ");
        String marca = scanner.nextLine();
        System.out.print("Ingresa el modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Ingresa el anio: ");
        int anio = scanner.nextInt();
        scanner.nextLine();

        Automovil auto = new Automovil(marca, modelo, anio);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ AUTOMÓVIL ---");
            System.out.println("1. Acelerar");
            System.out.println("2. Frenar");
            System.out.println("3. Ver Estado");
            System.out.println("0. Volver");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch(opcion) {
                case 1:
                    System.out.print("¿Cuántos km/h deseas acelerar?: ");
                    int kmAcelerar = scanner.nextInt();
                    auto.acelerar(kmAcelerar);
                    System.out.println("Aceleraste a " + auto.velocidadActual + " km/h");
                    break;
                case 2:
                    System.out.print("¿Cuántos km/h deseas frenar?: ");
                    int kmFrenar = scanner.nextInt();
                    auto.frenar(kmFrenar);
                    System.out.println("Bajaste la velocidad a " + auto.velocidadActual + " km/h");
                    break;
                case 3:
                    System.out.println("\n-- ESTADO DEL AUTO --");
                    auto.getEstado();
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
