package MEDIO.Ejercicio08_Nomina;

import java.util.ArrayList;

public class NominaEmpleados {
    private ArrayList<Empleado> empleados;

    public NominaEmpleados() {
        empleados = new ArrayList<>();
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
        for (Empleado e : empleados) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        NominaEmpleados nomina = new NominaEmpleados();

        EmpleadoFijo fijo1 = new EmpleadoFijo("Juan", "EF001", 3000);
        EmpleadoFijo fijo2 = new EmpleadoFijo("MarÃ­a", "EF002", 3500);
        EmpleadoTemporal temporal1 = new EmpleadoTemporal("Carlos", "ET001", 160, 15);
        EmpleadoTemporal temporal2 = new EmpleadoTemporal("Ana", "ET002", 120, 12);

        nomina.agregar(fijo1);
        nomina.agregar(fijo2);
        nomina.agregar(temporal1);
        nomina.agregar(temporal2);

        System.out.println("=== NÃ³mina ===");
        nomina.mostrarNomina();
        System.out.println();
        System.out.println("Total NÃ³mina: $" + nomina.calcularNominaTotal());
    }
}
