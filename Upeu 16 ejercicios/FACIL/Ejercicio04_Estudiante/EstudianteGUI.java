package FACIL.Ejercicio04_Estudiante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EstudianteGUI extends JFrame {
    private JTextField txtNombre, txtCodigo, txtNota;
    private DefaultTableModel tableModel;

    public EstudianteGUI() {
        super("GESTIÓN DE ESTUDIANTES");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Nombre del Estudiante:"));
        txtNombre = new JTextField();
        formPanel.add(txtNombre);

        formPanel.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        formPanel.add(txtCodigo);

        formPanel.add(new JLabel("Nota (0 - 10):"));
        txtNota = new JTextField();
        formPanel.add(txtNota);

        JButton btnRegistrar = new JButton("Registrar Estudiante");
        btnRegistrar.addActionListener(e -> registrar());
        
        formPanel.add(new JLabel());
        formPanel.add(btnRegistrar);
        
        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"Nombre", "Código", "Nota", "Calificación"}, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void registrar() {
        try {
            String nombre = txtNombre.getText().trim();
            String codigo = txtCodigo.getText().trim();
            double nota = Double.parseDouble(txtNota.getText().trim());

            if(nota < 0 || nota > 10) {
                JOptionPane.showMessageDialog(this, "La nota debe ser entre 0 y 10");
                return;
            }

            Estudiante est = new Estudiante(nombre, codigo, nota);
            tableModel.addRow(new Object[]{est.getNombre(), est.getCodigo(), est.getNota(), est.getCalificacion()});
            
            txtNombre.setText(""); txtCodigo.setText(""); txtNota.setText("");
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Datos numéricos inválidos.");
        }
    }
}
