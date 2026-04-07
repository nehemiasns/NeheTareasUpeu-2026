package DIFICIL.Ejercicio11_Voladores;

import UI.ConsolePanel;
import javax.swing.*;
import java.awt.*;

public class VoladoresGUI extends JFrame {
    private ConsolePanel console;

    public VoladoresGUI() {
        super("SISTEMA DE VOLADORES (Interfaces Múltiples)");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Seleccione un Animal Volador para demostrar sus habilidades:", SwingConstants.CENTER));
        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        
        JButton btnPato = new JButton("Pato");
        btnPato.addActionListener(e -> demostrar(new Pato("Pato Donald")));
        
        JButton btnAguila = new JButton("Águila");
        btnAguila.addActionListener(e -> demostrar(new Aguila("Águila Imperial")));

        btnPanel.add(btnPato); btnPanel.add(btnAguila);
        formPanel.add(btnPanel);
        
        add(formPanel, BorderLayout.NORTH);
        
        console = new ConsolePanel();
        add(console, BorderLayout.CENTER);
    }

    private void demostrar(Volador v) {
        System.out.println("========== DEMOSTRACIÓN ==========");
        System.out.println("Objeto: " + v.getClass().getSimpleName());
        v.volar();
        System.out.println("----------------------------------");
        
        if (v instanceof Nadador) {
            System.out.println(">> Funciones específicas como Nadador:");
            ((Nadador) v).nadar();
        } else if (v instanceof Aguila) {
            System.out.println(">> Habilidades Extras de Águila:");
            System.out.println("Vista panorámica super avispada.");
        }
        System.out.println();
    }
}
