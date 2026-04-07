package MEDIO.Ejercicio07_Biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaInteractiva {
    private ArrayList<Libro> libros;
    private Scanner scanner;

    public BibliotecaInteractiva() {
        libros = new ArrayList<>();
        scanner = new Scanner(System.in);
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
        for (int i = 0; i < libros.size(); i++) {
            System.out.println((i + 1) + ". " + libros.get(i));
        }
    }

    public void menuInteractivo() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nâ•”â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•—");
            System.out.println("â•‘   BIBLIOTECA INTERACTIVA        â•‘");
            System.out.println("â•šâ•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•");
            System.out.println("1. Agregar libro");
            System.out.println("2. Ver todos los libros");
            System.out.println("3. Buscar por autor");
            System.out.println("4. Libro mÃ¡s caro");
            System.out.println("5. Eliminar libro");
            System.out.println("6. Cargar datos de prueba");
            System.out.println("0. Salir");
            System.out.print("Selecciona opciÃ³n: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("TÃ­tulo: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Precio: ");
                    double precio = scanner.nextDouble();
                    scanner.nextLine();
                    agregar(new Libro(titulo, autor, isbn, precio));
                    System.out.println("âœ“ Libro agregado");
                    break;
                case 2:
                    System.out.println("\n--- Todos los Libros ---");
                    listarTodos();
                    break;
                case 3:
                    System.out.print("Autor a buscar: ");
                    String autorBuscar = scanner.nextLine();
                    ArrayList<Libro> porAutor = buscarPorAutor(autorBuscar);
                    if (porAutor.isEmpty()) {
                        System.out.println("No hay libros de ese autor");
                    } else {
                        System.out.println("Libros de " + autorBuscar + ":");
                        for (Libro l : porAutor) {
                            System.out.println("  " + l);
                        }
                    }
                    break;
                case 4:
                    Libro caro = libroMasCaro();
                    if (caro == null) {
                        System.out.println("No hay libros");
                    } else {
                        System.out.println("Libro mÃ¡s caro: " + caro);
                    }
                    break;
                case 5:
                    System.out.print("ISBN del libro a eliminar: ");
                    String isbnEliminar = scanner.nextLine();
                    eliminar(isbnEliminar);
                    System.out.println("âœ“ Libro eliminado");
                    break;
                case 6:
                    agregar(new Libro("Clean Code", "Robert Martin", "ISBN001", 50));
                    agregar(new Libro("Java Effective", "Joshua Bloch", "ISBN002", 60));
                    agregar(new Libro("Design Patterns", "Gang of Four", "ISBN003", 80));
                    agregar(new Libro("Otro Libro", "Robert Martin", "ISBN004", 45));
                    System.out.println("âœ“ Datos de prueba cargados");
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("OpciÃ³n invÃ¡lida");
            }
        }
    }

    public static void main(String[] args) {
        BibliotecaInteractiva biblioteca = new BibliotecaInteractiva();
        biblioteca.menuInteractivo();
    }
}
