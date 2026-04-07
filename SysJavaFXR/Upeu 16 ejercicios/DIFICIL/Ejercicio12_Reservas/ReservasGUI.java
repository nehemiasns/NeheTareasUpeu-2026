package DIFICIL.Ejercicio12_Reservas;

import UI.ConsolePanel;
import javax.swing.*;
import java.awt.*;

public class ReservasGUI extends JFrame {
    private CentralReservas central;
    private ConsolePanel console;
    private JTextField txtId, txtCliente, txtFecha, txtHabitacion;
    private JTextField txtIdAccion;

    public ReservasGUI() {
        super("SISTEMA CENTRAL DE RESERVAS");
        central = new CentralReservas();
        setSize(700, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("ID Reserva:"));
        txtId = new JTextField();
        formPanel.add(txtId);

        formPanel.add(new JLabel("Cliente:"));
        txtCliente = new JTextField();
        formPanel.add(txtCliente);

        formPanel.add(new JLabel("Fecha (YYYY-MM-DD):"));
        txtFecha = new JTextField();
        formPanel.add(txtFecha);

        formPanel.add(new JLabel("Habitación N°:"));
        txtHabitacion = new JTextField();
        formPanel.add(txtHabitacion);

        formPanel.add(new JLabel());
        JButton btnAgregar = new JButton("Crear Nueva Reserva");
        btnAgregar.addActionListener(e -> agregar());
        formPanel.add(btnAgregar);

        formPanel.add(new JSeparator());
        formPanel.add(new JSeparator());

        add(formPanel, BorderLayout.NORTH);

        console = new ConsolePanel();
        add(console, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new FlowLayout());
        footerPanel.add(new JLabel("ID para Acción:"));
        txtIdAccion = new JTextField(5);
        footerPanel.add(txtIdAccion);

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(e -> accionar(true));
        footerPanel.add(btnConfirmar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> accionar(false));
        footerPanel.add(btnCancelar);

        JButton btnBuscar = new JButton("Tus Reservas (Escribe cliente arriba)");
        btnBuscar.addActionListener(e -> buscar());
        footerPanel.add(btnBuscar);

        add(footerPanel, BorderLayout.SOUTH);
    }

    private void agregar() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String cli = txtCliente.getText().trim();
            String fec = txtFecha.getText().trim();
            String hab = txtHabitacion.getText().trim();
            
            if (cli.isEmpty() || fec.isEmpty()) throw new IllegalArgumentException();

            Reserva r = new Reserva(id, cli, fec, hab);
            central.agregar(r);
            System.out.println("[+] Reserva #" + id + " añadida exitosamente (Pendiente).");
            
            txtId.setText(""); txtHabitacion.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Asegúrate de poner un ID numérico.");
        }
    }

    private void accionar(boolean esBotonConfirmar) {
        try {
            int id = Integer.parseInt(txtIdAccion.getText().trim());
            if (esBotonConfirmar) {
                central.confirmar(id);
            } else {
                central.cancelar(id);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ingresa un ID válido abajo.");
        }
    }

    private void buscar() {
        String cli = txtCliente.getText().trim();
        System.out.println("\n--- Búsqueda de " + cli + " ---");
        for (Reserva r : central.buscarPorCliente(cli)) {
            System.out.println(r.toString());
        }
    }
}
