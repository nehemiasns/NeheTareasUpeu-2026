package DIFICIL.Ejercicio13_Vehiculos;

import UI.ConsolePanel;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VehiculosGUI extends JFrame {
    private ArrayList<VehiculoFactory> vehiculos;
    private ConsolePanel console;
    private JTextField txtMarca, txtParametro;
    private JComboBox<String> cmbTipo;

    public VehiculosGUI() {
        super("FÁBRICA DE VEHÍCULOS (Factory Pattern)");
        vehiculos = new ArrayList<>();
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Tipo de Vehículo:"));
        cmbTipo = new JComboBox<>(new String[]{"Auto", "Moto", "Camion"});
        formPanel.add(cmbTipo);

        formPanel.add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        formPanel.add(txtMarca);

        formPanel.add(new JLabel("Parámetro (Puertas, Tipo o Tonelaje):"));
        txtParametro = new JTextField();
        formPanel.add(txtParametro);

        formPanel.add(new JLabel());
        JButton btnCrear = new JButton("Ensamblar Vehículo");
        btnCrear.addActionListener(e -> crearVehiculo());
        formPanel.add(btnCrear);

        add(formPanel, BorderLayout.NORTH);
        
        console = new ConsolePanel();
        add(console, BorderLayout.CENTER);

        JPanel footer = new JPanel();
        JButton btnMostrar = new JButton("MOSTRAR GARAJE");
        btnMostrar.addActionListener(e -> mostrarGaraje());
        footer.add(btnMostrar);
        add(footer, BorderLayout.SOUTH);
    }

    private void crearVehiculo() {
        try {
            String tipo = cmbTipo.getSelectedItem().toString().toLowerCase();
            String marca = txtMarca.getText().trim();
            String paramStr = txtParametro.getText().trim();

            if(marca.isEmpty() || paramStr.isEmpty()) throw new IllegalArgumentException();

            Object parametroObj;
            if(tipo.equals("moto")) {
                parametroObj = paramStr;
            } else {
                parametroObj = Integer.parseInt(paramStr);
            }

            VehiculoFactory v = FabricaVehiculos.crear(tipo, marca, parametroObj);
            vehiculos.add(v);
            
            System.out.println("[+] " + tipo.toUpperCase() + " '" + marca + "' ensamblado y guardado.");
            txtMarca.setText(""); txtParametro.setText("");
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos. Recuerda que Autos y Camiones requieren un número entero.");
        }
    }

    private void mostrarGaraje() {
        System.out.println("\n=== REGISTRO DEL GARAJE ===");
        if(vehiculos.isEmpty()) {
            System.out.println("Garaje vacío.");
            return;
        }
        for (VehiculoFactory v : vehiculos) {
            v.describir();
        }
    }
}
