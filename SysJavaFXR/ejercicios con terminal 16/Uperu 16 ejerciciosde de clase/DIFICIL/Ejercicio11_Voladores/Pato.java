package DIFICIL.Ejercicio11_Voladores;

public class Pato extends AnimalInterfaz implements Volador, Nadador {
    public Pato(String nombre) {
        super(nombre);
    }

    @Override
    public void volar() {
        System.out.println(nombre + " estÃ¡ volando");
    }

    @Override
    public void nadar() {
        System.out.println(nombre + " estÃ¡ nadando");
    }
}
