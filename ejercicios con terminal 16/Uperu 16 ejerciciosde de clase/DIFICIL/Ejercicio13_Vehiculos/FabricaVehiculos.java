package DIFICIL.Ejercicio13_Vehiculos;

import java.util.ArrayList;

public class FabricaVehiculos {
    public static VehiculoFactory crear(String tipo, String marca, Object... args) {
        return switch (tipo.toLowerCase()) {
            case "auto" -> new AutoFactory(marca, (int) args[0]);
            case "moto" -> new MotoFactory(marca, (String) args[0]);
            case "camion" -> new CamionFactory(marca, (int) args[0]);
            default -> throw new IllegalArgumentException("Tipo desconocido: " + tipo);
        };
    }

    public static void main(String[] args) {
        ArrayList<VehiculoFactory> vehiculos = new ArrayList<>();

        vehiculos.add(crear("auto", "Toyota", 4));
        vehiculos.add(crear("moto", "Yamaha", "deportiva"));
        vehiculos.add(crear("camion", "Volvo", 10));

        System.out.println("=== VehÃ­culos Creados ===");
        for (VehiculoFactory v : vehiculos) {
            v.describir();
        }
    }
}
