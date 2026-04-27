package DIFICIL.Ejercicio13_Vehiculos;

public class CamionFactory extends VehiculoFactory {
    private int toneladas;

    public CamionFactory(String marca, int toneladas) {
        super(marca);
        this.toneladas = toneladas;
    }

    @Override
    public void describir() {
        System.out.println("CamiÃ³n: " + marca + " con capacidad de " + toneladas + " toneladas");
    }
}
