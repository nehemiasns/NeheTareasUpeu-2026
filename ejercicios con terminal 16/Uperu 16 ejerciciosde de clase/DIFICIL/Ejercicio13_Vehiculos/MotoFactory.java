package DIFICIL.Ejercicio13_Vehiculos;

public class MotoFactory extends VehiculoFactory {
    private String tipo;

    public MotoFactory(String marca, String tipo) {
        super(marca);
        this.tipo = tipo;
    }

    @Override
    public void describir() {
        System.out.println("Moto: " + marca + " tipo " + tipo);
    }
}
