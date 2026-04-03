package MEDIO.Ejercicio06_Zoologico;

public class Gato extends Animal {
    public Gato(String nombre, int edad) {
        super(nombre, edad);
    }

    @Override
    public void hacerSonido() {
        System.out.println(nombre + ": Miau miau");
    }
}
