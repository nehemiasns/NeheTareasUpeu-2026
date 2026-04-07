package FACIL.Ejercicio02_Automovil;

import javax.swing.*;
import java.awt.*;

public class AutomovilGUI extends JFrame {
    private Automovil auto;
    private JLabel lblVelocidad;

    public AutomovilGUI() {
        super("SIMULADOR DE AUTOMÓVIL");
        auto = new Automovil("DemoMarca", "DemoModelo", 2024);
        
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        lblVelocidad = new JLabel("0 km/h", SwingConstants.CENTER);
        lblVelocidad.setFont(new Font("Segoe UI", Font.BOLD, 60));
        lblVelocidad.setForeground(Color.WHITE);
        add(lblVelocidad, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnAcelerar = new JButton("ACELERAR");
        JButton btnFrenar = new JButton("FRENAR");

        btnAcelerar.addActionListener(e -> {
            auto.acelerar(10);
            actualizarVelocimetro();
        });

        btnFrenar.addActionListener(e -> {
            auto.frenar(10);
            actualizarVelocimetro();
        });

        controlPanel.add(btnFrenar);
        controlPanel.add(btnAcelerar);

        add(controlPanel, BorderLayout.SOUTH);
    }

    private void actualizarVelocimetro() {
        lblVelocidad.setText(auto.getVelocidadActual() + " km/h");
        if(auto.getVelocidadActual() > 100) {
            lblVelocidad.setForeground(Color.RED);
        } else if (auto.getVelocidadActual() > 0) {
            lblVelocidad.setForeground(new Color(0, 255, 255));
        } else {
            lblVelocidad.setForeground(Color.WHITE);
        }
    }
}
