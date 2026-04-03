package MEDIO.Ejercicio07_Biblioteca;

import UI.ConsolePanel;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BibliotecaGUI extends JFrame {
    private ArrayList<Libro> inventario;
    private ConsolePanel console;
    private JTextField txtTitulo, txtAutor, txtDatoExtra;

    public BibliotecaGUI() {
        super("SISTEMA DE BIBLIOTECA (Herencia)");
        inventario = new ArrayList<>();
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Título del Libro:"));
        txtTitulo = new JTextField();
        formPanel.add(txtTitulo);

        formPanel.add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        formPanel.add(txtAutor);

        formPanel.add(new JLabel("Dato Extra (mb para Digital / gramos para Físico):"));
        txtDatoExtra = new JTextField();
        formPanel.add(txtDatoExtra);

        formPanel.add(new JLabel("Agregar Libro Físico/Digital (Precio como dato extra):"));
        JPanel btnPanel = new JPanel(new GridLayout(1, 1, 5, 5));
        
        JButton btnAgregar = new JButton("Agregar Libro");
        btnAgregar.addActionListener(e -> agregarLibro());

        btnPanel.add(btnAgregar);
        formPanel.add(btnPanel);
        
        add(formPanel, BorderLayout.NORTH);

        console = new ConsolePanel();
        add(console, BorderLayout.CENTER);

        JPanel footer = new JPanel();
        JButton btnListar = new JButton("MOSTRAR TODOS LOS LIBROS");
        btnListar.addActionListener(e -> mostrarLibros());
        footer.add(btnListar);
        
        JButton btnClear = new JButton("Limpiar Consola");
        btnClear.addActionListener(e -> console.clear());
        footer.add(btnClear);
        add(footer, BorderLayout.SOUTH);
    }

    private void agregarLibro() {
        try {
            String titulo = txtTitulo.getText().trim();
            String autor = txtAutor.getText().trim();
            String isbn = "ISBN-" + (int)(Math.random() * 1000);
            double precio = Double.parseDouble(txtDatoExtra.getText().trim());
            
            if(titulo.isEmpty() || autor.isEmpty()) throw new IllegalArgumentException();

            inventario.add(new Libro(titulo, autor, isbn, precio));
            
            System.out.println("[+] Libro registrado exitosamente: " + titulo);
            txtTitulo.setText(""); txtAutor.setText(""); txtDatoExtra.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos. Verifica que el precio sea numérico.");
        }
    }

    private void mostrarLibros() {
        System.out.println("\n=== INVENTARIO DE BIBLIOTECA ===");
        if(inventario.isEmpty()) {
            System.out.println("La biblioteca está vacía.");
            return;
        }
        
        for (Libro l : inventario) {
            System.out.println(l.toString());
            System.out.println("---");
        }
    }
}
