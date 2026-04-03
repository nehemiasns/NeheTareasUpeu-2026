package MEDIO.Ejercicio08_Nomina;

public class EmpleadoFijo extends Empleado {
    private double salarioMensual;

    public EmpleadoFijo(String nombre, String id, double salarioMensual) {
        super(nombre, id);
        this.salarioMensual = salarioMensual;
    }

    public double getSalarioMensual() {
        return salarioMensual;
    }

    @Override
    public double calcularPago() {
        return salarioMensual;
    }

    @Override
    public String toString() {
        return "EmpleadoFijo{" +
                "nombre='" + nombre + '\'' +
                ", id='" + id + '\'' +
                ", salario=" + salarioMensual +
                ", pago=" + calcularPago() +
                '}';
    }
}
