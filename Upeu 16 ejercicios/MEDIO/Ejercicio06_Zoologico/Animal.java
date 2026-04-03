package MEDIO.Ejercicio06_Zoologico;

public abstract class Animal {
    protected String nombre;
    protected int edad;

    public Animal(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public abstract void hacerSonido();

    public void comer() {
        System.out.println(nombre + " estÃ¡ comiendo");
    }

    public String getNombre() {
        return nombre;
    }
}
