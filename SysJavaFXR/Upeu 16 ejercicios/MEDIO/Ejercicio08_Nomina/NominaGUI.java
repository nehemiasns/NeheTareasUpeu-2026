package MEDIO.Ejercicio08_Nomina;

import UI.ConsolePanel;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NominaGUI extends JFrame {
    private ArrayList<Empleado> empleados;
    private ConsolePanel console;
    private JTextField txtNombre, txtSueldoHoras, txtBonoTarifa;

    public NominaGUI() {
        super("SISTEMA DE NÓMINA (TIPO DE EMPLEADOS)");
        empleados = new ArrayList<>();
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Nombre Empleado:"));
        txtNombre = new JTextField();
        formPanel.add(txtNombre);

        formPanel.add(new JLabel("Sueldo Base (Fijo) / Horas Trabajadas (Temporal):"));
        txtSueldoHoras = new JTextField();
        formPanel.add(txtSueldoHoras);

        formPanel.add(new JLabel("Bono (Fijo) / Tarifa por Hora (Temporal):"));
        txtBonoTarifa = new JTextField();
        formPanel.add(txtBonoTarifa);

        formPanel.add(new JLabel("Registrar Contrato:"));
        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        
        JButton btnFijo = new JButton("Empleado Fijo");
        btnFijo.addActionListener(e -> agregarEmpleado(true));
        
        JButton btnTemporal = new JButton("Empleado Temporal");
        btnTemporal.addActionListener(e -> agregarEmpleado(false));

        btnPanel.add(btnFijo); btnPanel.add(btnTemporal);
        formPanel.add(btnPanel);
        
        add(formPanel, BorderLayout.NORTH);

        console = new ConsolePanel();
        add(console, BorderLayout.CENTER);

        JPanel footer = new JPanel();
        JButton btnListar = new JButton("CALCULAR NÓMINA COMPLETA");
        btnListar.addActionListener(e -> calcularNomina());
        footer.add(btnListar);
        add(footer, BorderLayout.SOUTH);
    }

    private void agregarEmpleado(boolean esFijo) {
        try {
            String nombre = txtNombre.getText().trim();
            double val1 = Double.parseDouble(txtSueldoHoras.getText().trim());
            double val2 = Double.parseDouble(txtBonoTarifa.getText().trim());
            
            if(nombre.isEmpty()) throw new IllegalArgumentException();

            String id = "EMP-" + (int)(Math.random()*(9999-1000)+1000);

            if(esFijo) {
                empleados.add(new EmpleadoFijo(nombre, id, val1));
            } else {
                empleados.add(new EmpleadoTemporal(nombre, id, (int) val1, val2));
            }
            
            System.out.println("[+] Empleado contratado: " + nombre);
            txtNombre.setText(""); txtSueldoHoras.setText(""); txtBonoTarifa.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Asegúrate de rellenar bien los campos numéricos.");
        }
    }

    private void calcularNomina() {
        System.out.println("\n=== REPORTE DE NÓMINA ===");
        if(empleados.isEmpty()) {
            System.out.println("No hay empleados en el sistema.");
            return;
        }
        
        double totalGlobal = 0;
        for (Empleado e : empleados) {
            double salario = e.calcularPago();
            System.out.printf("Empleado: %s | Pago Final: $%.2f%n", e.getNombre(), salario);
            totalGlobal += salario;
        }
        System.out.printf(">>> Total Desembolso de Nómina: $%.2f%n", totalGlobal);
        System.out.println("-------------------------");
    }
}
