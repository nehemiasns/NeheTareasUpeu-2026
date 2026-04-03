package DIFICIL.Ejercicio14_Sistema;

import java.util.Scanner;

public class Sistema {
    private String nombre;

    public class Configuracion {
        private String host;
        private int puerto;
        private String usuario;

        public Configuracion(String host, int puerto, String usuario) {
            this.host = host;
            this.puerto = puerto;
            this.usuario = usuario;
        }

        public String getHost() {
            return host;
        }

        public int getPuerto() {
            return puerto;
        }

        public String getUsuario() {
            return usuario;
        }

        public void mostrar() {
            System.out.println("=== Configuracion de " + nombre + " ===");
            System.out.println("Host: " + host);
            System.out.println("Puerto: " + puerto);
            System.out.println("Usuario: " + usuario);
        }
    }

    public Sistema(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== SISTEMA Y CONFIGURACIÓN (CLASE INTERNA) ===");
        System.out.print("Ingresa el nombre del Sistema: ");
        String nombreSistema = scanner.nextLine();
        
        Sistema sistema = new Sistema(nombreSistema);
        
        System.out.println("\n--- Creando Configuración ---");
        System.out.print("Host (ej. localhost): ");
        String host = scanner.nextLine();
        System.out.print("Puerto (ej. 8080): ");
        int puerto = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();
        
        Sistema.Configuracion config = sistema.new Configuracion(host, puerto, usuario);
        
        System.out.println("\n¡Configuración registrada exitosamente!");
        System.out.println("\nPresiona ENTER para mostrar la configuración...");
        scanner.nextLine();
        
        config.mostrar();
    }
}
