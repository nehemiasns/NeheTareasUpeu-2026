package MEDIO.Ejercicio08_Nomina;

public class EmpleadoTemporal extends Empleado {
    private int horasTrabajadas;
    private double tarifaHora;

    public EmpleadoTemporal(String nombre, String id, int horasTrabajadas, double tarifaHora) {
        super(nombre, id);
        this.horasTrabajadas = horasTrabajadas;
        this.tarifaHora = tarifaHora;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public double getTarifaHora() {
        return tarifaHora;
    }

    @Override
    public double calcularPago() {
        return horasTrabajadas * tarifaHora;
    }

    @Override
    public String toString() {
        return "EmpleadoTemporal{" +
                "nombre='" + nombre + '\'' +
                ", id='" + id + '\'' +
                ", horas=" + horasTrabajadas +
                ", tarifa=" + tarifaHora +
                ", pago=" + calcularPago() +
                '}';
    }
}
