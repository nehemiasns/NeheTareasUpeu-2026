package DIFICIL.Ejercicio13_Vehiculos;

import java.util.ArrayList;
import java.util.Scanner;

public class VehiculosInteractiva {
    private ArrayList<VehiculoFactory> vehiculos;
    private Scanner scanner;

    public VehiculosInteractiva() {
        vehiculos = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public static VehiculoFactory crear(String tipo, String marca, Object... args) {
        return switch (tipo.toLowerCase()) {
            case "auto" -> new AutoFactory(marca, (int) args[0]);
            case "moto" -> new MotoFactory(marca, (String) args[0]);
            case "camion" -> new CamionFactory(marca, (int) args[0]);
            default -> throw new IllegalArgumentException("Tipo desconocido: " + tipo);
        };
    }

    public void agregarVehiculo(VehiculoFactory v) {
        vehiculos.add(v);
    }

    public void listarVehiculos() {
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados");
            return;
        }
        for (int i = 0; i < vehiculos.size(); i++) {
            System.out.print((i + 1) + ". ");
            vehiculos.get(i).describir();
        }
    }

    public void menuInteractivo() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=================================");
            System.out.println("||   FÁBRICA DE VEHÍCULOS      ||");
            System.out.println("=================================");
            System.out.println("1. Crear Auto");
            System.out.println("2. Crear Moto");
            System.out.println("3. Crear Camión");
            System.out.println("4. Ver todos los vehículos");
            System.out.println("5. Cargar datos de prueba");
            System.out.println("0. Salir");
            System.out.print("Selecciona opción: ");

            int opcion = -1;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("[x] Por favor, ingresa un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    try {
                        System.out.print("Marca: ");
                        String marcaAuto = scanner.nextLine();
                        System.out.print("Número de puertas (entero): ");
                        int puertas = Integer.parseInt(scanner.nextLine());
                        
                        agregarVehiculo(crear("auto", marcaAuto, puertas));
                        System.out.println("[✓] Auto agregado");
                    } catch (NumberFormatException ex) {
                        System.out.println("[x] Error: Debes ingresar un número válido.");
                    } catch (Exception e) {
                        System.out.println("[x] Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Marca: ");
                    String marcaMoto = scanner.nextLine();
                    System.out.print("Tipo (deportiva/urbana): ");
                    String tipoMoto = scanner.nextLine();
                    try {
                        agregarVehiculo(crear("moto", marcaMoto, tipoMoto));
                        System.out.println("[✓] Moto agregada");
                    } catch (Exception e) {
                        System.out.println("[x] Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.print("Marca: ");
                        String marcaCamion = scanner.nextLine();
                        System.out.print("Toneladas de capacidad (entero): ");
                        int toneladas = Integer.parseInt(scanner.nextLine());
                        
                        agregarVehiculo(crear("camion", marcaCamion, toneladas));
                        System.out.println("[✓] Camión agregado");
                    } catch (NumberFormatException ex) {
                        System.out.println("[x] Error: Debes ingresar un número entero válido.");
                    } catch (Exception e) {
                        System.out.println("[x] Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("\n--- Vehículos Registrados ---");
                    listarVehiculos();
                    break;
                case 5:
                    agregarVehiculo(crear("auto", "Toyota", 4));
                    agregarVehiculo(crear("moto", "Yamaha", "deportiva"));
                    agregarVehiculo(crear("camion", "Volvo", 10));
                    System.out.println("[✓] Datos de prueba cargados");
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    public static void main(String[] args) {
        VehiculosInteractiva fabrica = new VehiculosInteractiva();
        fabrica.menuInteractivo();
    }
}
