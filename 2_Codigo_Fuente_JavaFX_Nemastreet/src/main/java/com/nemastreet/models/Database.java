package com.nemastreet.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:sqlite:nemastreet.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void init() {
        String sqlUsuarios = "CREATE TABLE IF NOT EXISTS usuarios (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " nombre TEXT NOT NULL,\n"
                + " email TEXT NOT NULL,\n"
                + " password TEXT NOT NULL,\n"
                + " rol TEXT NOT NULL\n"
                + ");";
                
        String sqlProductos = "CREATE TABLE IF NOT EXISTS productos (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " nombre TEXT NOT NULL,\n"
                + " precio REAL NOT NULL,\n"
                + " stock INTEGER NOT NULL,\n"
                + " imagen_url TEXT NOT NULL,\n"
                + " tallas TEXT DEFAULT 'S, M, L',\n"
                + " colores TEXT DEFAULT 'Negro'\n"
                + ");";
                
        String sqlProveedores = "CREATE TABLE IF NOT EXISTS proveedores (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " nombre_empresa TEXT NOT NULL,\n"
                + " contacto TEXT NOT NULL,\n"
                + " telefono TEXT NOT NULL\n"
                + ");";
        
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlUsuarios);
            stmt.execute(sqlProductos);
            stmt.execute(sqlProveedores);
            System.out.println("Base de datos inicializada correctamente.");
            
            // Insertar admin por defecto si la tabla está vacía
            String checkAdmin = "INSERT INTO usuarios (nombre, email, password, rol) " +
                                "SELECT 'admin', 'admin@nema.com', '123', 'Admin' " +
                                "WHERE NOT EXISTS (SELECT 1 FROM usuarios WHERE nombre = 'admin')";
            stmt.execute(checkAdmin);
            
            // Insertar cliente por defecto si la tabla está vacía
            String checkClient = "INSERT INTO usuarios (nombre, email, password, rol) " +
                                "SELECT 'cliente', 'cliente@nema.com', '123', 'Cliente' " +
                                "WHERE NOT EXISTS (SELECT 1 FROM usuarios WHERE nombre = 'cliente')";
            stmt.execute(checkClient);
            
            // Intentar añadir las nuevas columnas si la tabla ya existía (ignorando error si ya existen)
            try { stmt.execute("ALTER TABLE productos ADD COLUMN tallas TEXT DEFAULT 'S, M, L'"); } catch (SQLException e) {}
            try { stmt.execute("ALTER TABLE productos ADD COLUMN colores TEXT DEFAULT 'Negro'"); } catch (SQLException e) {}
            
            // Comprobar si ya existen productos, si no, insertar 30 prendas
            ResultSet rsProd = stmt.executeQuery("SELECT COUNT(*) AS count FROM productos");
            int prodCount = rsProd.next() ? rsProd.getInt("count") : 0;
            
            if (prodCount == 0) {
                // 12 Prendas exclusivas, cada una con su imagen única generada por IA
                String[] prendas = {
                    "Camiseta Boxy Fit Blanca",
                    "Camiseta Gráfica Skull Negra",
                    "Camiseta Vintage Wash Grey",
                    "Camiseta Beige Oversize",
                    "Camiseta Anime White",
                    "Pantalón Cargo Olive",
                    "Jeans Baggy Skater Light Blue",
                    "Joggers Tactical Black",
                    "Hoodie Essential Gris",
                    "Hoodie Gothic Zip-Up",
                    "Chaqueta Puffer Matte",
                    "Chaqueta Denim Vintage"
                };
                
                String[] imagenes = {
                    "/com/nemastreet/images/tshirt_1.png",
                    "/com/nemastreet/images/tshirt_2.png",
                    "/com/nemastreet/images/tshirt_3.png",
                    "/com/nemastreet/images/tshirt_4.png",
                    "/com/nemastreet/images/tshirt_5.png",
                    "/com/nemastreet/images/pants_1.png",
                    "/com/nemastreet/images/pants_2.png",
                    "/com/nemastreet/images/pants_3.png",
                    "/com/nemastreet/images/hoodie_1.png",
                    "/com/nemastreet/images/hoodie_2.png",
                    "/com/nemastreet/images/jacket_1.png",
                    "/com/nemastreet/images/jacket_2.png"
                };
                
                String insertSQL = "INSERT INTO productos (nombre, precio, stock, imagen_url, tallas, colores) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    for (int i = 0; i < prendas.length; i++) {
                        pstmt.setString(1, prendas[i]);
                        double precioExacto = Math.floor(80.0 + (Math.random() * 80.0));
                        pstmt.setDouble(2, precioExacto); 
                        pstmt.setInt(3, 10 + (int)(Math.random() * 40));   
                        pstmt.setString(4, Database.class.getResource(imagenes[i]).toExternalForm());
                        pstmt.setString(5, "S, M, L, XL");
                        pstmt.setString(6, "Negro, Blanco, Gris, Beige");
                        pstmt.executeUpdate();
                    }
                }
            }
                         
            // Insertar proveedores por defecto si la tabla está vacía
            String checkProveedores = "INSERT INTO proveedores (nombre_empresa, contacto, telefono) " +
                                "SELECT 'Streetwear Supply Co.', 'Carlos Gomez', '+123456789' " +
                                "WHERE NOT EXISTS (SELECT 1 FROM proveedores);";
            stmt.execute(checkProveedores);

        } catch (SQLException e) {
            System.out.println("Error inicializando la base de datos: " + e.getMessage());
        }
    }
}
