package MEDIO.Ejercicio09_Solidos;

public class Cilindro extends Solido3D {
    private double radio;
    private double altura;

    public Cilindro(String color, double radio, double altura) {
        super(color);
        this.radio = radio;
        this.altura = altura;
    }

    @Override
    public double calcularVolumen() {
        return Math.PI * Math.pow(radio, 2) * altura;
    }

    @Override
    public double calcularAreaSuperficial() {
        return 2 * Math.PI * radio * (radio + altura);
    }

    @Override
    public String toString() {
        return "Cilindro{" +
                "color='" + color + '\'' +
                ", radio=" + radio +
                ", altura=" + altura +
                ", volumen=" + calcularVolumen() +
                ", Ã¡rea=" + calcularAreaSuperficial() +
                '}';
    }
}
