package MEDIO.Ejercicio07_Biblioteca;

import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Libro> libros;

    public Biblioteca() {
        libros = new ArrayList<>();
    }

    public void agregar(Libro l) {
        libros.add(l);
    }

    public void eliminar(String isbn) {
        libros.removeIf(l -> l.getIsbn().equals(isbn));
    }

    public ArrayList<Libro> buscarPorAutor(String autor) {
        ArrayList<Libro> resultado = new ArrayList<>();
        for (Libro l : libros) {
            if (l.getAutor().equalsIgnoreCase(autor)) {
                resultado.add(l);
            }
        }
        return resultado;
    }

    public Libro libroMasCaro() {
        if (libros.isEmpty()) return null;
        Libro max = libros.get(0);
        for (Libro l : libros) {
            if (l.getPrecio() > max.getPrecio()) {
                max = l;
            }
        }
        return max;
    }

    public void listarTodos() {
        if (libros.isEmpty()) {
            System.out.println("No hay libros en la biblioteca");
            return;
        }
        for (Libro l : libros) {
            System.out.println(l);
        }
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.agregar(new Libro("Clean Code", "Robert Martin", "ISBN001", 50));
        biblioteca.agregar(new Libro("Java Effective", "Joshua Bloch", "ISBN002", 60));
        biblioteca.agregar(new Libro("Design Patterns", "Gang of Four", "ISBN003", 80));
        biblioteca.agregar(new Libro("Otro Libro", "Robert Martin", "ISBN004", 45));

        System.out.println("=== Todos los libros ===");
        biblioteca.listarTodos();
        System.out.println();

        System.out.println("=== Libros de Robert Martin ===");
        ArrayList<Libro> porAutor = biblioteca.buscarPorAutor("Robert Martin");
        for (Libro l : porAutor) {
            System.out.println(l);
        }
        System.out.println();

        System.out.println("=== Libro mÃ¡s caro ===");
        System.out.println(biblioteca.libroMasCaro());
    }
}
