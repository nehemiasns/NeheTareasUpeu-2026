package FACIL.Ejercicio01_Persona;

import UI.StyleApplier;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PersonaGUI extends JFrame {
    private JTextField txtNombre;
    private JTextField txtEdad;
    private DefaultTableModel tableModel;

    public PersonaGUI() {
        super("SISTEMA DE PERSONAS");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        inputPanel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        inputPanel.add(txtNombre);

        inputPanel.add(new JLabel("Edad:"));
        txtEdad = new JTextField();
        inputPanel.add(txtEdad);

        JButton btnRegistrar = new JButton("Registrar Persona");
        btnRegistrar.addActionListener(e -> registrarPersona());
        
        inputPanel.add(new JLabel());
        inputPanel.add(btnRegistrar);

        add(inputPanel, BorderLayout.NORTH);

        String[] columnas = {"Nombre", "Edad", "Correo"};
        tableModel = new DefaultTableModel(columnas, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        add(scrollPane, BorderLayout.CENTER);
    }

    private void registrarPersona() {
        String nombre = txtNombre.getText().trim();
        String edadStr = txtEdad.getText().trim();

        if (nombre.isEmpty() || edadStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int edad = Integer.parseInt(edadStr);
            Persona p = new Persona(nombre, edad, "sin-correo@example.com");

            tableModel.addRow(new Object[]{p.getNombre(), p.getEdad(), p.getCorreo()});

            txtNombre.setText("");
            txtEdad.setText("");
            txtNombre.requestFocus();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La edad debe ser un número entero", "Cuidado", JOptionPane.WARNING_MESSAGE);
        }
    }
}
