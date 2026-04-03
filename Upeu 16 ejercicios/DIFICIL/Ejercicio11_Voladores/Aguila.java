package DIFICIL.Ejercicio11_Voladores;

public class Aguila extends AnimalInterfaz implements Volador {
    public Aguila(String nombre) {
        super(nombre);
    }

    @Override
    public void volar() {
        System.out.println(nombre + " estÃ¡ volando en el cielo");
    }
}
