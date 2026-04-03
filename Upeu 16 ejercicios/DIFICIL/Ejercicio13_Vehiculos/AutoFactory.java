package DIFICIL.Ejercicio13_Vehiculos;

public class AutoFactory extends VehiculoFactory {
    private int numPuertas;

    public AutoFactory(String marca, int numPuertas) {
        super(marca);
        this.numPuertas = numPuertas;
    }

    @Override
    public void describir() {
        System.out.println("Auto: " + marca + " con " + numPuertas + " puertas");
    }
}
