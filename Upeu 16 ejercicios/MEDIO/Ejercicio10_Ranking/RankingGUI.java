package MEDIO.Ejercicio10_Ranking;

import UI.ConsolePanel;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class RankingGUI extends JFrame {
    private ArrayList<Jugador> jugadores;
    private ConsolePanel console;
    private JTextField txtNombre, txtPuntaje;

    public RankingGUI() {
        super("SISTEMA DE RANKING (Comparator)");
        jugadores = new ArrayList<>();
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Nombre Jugador:"));
        txtNombre = new JTextField();
        formPanel.add(txtNombre);

        formPanel.add(new JLabel("Puntaje Logrado:"));
        txtPuntaje = new JTextField();
        formPanel.add(txtPuntaje);

        formPanel.add(new JLabel());
        JButton btnAgregar = new JButton("Ingresar Puntuación");
        btnAgregar.addActionListener(e -> agregar());
        formPanel.add(btnAgregar);
        
        add(formPanel, BorderLayout.NORTH);
        
        console = new ConsolePanel();
        add(console, BorderLayout.CENTER);

        JPanel footer = new JPanel();
        JButton btnMostrar = new JButton("MOSTRAR RANKING ORDENADO DESCENDENTE");
        btnMostrar.addActionListener(e -> mostrar());
        footer.add(btnMostrar);
        add(footer, BorderLayout.SOUTH);
    }

    private void agregar() {
        try {
            String nombre = txtNombre.getText().trim();
            int puntaje = Integer.parseInt(txtPuntaje.getText().trim());
            
            if(nombre.isEmpty()) throw new IllegalArgumentException();

            jugadores.add(new Jugador(nombre, puntaje));
            System.out.println("[+] " + nombre + " añadido con " + puntaje + " puntos.");
            
            txtNombre.setText(""); txtPuntaje.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos.");
        }
    }

    private void mostrar() {
        System.out.println("\n=== TOP GLOBAL ===");
        if(jugadores.isEmpty()) {
            System.out.println("Nadie ha jugado aún.");
            return;
        }
        
        jugadores.sort((a, b) -> Integer.compare(b.getPuntos(), a.getPuntos()));
        
        int r = 1;
        for (Jugador j : jugadores) {
            System.out.printf("#%d - %s (Pts: %d)%n", r++, j.getNombre(), j.getPuntos());
        }
    }
}
