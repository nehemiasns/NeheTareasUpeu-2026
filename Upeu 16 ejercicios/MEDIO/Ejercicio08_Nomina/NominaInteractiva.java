package MEDIO.Ejercicio08_Nomina;

import java.util.ArrayList;
import java.util.Scanner;

public class NominaInteractiva {
    private ArrayList<Empleado> empleados;
    private Scanner scanner;

    public NominaInteractiva() {
        empleados = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void agregar(Empleado e) {
        empleados.add(e);
    }

    public double calcularNominaTotal() {
        double total = 0;
        for (Empleado e : empleados) {
            total += e.calcularPago();
        }
        return total;
    }

    public void mostrarNomina() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados");
            return;
        }
        for (int i = 0; i < empleados.size(); i++) {
            System.out.println((i + 1) + ". " + empleados.get(i));
        }
    }

    public void menuInteractivo() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nâ•”â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•—");
            System.out.println("â•‘   NÃ“MINA DE EMPLEADOS           â•‘");
            System.out.println("â•šâ•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•");
            System.out.println("1. Agregar empleado fijo");
            System.out.println("2. Agregar empleado temporal");
            System.out.println("3. Ver nÃ³mina completa");
            System.out.println("4. Total de nÃ³mina");
            System.out.println("5. Cargar datos de prueba");
            System.out.println("0. Salir");
            System.out.print("Selecciona opciÃ³n: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Salario mensual: ");
                    double salario = scanner.nextDouble();
                    scanner.nextLine();
                    agregar(new EmpleadoFijo(nombre, id, salario));
                    System.out.println("âœ“ Empleado fijo agregado");
                    break;
                case 2:
                    System.out.print("Nombre: ");
                    String nombreT = scanner.nextLine();
                    System.out.print("ID: ");
                    String idT = scanner.nextLine();
                    System.out.print("Horas trabajadas: ");
                    int horas = scanner.nextInt();
                    System.out.print("Tarifa por hora: ");
                    double tarifa = scanner.nextDouble();
                    scanner.nextLine();
                    agregar(new EmpleadoTemporal(nombreT, idT, horas, tarifa));
                    System.out.println("âœ“ Empleado temporal agregado");
                    break;
                case 3:
                    System.out.println("\n--- NÃ³mina ---");
                    mostrarNomina();
                    break;
                case 4:
                    System.out.println("Total nÃ³mina: $" + calcularNominaTotal());
                    break;
                case 5:
                    agregar(new EmpleadoFijo("Juan", "EF001", 3000));
                    agregar(new EmpleadoFijo("MarÃ­a", "EF002", 3500));
                    agregar(new EmpleadoTemporal("Carlos", "ET001", 160, 15));
                    agregar(new EmpleadoTemporal("Ana", "ET002", 120, 12));
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
        NominaInteractiva nomina = new NominaInteractiva();
        nomina.menuInteractivo();
    }
}
