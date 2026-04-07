package MEDIO.Ejercicio10_Ranking;

import java.util.ArrayList;
import java.util.Comparator;

public class Ranking {
    private ArrayList<Jugador> jugadores;

    public Ranking() {
        jugadores = new ArrayList<>();
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

    public static void main(String[] args) {
        Ranking ranking = new Ranking();

        ranking.agregar(new Jugador("Player1", 150));
        ranking.agregar(new Jugador("Player2", 200));
        ranking.agregar(new Jugador("Player3", 180));
        ranking.agregar(new Jugador("Player4", 220));
        ranking.agregar(new Jugador("Player5", 170));

        ranking.clasificar();

        System.out.println("=== Top 3 Jugadores ===");
        ranking.mostrarTop(3);
        System.out.println();
        System.out.println("Puntos Totales: " + ranking.puntosTotales());
    }
}
