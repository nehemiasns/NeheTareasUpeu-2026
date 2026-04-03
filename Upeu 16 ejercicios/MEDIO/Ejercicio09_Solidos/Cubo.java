package MEDIO.Ejercicio09_Solidos;

public class Cubo extends Solido3D {
    private double lado;

    public Cubo(String color, double lado) {
        super(color);
        this.lado = lado;
    }

    @Override
    public double calcularVolumen() {
        return Math.pow(lado, 3);
    }

    @Override
    public double calcularAreaSuperficial() {
        return 6 * Math.pow(lado, 2);
    }

    @Override
    public String toString() {
        return "Cubo{" +
                "color='" + color + '\'' +
                ", lado=" + lado +
                ", volumen=" + calcularVolumen() +
                ", Ã¡rea=" + calcularAreaSuperficial() +
                '}';
    }
}
