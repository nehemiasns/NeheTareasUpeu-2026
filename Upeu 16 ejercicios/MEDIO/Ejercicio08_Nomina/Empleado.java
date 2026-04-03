package MEDIO.Ejercicio08_Nomina;

public abstract class Empleado {
    protected String nombre;
    protected String id;

    public Empleado(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }

    public abstract double calcularPago();

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }
}
