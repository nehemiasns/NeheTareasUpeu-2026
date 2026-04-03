package DIFICIL.Ejercicio11_Voladores;

public abstract class AnimalInterfaz {
    protected String nombre;

    public AnimalInterfaz(String nombre) {
        this.nombre = nombre;
    }

    public void comer() {
        System.out.println(nombre + " estÃ¡ comiendo");
    }

    public String getNombre() {
        return nombre;
    }
}
