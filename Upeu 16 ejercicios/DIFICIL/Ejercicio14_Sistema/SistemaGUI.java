package DIFICIL.Ejercicio14_Sistema;

import UI.ConsolePanel;
import javax.swing.*;
import java.awt.*;

public class SistemaGUI extends JFrame {
    private ConsolePanel console;
    private JTextField txtNombreSistema, txtHost, txtPuerto, txtUsuario;
    private Sistema.Configuracion configuracionCargada;

    public SistemaGUI() {
        super("SISTEMA & CONFIGURACIÓN (Clase Interna)");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Nombre del Sistema:"));
        txtNombreSistema = new JTextField();
        formPanel.add(txtNombreSistema);

        formPanel.add(new JLabel("Host de Conexión:"));
        txtHost = new JTextField();
        formPanel.add(txtHost);

        formPanel.add(new JLabel("Puerto numérico:"));
        txtPuerto = new JTextField();
        formPanel.add(txtPuerto);

        formPanel.add(new JLabel("Usuario de BD:"));
        txtUsuario = new JTextField();
        formPanel.add(txtUsuario);

        formPanel.add(new JLabel("Generar y Acoplar Configuración:"));
        JButton btnCrear = new JButton("Vincular Entorno");
        btnCrear.addActionListener(e -> generar());
        formPanel.add(btnCrear);

        add(formPanel, BorderLayout.NORTH);
        
        console = new ConsolePanel();
        add(console, BorderLayout.CENTER);

        JPanel footer = new JPanel();
        JButton btnMostrar = new JButton("Testear Configuración Cargada");
        btnMostrar.addActionListener(e -> probar());
        footer.add(btnMostrar);
        add(footer, BorderLayout.SOUTH);
    }

    private void generar() {
        try {
            String sis = txtNombreSistema.getText().trim();
            String hos = txtHost.getText().trim();
            int pue = Integer.parseInt(txtPuerto.getText().trim());
            String usr = txtUsuario.getText().trim();

            if (sis.isEmpty() || hos.isEmpty() || usr.isEmpty()) throw new IllegalArgumentException();

            Sistema sistema = new Sistema(sis);
            configuracionCargada = sistema.new Configuracion(hos, pue, usr);
            
            System.out.println("[+] Contexto de sistema '" + sis + "' y entorno creados exitosamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Asegúrate de llenar todos los campos y usar puerto numérico.");
        }
    }

    private void probar() {
        if (configuracionCargada == null) {
            System.out.println("No hay ninguna configuración vinculada en memoria.");
            return;
        }
        System.out.println("\n--- TESTING ---");
        configuracionCargada.mostrar();
    }
}
