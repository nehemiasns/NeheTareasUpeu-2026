package MEDIO.Ejercicio09_Solidos;

public abstract class Solido3D {
    protected String color;

    public Solido3D(String color) {
        this.color = color;
    }

    public abstract double calcularVolumen();

    public abstract double calcularAreaSuperficial();

    public String getColor() {
        return color;
    }
}
