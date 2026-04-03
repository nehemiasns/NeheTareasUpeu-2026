package DIFICIL.Ejercicio13_Vehiculos;

public abstract class VehiculoFactory {
    protected String marca;

    public VehiculoFactory(String marca) {
        this.marca = marca;
    }

    public abstract void describir();
}
