package DIFICIL.Ejercicio15_Excepciones;

import UI.ConsolePanel;
import javax.swing.*;
import java.awt.*;

public class ExcepcionesGUI extends JFrame {
    private ListaLimitada lista;
    private ConsolePanel console;
    private JTextField txtLimite, txtElemento;
    private JButton btnEstablecer, btnAgregar;

    public ExcepcionesGUI() {
        super("SISTEMA DE EXCEPCIONES Y DESBORDAMIENTOS");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Capacidad Máxima Numérica:"));
        txtLimite = new JTextField("3");
        formPanel.add(txtLimite);
        
        btnEstablecer = new JButton("Establecer Límite");
        btnEstablecer.addActionListener(e -> establecer());
        formPanel.add(btnEstablecer);

        formPanel.add(new JLabel("Elemento a inyectar:"));
        txtElemento = new JTextField();
        formPanel.add(txtElemento);

        btnAgregar = new JButton("Inyectar (Puede Fallar)");
        btnAgregar.addActionListener(e -> agregarElemento());
        btnAgregar.setEnabled(false);
        formPanel.add(btnAgregar);

        add(formPanel, BorderLayout.NORTH);

        console = new ConsolePanel();
        add(console, BorderLayout.CENTER);
        
        JPanel footer = new JPanel();
        JButton btnListar = new JButton("Ver Contenido de la Lista");
        btnListar.addActionListener(e -> listar());
        footer.add(btnListar);
        
        JButton btnReiniciar = new JButton("Reiniciar Laboratorio");
        btnReiniciar.addActionListener(e -> {
            lista = null;
            btnEstablecer.setEnabled(true);
            btnAgregar.setEnabled(false);
            console.clear();
            System.out.println("--- Laboratorio Limpiado ---");
        });
        footer.add(btnReiniciar);
        add(footer, BorderLayout.SOUTH);
    }

    private void establecer() {
        try {
            int max = Integer.parseInt(txtLimite.getText().trim());
            lista = new ListaLimitada(max);
            System.out.println("[ok] Lista creada con límite seguro de: " + max);
            btnEstablecer.setEnabled(false);
            btnAgregar.setEnabled(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Debe ser un número entero válido.");
        }
    }

    private void agregarElemento() {
        String data = txtElemento.getText().trim();
        if(data.isEmpty()) return;

        try {
            lista.agregar(data);
            System.out.println("[+] " + data + " insertado.");
            txtElemento.setText("");
        } catch (CapacidadExcedidaException customException) {
            JOptionPane.showMessageDialog(this, "EXCEPCIÓN ATRAPADA:\n" + customException.getMessage(), "Error de Desbordamiento", JOptionPane.ERROR_MESSAGE);
            System.out.println("[ERROR FATAL] " + customException.getMessage());
        }
    }

    private void listar() {
        if(lista == null) return;
        System.out.print("\n=== CONTENIDO ACTUAL ===\n[ ");
        for(int i = 0; i < lista.getTamano(); i++) {
            System.out.print(lista.obtener(i) + " ");
        }
        System.out.println("]\n");
    }
}
