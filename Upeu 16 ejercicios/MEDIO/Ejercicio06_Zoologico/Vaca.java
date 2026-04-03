package MEDIO.Ejercicio06_Zoologico;

public class Vaca extends Animal {
    public Vaca(String nombre, int edad) {
        super(nombre, edad);
    }

    @Override
    public void hacerSonido() {
        System.out.println(nombre + ": Muuu");
    }
}
