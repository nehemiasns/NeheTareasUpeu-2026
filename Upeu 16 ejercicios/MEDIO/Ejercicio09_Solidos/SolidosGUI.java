package MEDIO.Ejercicio09_Solidos;

import UI.ConsolePanel;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SolidosGUI extends JFrame {
    private ArrayList<Solido3D> figuras;
    private ConsolePanel console;
    private JTextField txtDimension;

    public SolidosGUI() {
        super("SÓLIDOS GEOMÉTRICOS 3D");
        figuras = new ArrayList<>();
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Dimensión (Lado para Cubo / Radio para Esfera):"));
        txtDimension = new JTextField();
        formPanel.add(txtDimension);

        formPanel.add(new JLabel("Añadir Sólido:"));
        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        
        JButton btnCubo = new JButton("Cubo");
        btnCubo.addActionListener(e -> agregar(true));
        
        JButton btnEsfera = new JButton("Esfera");
        btnEsfera.addActionListener(e -> agregar(false));

        btnPanel.add(btnCubo); btnPanel.add(btnEsfera);
        formPanel.add(btnPanel);
        
        add(formPanel, BorderLayout.NORTH);
        
        console = new ConsolePanel();
        add(console, BorderLayout.CENTER);

        JPanel footer = new JPanel();
        JButton btnCalcular = new JButton("CALCULAR VOLUMEN DE TODOS");
        btnCalcular.addActionListener(e -> mostrarCalculos());
        footer.add(btnCalcular);
        add(footer, BorderLayout.SOUTH);
    }

    private void agregar(boolean esCubo) {
        try {
            double dim = Double.parseDouble(txtDimension.getText().trim());
            
            if (esCubo) {
                figuras.add(new Cubo("Azul Neón", dim));
                System.out.println("[+] Cubo de lado " + dim + " agregado.");
            } else {
                figuras.add(new Esfera("Azul Neón", dim));
                System.out.println("[+] Esfera de radio " + dim + " agregada.");
            }
            txtDimension.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Dimension inválida.");
        }
    }

    private void mostrarCalculos() {
        System.out.println("\n=== ANÁLISIS DE VOLUMEN 3D ===");
        if(figuras.isEmpty()) {
            System.out.println("No hay sólidos registrados.");
            return;
        }
        
        for (Solido3D f : figuras) {
            String tipo = f.getClass().getSimpleName();
            System.out.printf("%s -> Volumen: %.2f%n", tipo, f.calcularVolumen());
        }
    }
}
