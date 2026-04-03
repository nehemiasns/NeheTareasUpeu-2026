package MEDIO.Ejercicio06_Zoologico;

import UI.ConsolePanel;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ZoologicoGUI extends JFrame {
    private ArrayList<Animal> animales;
    private ConsolePanel console;
    private JTextField txtNombre, txtEdad;

    public ZoologicoGUI() {
        super("SISTEMA DE ZOOLÓGICO POLIMÓRFICO");
        animales = new ArrayList<>();
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Nombre del Animal:"));
        txtNombre = new JTextField();
        formPanel.add(txtNombre);

        formPanel.add(new JLabel("Edad (años):"));
        txtEdad = new JTextField();
        formPanel.add(txtEdad);

        formPanel.add(new JLabel("Agregar Especie:"));
        JPanel btnPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        
        JButton btnPerro = new JButton("Perro");
        btnPerro.addActionListener(e -> agregarAnimal("Perro"));
        
        JButton btnGato = new JButton("Gato");
        btnGato.addActionListener(e -> agregarAnimal("Gato"));
        
        JButton btnVaca = new JButton("Vaca");
        btnVaca.addActionListener(e -> agregarAnimal("Vaca"));

        btnPanel.add(btnPerro); btnPanel.add(btnGato); btnPanel.add(btnVaca);
        formPanel.add(btnPanel);
        
        add(formPanel, BorderLayout.NORTH);

        console = new ConsolePanel();
        add(console, BorderLayout.CENTER);

        JPanel footer = new JPanel();
        JButton btnEjecutarDesc = new JButton("EJECUTAR SONIDOS Y COMER (Polimorfismo)");
        btnEjecutarDesc.addActionListener(e -> ejecutarPolimorfismo());
        footer.add(btnEjecutarDesc);
        add(footer, BorderLayout.SOUTH);
    }

    private void agregarAnimal(String especie) {
        try {
            String nombre = txtNombre.getText().trim();
            int edad = Integer.parseInt(txtEdad.getText().trim());
            
            if(nombre.isEmpty()) throw new IllegalArgumentException();

            switch(especie) {
                case "Perro" -> animales.add(new Perro(nombre, edad));
                case "Gato" -> animales.add(new Gato(nombre, edad));
                case "Vaca" -> animales.add(new Vaca(nombre, edad));
            }
            
            System.out.println("[+] Se ha agregado un " + especie + " llamado " + nombre);
            txtNombre.setText(""); txtEdad.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un nombre y una edad numérica.");
        }
    }

    private void ejecutarPolimorfismo() {
        System.out.println("\n=== INICIANDO RUTINA DEL ZOOLÓGICO ===");
        if(animales.isEmpty()) {
            System.out.println("El zoológico está vacío.");
            return;
        }
        
        for (Animal a : animales) {
            a.hacerSonido();
            a.comer();
            System.out.println("---");
        }
    }
}
