package FACIL.Ejercicio05_Tienda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TiendaGUI extends JFrame {
    private JTextField txtNombre, txtPrecio, txtStock;
    private DefaultTableModel tableModel;
    private TiendaInteractiva tiendaLogica;

    public TiendaGUI() {
        super("SISTEMA DE INVENTARIO - TIENDA");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Nombre del Producto:"));
        txtNombre = new JTextField();
        formPanel.add(txtNombre);

        formPanel.add(new JLabel("Precio $:"));
        txtPrecio = new JTextField();
        formPanel.add(txtPrecio);

        formPanel.add(new JLabel("Cantidad en Stock:"));
        txtStock = new JTextField();
        formPanel.add(txtStock);

        JButton btnRegistrar = new JButton("Ingresar a Inventario");
        btnRegistrar.addActionListener(e -> agregarProducto());
        
        formPanel.add(new JLabel());
        formPanel.add(btnRegistrar);

        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"ID Producto", "Nombre", "Precio ($)", "Stock Total"}, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        JButton btnCargarPrueba = new JButton("Cargar Datos Prueba");
        btnCargarPrueba.addActionListener(e -> cargarPrueba());
        footerPanel.add(btnCargarPrueba);
        add(footerPanel, BorderLayout.SOUTH);
    }

    private void agregarProducto() {
        try {
            String nombre = txtNombre.getText().trim();
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            int stock = Integer.parseInt(txtStock.getText().trim());

            if(nombre.isEmpty()) throw new IllegalArgumentException("Nombre vacío");

            Producto p = new Producto(nombre, precio, "General", stock);
            tableModel.addRow(new Object[]{p.getCategoria(), p.getNombre(), p.getPrecio(), p.getStock()});
            
            txtNombre.setText(""); txtPrecio.setText(""); txtStock.setText("");
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos. Verifique campos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarPrueba() {
        Producto p1 = new Producto("Laptop Samsung", 1500.0, "Tecnología", 10);
        Producto p2 = new Producto("Mouse Gaming", 25.5, "Accesorios", 50);
        tableModel.addRow(new Object[]{p1.getCategoria(), p1.getNombre(), p1.getPrecio(), p1.getStock()});
        tableModel.addRow(new Object[]{p2.getCategoria(), p2.getNombre(), p2.getPrecio(), p2.getStock()});
    }
}
