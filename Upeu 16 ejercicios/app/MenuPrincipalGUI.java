package app;

import UI.StyleApplier;
import javax.swing.*;
import java.awt.*;

public class MenuPrincipalGUI extends JFrame {

    public MenuPrincipalGUI() {
        super("Ejercicios POO - Neon Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout(10, 10));
        
        JLabel titleLabel = new JLabel("Ejercicios de Java (Modo Interfaz Gráfica)", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(StyleApplier.COLOR_NEON_CYAN);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        gridPanel.add(crearCategoriaPanel("FÁCIL", new String[]{
            "01. Persona", "02. Automóvil", "03. Rectángulo", "04. Estudiante", "05. Tienda"
        }));

        gridPanel.add(crearCategoriaPanel("MEDIO", new String[]{
            "06. Zoológico", "07. Biblioteca", "08. Nómina", "09. Sólidos 3D", "10. Ranking"
        }));

        gridPanel.add(crearCategoriaPanel("DIFÍCIL", new String[]{
            "11. Voladores", "12. Reservas", "13. Vehículos", "14. Sistema", "15. Excepciones"
        }));

        gridPanel.add(crearCategoriaPanel("EXTRA", new String[]{
            "16. Hospital Completo"
        }));

        add(gridPanel, BorderLayout.CENTER);
    }

    private JPanel crearCategoriaPanel(String titulo, String[] ejercicios) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(StyleApplier.COLOR_NEON_CYAN, 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panel.setBackground(StyleApplier.COLOR_DARK_GRAY);

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        panel.add(lblTitulo, BorderLayout.NORTH);

        JPanel btnPanel = new JPanel(new GridLayout(ejercicios.length, 1, 5, 5));
        btnPanel.setOpaque(false);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        for (String ej : ejercicios) {
            JButton btn = new JButton(ej);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.addActionListener(e -> {
                abrirEjercicio(ej);
            });
            btnPanel.add(btn);
        }

        panel.add(btnPanel, BorderLayout.CENTER);
        return panel;
    }

    private void abrirEjercicio(String nombreEjercicio) {
        
        if (nombreEjercicio.contains("01. Persona")) {
            new FACIL.Ejercicio01_Persona.PersonaGUI().setVisible(true);
        } else if (nombreEjercicio.contains("02. Automóvil")) {
            new FACIL.Ejercicio02_Automovil.AutomovilGUI().setVisible(true);
        } else if (nombreEjercicio.contains("03. Rectángulo")) {
            new FACIL.Ejercicio03_Rectangulo.RectanguloGUI().setVisible(true);
        } else if (nombreEjercicio.contains("04. Estudiante")) {
            new FACIL.Ejercicio04_Estudiante.EstudianteGUI().setVisible(true);
        } else if (nombreEjercicio.contains("05. Tienda")) {
            new FACIL.Ejercicio05_Tienda.TiendaGUI().setVisible(true);
        } else if (nombreEjercicio.contains("06. Zoológico")) {
            new MEDIO.Ejercicio06_Zoologico.ZoologicoGUI().setVisible(true);
        } else if (nombreEjercicio.contains("07. Biblioteca")) {
            new MEDIO.Ejercicio07_Biblioteca.BibliotecaGUI().setVisible(true);
        } else if (nombreEjercicio.contains("08. Nómina")) {
            new MEDIO.Ejercicio08_Nomina.NominaGUI().setVisible(true);
        } else if (nombreEjercicio.contains("09. Sólidos 3D")) {
            new MEDIO.Ejercicio09_Solidos.SolidosGUI().setVisible(true);
        } else if (nombreEjercicio.contains("10. Ranking")) {
            new MEDIO.Ejercicio10_Ranking.RankingGUI().setVisible(true);
        } else if (nombreEjercicio.contains("11. Voladores")) {
            new DIFICIL.Ejercicio11_Voladores.VoladoresGUI().setVisible(true);
        } else if (nombreEjercicio.contains("12. Reservas")) {
            new DIFICIL.Ejercicio12_Reservas.ReservasGUI().setVisible(true);
        } else if (nombreEjercicio.contains("13. Vehículos")) {
            new DIFICIL.Ejercicio13_Vehiculos.VehiculosGUI().setVisible(true);
        } else if (nombreEjercicio.contains("14. Sistema")) {
            new DIFICIL.Ejercicio14_Sistema.SistemaGUI().setVisible(true);
        } else if (nombreEjercicio.contains("15. Excepciones")) {
            new DIFICIL.Ejercicio15_Excepciones.ExcepcionesGUI().setVisible(true);
        } else if (nombreEjercicio.contains("16. Hospital Completo")) {
            new EXTRA.Ejercicio16_Hospital.HospitalGUI().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, 
                "Próximamente: " + nombreEjercicio + "\n(Migración a GUI pendiente en la siguiente fase).",
                "En desarrollo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        StyleApplier.applyNeonDarkStyle();
        
        SwingUtilities.invokeLater(() -> {
            new MenuPrincipalGUI().setVisible(true);
        });
    }
}
