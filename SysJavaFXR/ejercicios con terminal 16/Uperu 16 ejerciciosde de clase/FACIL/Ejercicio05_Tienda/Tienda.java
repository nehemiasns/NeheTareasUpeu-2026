package FACIL.Ejercicio05_Tienda;

import java.util.ArrayList;

public class Tienda {
    private ArrayList<Producto> productos;

    public Tienda() {
        productos = new ArrayList<>();
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
        for (Producto p : productos) {
            System.out.println(p);
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

    public static void main(String[] args) {
        Tienda tienda = new Tienda();

        tienda.agregarProducto(new Producto("Laptop", 800, "ElectrÃ³nica", 5));
        tienda.agregarProducto(new Producto("Mouse", 25, "ElectrÃ³nica", 20));
        tienda.agregarProducto(new Producto("Libro Java", 45, "EducaciÃ³n", 10));
        tienda.agregarProducto(new Producto("Novela", 30, "EducaciÃ³n", 15));

        System.out.println("=== Todos los productos ===");
        tienda.listarTodos();
        System.out.println();

        System.out.println("=== Productos en ElectrÃ³nica ===");
        ArrayList<Producto> electronicos = tienda.buscarPorCategoria("ElectrÃ³nica");
        for (Producto p : electronicos) {
            System.out.println(p);
        }
        System.out.println();

        System.out.println("=== Eliminando Mouse ===");
        tienda.eliminarPorNombre("Mouse");
        tienda.listarTodos();
    }
}
