package FACIL.Ejercicio05_Tienda;

import java.util.ArrayList;
import java.util.Scanner;

public class TiendaInteractiva {
    private ArrayList<Producto> productos;
    private Scanner scanner;

    public TiendaInteractiva() {
        productos = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public void eliminarPorNombre(String nombre) {
        productos.removeIf(p -> p.getNombre().equalsIgnoreCase(nombre));
    }

    public void listarTodos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos en la tienda");
            return;
        }
        for (int i = 0; i < productos.size(); i++) {
            System.out.println((i + 1) + ". " + productos.get(i));
        }
    }

    public ArrayList<Producto> buscarPorCategoria(String cat) {
        ArrayList<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getCategoria().equalsIgnoreCase(cat)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public void menuInteractivo() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nâ•”â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•—");
            System.out.println("â•‘   TIENDA INTERACTIVA            â•‘");
            System.out.println("â•šâ•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•");
            System.out.println("1. Agregar producto");
            System.out.println("2. Ver todos los productos");
            System.out.println("3. Buscar por categorÃ­a");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Cargar datos de prueba");
            System.out.println("0. Salir");
            System.out.print("Selecciona opciÃ³n: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Precio: ");
                    double precio = scanner.nextDouble();
                    System.out.print("Stock: ");
                    int stock = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("CategorÃ­a: ");
                    String categoria = scanner.nextLine();
                    agregarProducto(new Producto(nombre, precio, categoria, stock));
                    System.out.println("âœ“ Producto agregado");
                    break;
                case 2:
                    System.out.println("\n--- Productos ---");
                    listarTodos();
                    break;
                case 3:
                    System.out.print("CategorÃ­a a buscar: ");
                    String cat = scanner.nextLine();
                    ArrayList<Producto> resultado = buscarPorCategoria(cat);
                    if (resultado.isEmpty()) {
                        System.out.println("No hay productos en esa categorÃ­a");
                    } else {
                        for (Producto p : resultado) {
                            System.out.println(p);
                        }
                    }
                    break;
                case 4:
                    System.out.print("Nombre del producto a eliminar: ");
                    String nombreEliminar = scanner.nextLine();
                    eliminarPorNombre(nombreEliminar);
                    System.out.println("âœ“ Producto eliminado");
                    break;
                case 5:
                    agregarProducto(new Producto("Laptop", 800, "ElectrÃ³nica", 5));
                    agregarProducto(new Producto("Mouse", 25, "ElectrÃ³nica", 20));
                    agregarProducto(new Producto("Libro Java", 45, "EducaciÃ³n", 10));
                    agregarProducto(new Producto("Novela", 30, "EducaciÃ³n", 15));
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
        TiendaInteractiva tienda = new TiendaInteractiva();
        tienda.menuInteractivo();
    }
}
