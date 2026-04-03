package MEDIO.Ejercicio09_Solidos;

public class Esfera extends Solido3D {
    private double radio;

    public Esfera(String color, double radio) {
        super(color);
        this.radio = radio;
    }

    @Override
    public double calcularVolumen() {
        return (4.0 / 3) * Math.PI * Math.pow(radio, 3);
    }

    @Override
    public double calcularAreaSuperficial() {
        return 4 * Math.PI * Math.pow(radio, 2);
    }

    @Override
    public String toString() {
        return "Esfera{" +
                "color='" + color + '\'' +
                ", radio=" + radio +
                ", volumen=" + calcularVolumen() +
                ", Ã¡rea=" + calcularAreaSuperficial() +
                '}';
    }
}
