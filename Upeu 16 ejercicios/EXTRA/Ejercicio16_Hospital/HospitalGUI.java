package EXTRA.Ejercicio16_Hospital;

import UI.ConsolePanel;
import javax.swing.*;
import java.awt.*;

public class HospitalGUI extends JFrame {
    private Hospital hospital;
    private ConsolePanel console;
    
    private JTextField txtDocNombre, txtDocDni, txtDocEspecialidad;
    private JTextField txtPacNombre, txtPacDni, txtPacEnfermedad;

    public HospitalGUI() {
        super("SISTEMA HOSPITALARIO (Sistema Compuesto)");
        hospital = new Hospital();
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel splitPane = new JPanel(new GridLayout(1, 2, 10, 10));
        splitPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel docPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        docPanel.setBorder(BorderFactory.createTitledBorder("Módulo Doctores"));
        docPanel.add(new JLabel("Nombre y Apellido:"));
        txtDocNombre = new JTextField();
        docPanel.add(txtDocNombre);
        docPanel.add(new JLabel("DNI:"));
        txtDocDni = new JTextField();
        docPanel.add(txtDocDni);
        docPanel.add(new JLabel("Especialidad:"));
        txtDocEspecialidad = new JTextField();
        docPanel.add(txtDocEspecialidad);
        docPanel.add(new JLabel());
        JButton btnAgregarDoc = new JButton("Ingresar Doctor");
        btnAgregarDoc.addActionListener(e -> agregarDoctor());
        docPanel.add(btnAgregarDoc);

        JPanel pacPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        pacPanel.setBorder(BorderFactory.createTitledBorder("Módulo Pacientes"));
        pacPanel.add(new JLabel("Nombre y Apellido:"));
        txtPacNombre = new JTextField();
        pacPanel.add(txtPacNombre);
        pacPanel.add(new JLabel("DNI:"));
        txtPacDni = new JTextField();
        pacPanel.add(txtPacDni);
        pacPanel.add(new JLabel("Enfermedad Base:"));
        txtPacEnfermedad = new JTextField();
        pacPanel.add(txtPacEnfermedad);
        pacPanel.add(new JLabel());
        JButton btnAgregarPac = new JButton("Ingresar Paciente");
        btnAgregarPac.addActionListener(e -> agregarPaciente());
        pacPanel.add(btnAgregarPac);

        splitPane.add(docPanel);
        splitPane.add(pacPanel);
        
        add(splitPane, BorderLayout.NORTH);

        console = new ConsolePanel();
        add(console, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout());
        JButton btnAsignarAuto = new JButton("Asignar aleatoriamente (Demo)");
        btnAsignarAuto.addActionListener(e -> asignarAleatorio());
        footer.add(btnAsignarAuto);

        JButton btnReporte = new JButton("VER REPORTE GENERAL");
        btnReporte.addActionListener(e -> hospital.reporteGeneral());
        footer.add(btnReporte);

        add(footer, BorderLayout.SOUTH);
    }

    private void agregarDoctor() {
        try {
            String n = txtDocNombre.getText().trim();
            String d = txtDocDni.getText().trim();
            String e = txtDocEspecialidad.getText().trim();
            if(n.isEmpty() || d.isEmpty()) throw new IllegalArgumentException();
            hospital.agregarDoctor(new Doctor(n, d, e));
            System.out.println("[+] Doctor(a) " + n + " (" + e + ") agregado exitosamente.");
            txtDocNombre.setText(""); txtDocDni.setText(""); txtDocEspecialidad.setText("");
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos en el formulario de Doctor.");
        }
    }

    private void agregarPaciente() {
        try {
            String n = txtPacNombre.getText().trim();
            String d = txtPacDni.getText().trim();
            String e = txtPacEnfermedad.getText().trim();
            if(n.isEmpty() || d.isEmpty()) throw new IllegalArgumentException();
            hospital.agregarPaciente(new Paciente(n, d, e));
            System.out.println("[+] Paciente " + n + " registrado exitosamente.");
            txtPacNombre.setText(""); txtPacDni.setText(""); txtPacEnfermedad.setText("");
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos en el formulario de Paciente.");
        }
    }

    private void asignarAleatorio() {
        try {
            System.out.println("\n[SISTEMA] Esta función requiere de listas enlazadas dinámicas que se verán en producción avanzada.");
            System.out.println("Por ahora, revise que la estructura del hospital sigue operativa desde consola central.");
        } catch(Exception ignored) {}
    }
}
