package DIFICIL.Ejercicio12_Reservas;

import java.util.ArrayList;
import java.util.Scanner;

public class ReservasInteractiva {
    private ArrayList<Reserva> reservas;
    private Scanner scanner;
    private int siguienteId;

    public ReservasInteractiva() {
        reservas = new ArrayList<>();
        scanner = new Scanner(System.in);
        siguienteId = 1;
    }

    public void agregar(Reserva r) {
        reservas.add(r);
    }

    public void confirmar(int id) {
        for (Reserva r : reservas) {
            if (r.getId() == id) {
                r.setEstado(EstadoReserva.CONFIRMADA);
                System.out.println("âœ“ Reserva " + id + " confirmada");
                return;
            }
        }
        System.out.println("âœ— Reserva no encontrada");
    }

    public void cancelar(int id) {
        for (Reserva r : reservas) {
            if (r.getId() == id) {
                r.setEstado(EstadoReserva.CANCELADA);
                System.out.println("âœ“ Reserva " + id + " cancelada");
                return;
            }
        }
        System.out.println("âœ— Reserva no encontrada");
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

    public void listarTodas() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas");
            return;
        }
        for (int i = 0; i < reservas.size(); i++) {
            System.out.println((i + 1) + ". " + reservas.get(i));
        }
    }

    public void menuInteractivo() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nâ•”â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•—");
            System.out.println("â•‘   CENTRAL DE RESERVAS           â•‘");
            System.out.println("â•šâ•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•");
            System.out.println("1. Nueva reserva");
            System.out.println("2. Ver todas las reservas");
            System.out.println("3. Confirmar reserva");
            System.out.println("4. Cancelar reserva");
            System.out.println("5. Buscar por cliente");
            System.out.println("6. Reservas del dÃ­a");
            System.out.println("7. Cargar datos de prueba");
            System.out.println("0. Salir");
            System.out.print("Selecciona opciÃ³n: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Cliente: ");
                    String cliente = scanner.nextLine();
                    System.out.print("Fecha (YYYY-MM-DD): ");
                    String fecha = scanner.nextLine();
                    System.out.print("HabitaciÃ³n: ");
                    String habitacion = scanner.nextLine();
                    agregar(new Reserva(siguienteId++, cliente, fecha, habitacion));
                    System.out.println("âœ“ Reserva creada con ID: " + (siguienteId - 1));
                    break;
                case 2:
                    System.out.println("\n--- Todas las Reservas ---");
                    listarTodas();
                    break;
                case 3:
                    System.out.print("ID de reserva a confirmar: ");
                    int idConfirmar = scanner.nextInt();
                    scanner.nextLine();
                    confirmar(idConfirmar);
                    break;
                case 4:
                    System.out.print("ID de reserva a cancelar: ");
                    int idCancelar = scanner.nextInt();
                    scanner.nextLine();
                    cancelar(idCancelar);
                    break;
                case 5:
                    System.out.print("Nombre del cliente: ");
                    String clienteBuscar = scanner.nextLine();
                    ArrayList<Reserva> porCliente = buscarPorCliente(clienteBuscar);
                    if (porCliente.isEmpty()) {
                        System.out.println("No hay reservas de ese cliente");
                    } else {
                        System.out.println("Reservas de " + clienteBuscar + ":");
                        for (Reserva r : porCliente) {
                            System.out.println("  " + r);
                        }
                    }
                    break;
                case 6:
                    System.out.print("Fecha (YYYY-MM-DD): ");
                    String fechaBuscar = scanner.nextLine();
                    ArrayList<Reserva> delDia = reservasDelDia(fechaBuscar);
                    if (delDia.isEmpty()) {
                        System.out.println("No hay reservas para ese dÃ­a");
                    } else {
                        System.out.println("Reservas del " + fechaBuscar + ":");
                        for (Reserva r : delDia) {
                            System.out.println("  " + r);
                        }
                    }
                    break;
                case 7:
                    agregar(new Reserva(siguienteId++, "Juan", "2024-01-15", "101"));
                    agregar(new Reserva(siguienteId++, "MarÃ­a", "2024-01-15", "102"));
                    agregar(new Reserva(siguienteId++, "Juan", "2024-01-16", "103"));
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
        ReservasInteractiva central = new ReservasInteractiva();
        central.menuInteractivo();
    }
}
