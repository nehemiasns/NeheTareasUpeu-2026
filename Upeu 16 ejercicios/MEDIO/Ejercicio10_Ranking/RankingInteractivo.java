package MEDIO.Ejercicio10_Ranking;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class RankingInteractivo {
    private ArrayList<Jugador> jugadores;
    private Scanner scanner;

    public RankingInteractivo() {
        jugadores = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void agregar(Jugador j) {
        jugadores.add(j);
    }

    public void clasificar() {
        jugadores.sort(Comparator.comparingInt(Jugador::getPuntos).reversed());
    }

    public void mostrarTop(int n) {
        int limit = Math.min(n, jugadores.size());
        for (int i = 0; i < limit; i++) {
            System.out.println((i + 1) + ". " + jugadores.get(i));
        }
    }

    public int puntosTotales() {
        int total = 0;
        for (Jugador j : jugadores) {
            total += j.getPuntos();
        }
        return total;
    }

    public void menuInteractivo() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nâ•”â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•—");
            System.out.println("â•‘   RANKING DE JUGADORES          â•‘");
            System.out.println("â•šâ•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•");
            System.out.println("1. Agregar jugador");
            System.out.println("2. Ver ranking completo");
            System.out.println("3. Ver top N jugadores");
            System.out.println("4. Puntos totales");
            System.out.println("5. Cargar datos de prueba");
            System.out.println("0. Salir");
            System.out.print("Selecciona opciÃ³n: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del jugador: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Puntos: ");
                    int puntos = scanner.nextInt();
                    scanner.nextLine();
                    agregar(new Jugador(nombre, puntos));
                    System.out.println("âœ“ Jugador agregado");
                    break;
                case 2:
                    clasificar();
                    System.out.println("\n--- Ranking Completo ---");
                    if (jugadores.isEmpty()) {
                        System.out.println("No hay jugadores");
                    } else {
                        for (int i = 0; i < jugadores.size(); i++) {
                            System.out.println((i + 1) + ". " + jugadores.get(i));
                        }
                    }
                    break;
                case 3:
                    System.out.print("Â¿CuÃ¡ntos jugadores quieres ver?: ");
                    int n = scanner.nextInt();
                    scanner.nextLine();
                    clasificar();
                    System.out.println("\n--- Top " + n + " ---");
                    mostrarTop(n);
                    break;
                case 4:
                    System.out.println("Puntos totales: " + puntosTotales());
                    break;
                case 5:
                    agregar(new Jugador("Player1", 150));
                    agregar(new Jugador("Player2", 200));
                    agregar(new Jugador("Player3", 180));
                    agregar(new Jugador("Player4", 220));
                    agregar(new Jugador("Player5", 170));
                    System.out.println("âœ“ Datos de prueba cargados");
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("OpciÃ³n invÃ¡lida");
            }
        }
    }

    public static void main(String[] args) {
        RankingInteractivo ranking = new RankingInteractivo();
        ranking.menuInteractivo();
    }
}
