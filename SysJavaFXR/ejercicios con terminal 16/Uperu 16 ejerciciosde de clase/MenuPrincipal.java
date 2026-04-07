import java.util.Scanner;

import FACIL.Ejercicio01_Persona.Persona;
import FACIL.Ejercicio02_Automovil.Automovil;
import FACIL.Ejercicio03_Rectangulo.Rectangulo;
import FACIL.Ejercicio04_Estudiante.Estudiante;
import FACIL.Ejercicio05_Tienda.TiendaInteractiva;

import MEDIO.Ejercicio06_Zoologico.ZoologicoAnimales;
import MEDIO.Ejercicio07_Biblioteca.BibliotecaInteractiva;
import MEDIO.Ejercicio08_Nomina.NominaInteractiva;
import MEDIO.Ejercicio09_Solidos.SolidosGeometricos;
import MEDIO.Ejercicio10_Ranking.RankingInteractivo;

import DIFICIL.Ejercicio11_Voladores.EjemploVoladores;
import DIFICIL.Ejercicio12_Reservas.ReservasInteractiva;
import DIFICIL.Ejercicio13_Vehiculos.VehiculosInteractiva;
import DIFICIL.Ejercicio14_Sistema.Sistema;
import DIFICIL.Ejercicio15_Excepciones.ListaLimitada;

import EXTRA.Ejercicio16_Hospital.HospitalInteractivo;

public class MenuPrincipal {

    static class OpcionesMenu {
        static final int FACIL = 1;
        static final int MEDIO = 2;
        static final int DIFICIL = 3;
        static final int EXTRA = 4;
        static final int SALIR = 5;
    }

    public static void menuFacil(Scanner scanner) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== NIVEL FÁCIL ===");
            System.out.println("1. Persona (Clase con Getters/Setters)");
            System.out.println("2. Automóvil (Métodos de Velocidad)");
            System.out.println("3. Rectángulo (Cálculos Geométricos)");
            System.out.println("4. Estudiante (Validación de Notas)");
            System.out.println("5. Tienda (ArrayList de Productos)");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Ejecutando Persona ---");
                    Persona.main(new String[]{});
                    break;
                case 2:
                    System.out.println("\n--- Ejecutando Automóvil ---");
                    Automovil.main(new String[]{});
                    break;
                case 3:
                    System.out.println("\n--- Ejecutando Rectángulo ---");
                    Rectangulo.main(new String[]{});
                    break;
                case 4:
                    System.out.println("\n--- Ejecutando Estudiante ---");
                    Estudiante.main(new String[]{});
                    break;
                case 5:
                    System.out.println("\n--- Ejecutando Tienda Interactiva ---");
                    TiendaInteractiva.main(new String[]{});
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    public static void menuMedio(Scanner scanner) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== NIVEL MEDIO ===");
            System.out.println("1. Biblioteca (Herencia y ArrayList)");
            System.out.println("2. Empleados Fijo/Temporal (Clases Abstractas)");
            System.out.println("3. Ranking con Ordenamiento (Comparator)");
            System.out.println("4. Animales con Polimorfismo (Sobrescritura)");
            System.out.println("5. Sólidos 3D (Figuras Geométricas)");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Ejecutando Biblioteca Interactiva ---");
                    BibliotecaInteractiva.main(new String[]{});
                    break;
                case 2:
                    System.out.println("\n--- Ejecutando Nómina Interactiva ---");
                    NominaInteractiva.main(new String[]{});
                    break;
                case 3:
                    System.out.println("\n--- Ejecutando Ranking Interactivo ---");
                    RankingInteractivo.main(new String[]{});
                    break;
                case 4:
                    System.out.println("\n--- Ejecutando Zoológico ---");
                    ZoologicoAnimales.main(new String[]{});
                    break;
                case 5:
                    System.out.println("\n--- Ejecutando Sólidos Geométricos ---");
                    SolidosGeometricos.main(new String[]{});
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    public static void menuDificil(Scanner scanner) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== NIVEL DIFÍCIL ===");
            System.out.println("1. Lista con Capacidad Máxima (Excepciones)");
            System.out.println("2. Sistema de Reservas (Enums)");
            System.out.println("3. Interfaces Múltiples (Volador/Nadador)");
            System.out.println("4. Vehículos con Factory Pattern");
            System.out.println("5. Sistema con Clase Interna");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Ejecutando Lista Limitada ---");
                    ListaLimitada.main(new String[]{});
                    break;
                case 2:
                    System.out.println("\n--- Ejecutando Sistema Reservas Interactivo ---");
                    ReservasInteractiva.main(new String[]{});
                    break;
                case 3:
                    System.out.println("\n--- Ejecutando Ejemplo Voladores ---");
                    EjemploVoladores.main(new String[]{});
                    break;
                case 4:
                    System.out.println("\n--- Ejecutando Vehículos Interactivo ---");
                    VehiculosInteractiva.main(new String[]{});
                    break;
                case 5:
                    System.out.println("\n--- Ejecutando Sistema con Clase Interna ---");
                    Sistema.main(new String[]{});
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    public static void menuExtra(Scanner scanner) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== NIVEL EXTRA (MÁXIMA DIFICULTAD) ===");
            System.out.println("1. Mini Sistema Hospitalario (Jerarquía Completa)");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Ejecutando Sistema Hospitalario Interactivo ---");
                    HospitalInteractivo.main(new String[]{});
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    public static void mostrarMenuPrincipal() {
        System.out.println("\n" +
                "╔════════════════════════════════════════════════════════════╗\n" +
                "║    EJERCICIOS DE PROGRAMACIÓN ORIENTADA A OBJETOS EN JAVA  ║\n" +
                "║                        30 EJERCICIOS                        ║\n" +
                "╚════════════════════════════════════════════════════════════╝");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            mostrarMenuPrincipal();

            System.out.println("\n1. Nivel FÁCIL (5 ejercicios)");
            System.out.println("2. Nivel MEDIO (5 ejercicios)");
            System.out.println("3. Nivel DIFÍCIL (5 ejercicios)");
            System.out.println("4. Nivel EXTRA (Máxima dificultad)");
            System.out.println("5. Salir");
            System.out.print("\nSelecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case OpcionesMenu.FACIL:
                    menuFacil(scanner);
                    break;
                case OpcionesMenu.MEDIO:
                    menuMedio(scanner);
                    break;
                case OpcionesMenu.DIFICIL:
                    menuDificil(scanner);
                    break;
                case OpcionesMenu.EXTRA:
                    menuExtra(scanner);
                    break;
                case OpcionesMenu.SALIR:
                    salir = true;
                    System.out.println("\n¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }

        scanner.close();
    }
}
