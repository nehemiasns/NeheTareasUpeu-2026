package FACIL.Ejercicio03_Rectangulo;

import javax.swing.*;
import java.awt.*;

public class RectanguloGUI extends JFrame {
    private JTextField txtBase, txtAltura;
    private JLabel lblResult;

    public RectanguloGUI() {
        super("SISTEMA DE RECTÁNGULOS");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Base:", SwingConstants.RIGHT));
        txtBase = new JTextField();
        formPanel.add(txtBase);

        formPanel.add(new JLabel("Altura:", SwingConstants.RIGHT));
        txtAltura = new JTextField();
        formPanel.add(txtAltura);
        
        add(formPanel, BorderLayout.NORTH);

        JButton btnCalcular = new JButton("CALCULAR");
        btnCalcular.addActionListener(e -> calcular());
        
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnCalcular);
        add(btnPanel, BorderLayout.CENTER);

        lblResult = new JLabel("Esperando datos...", SwingConstants.CENTER);
        lblResult.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(lblResult, BorderLayout.SOUTH);
    }

    private void calcular() {
        try {
            double base = Double.parseDouble(txtBase.getText().trim());
            double alt = Double.parseDouble(txtAltura.getText().trim());
            
            Rectangulo r = new Rectangulo(base, alt);
            String html = "<html><center>Área: " + r.calcularArea() 
                        + "<br>Perímetro: " + r.calcularPerimetro() 
                        + "<br>¿Es cuadrado?: " + (r.esCuadrado() ? "SÍ" : "NO") 
                        + "</center></html>";
            lblResult.setText(html);
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese valores numéricos válidos.");
        }
    }
}
