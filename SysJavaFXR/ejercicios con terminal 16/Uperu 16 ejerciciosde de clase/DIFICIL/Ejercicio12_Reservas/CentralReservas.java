package DIFICIL.Ejercicio12_Reservas;

import java.util.ArrayList;

public class CentralReservas {
    private ArrayList<Reserva> reservas;

    public CentralReservas() {
        reservas = new ArrayList<>();
    }

    public void agregar(Reserva r) {
        reservas.add(r);
    }

    public void confirmar(int id) {
        for (Reserva r : reservas) {
            if (r.getId() == id) {
                r.setEstado(EstadoReserva.CONFIRMADA);
                System.out.println("Reserva " + id + " confirmada");
                return;
            }
        }
        System.out.println("Reserva no encontrada");
    }

    public void cancelar(int id) {
        for (Reserva r : reservas) {
            if (r.getId() == id) {
                r.setEstado(EstadoReserva.CANCELADA);
                System.out.println("Reserva " + id + " cancelada");
                return;
            }
        }
        System.out.println("Reserva no encontrada");
    }

    public ArrayList<Reserva> buscarPorCliente(String cliente) {
        ArrayList<Reserva> resultado = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getCliente().equalsIgnoreCase(cliente)) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    public ArrayList<Reserva> reservasDelDia(String fecha) {
        ArrayList<Reserva> resultado = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getFecha().equals(fecha)) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    public static void main(String[] args) {
        CentralReservas central = new CentralReservas();

        central.agregar(new Reserva(1, "Juan", "2024-01-15", "101"));
        central.agregar(new Reserva(2, "MarÃ­a", "2024-01-15", "102"));
        central.agregar(new Reserva(3, "Juan", "2024-01-16", "103"));

        System.out.println("=== Reservas del 2024-01-15 ===");
        for (Reserva r : central.reservasDelDia("2024-01-15")) {
            System.out.println(r);
        }
        System.out.println();

        central.confirmar(1);
        System.out.println();

        System.out.println("=== Reservas de Juan ===");
        for (Reserva r : central.buscarPorCliente("Juan")) {
            System.out.println(r);
        }
        System.out.println();

        central.cancelar(2);
    }
}
