package DIFICIL.Ejercicio15_Excepciones;

import java.util.ArrayList;
import java.util.Scanner;

public class ListaLimitada {
    private ArrayList<Object> lista;
    private int capacidadMaxima;

    public ListaLimitada(int capacidadMaxima) {
        this.lista = new ArrayList<>();
        this.capacidadMaxima = capacidadMaxima;
    }

    public void agregar(Object elem) {
        if (lista.size() >= capacidadMaxima) {
            throw new CapacidadExcedidaException("Lista llena, capacidad maxima: " + capacidadMaxima);
        }
        lista.add(elem);
    }

    public Object remover(int indice) {
        return lista.remove(indice);
    }

    public Object obtener(int indice) {
        return lista.get(indice);
    }

    public int getTamano() {
        return lista.size();
    }

    public void listar() {
        System.out.println(lista);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== SISTEMA DE EXCEPCIONES: LISTA LIMITADA ===");
        System.out.print("Ingresa la capacidad máxima de tu lista: ");
        int limite = scanner.nextInt();
        scanner.nextLine();
        
        ListaLimitada lista = new ListaLimitada(limite);
        boolean excepcionLanzada = false;

        System.out.println("\nComenzando captura de elementos...");

        while (!excepcionLanzada) {
            System.out.println("\n1. Agregar elemento");
            System.out.println("2. Ver elementos actuales");
            System.out.println("0. Salir prematuramente");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Escribe el elemento (String): ");
                    String elemento = scanner.nextLine();
                    try {
                        lista.agregar(elemento);
                        System.out.println("-> Agregado. Tamaño actual: " + lista.getTamano());
                    } catch (CapacidadExcedidaException e) {
                        System.out.println("\n[!] EXCEPCIÓN ATRAPADA [!]");
                        System.out.println("Error: " + e.getMessage());
                        excepcionLanzada = true;
                        System.out.println("-> Terminando ejercicio por desbordamiento controlado.");
                    }
                    break;
                case 2:
                    System.out.print("Elementos: ");
                    lista.listar();
                    break;
                case 0:
                    excepcionLanzada = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
