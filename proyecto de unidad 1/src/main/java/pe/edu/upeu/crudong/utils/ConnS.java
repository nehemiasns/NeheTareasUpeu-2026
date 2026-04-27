package pe.edu.upeu.crudong.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnS {
    private static final String URL = "jdbc:sqlite:db_ong.db";
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL);
                initializeDatabase();
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return connection;
    }

    private static void initializeDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS organizaciones (" +
                     "id TEXT PRIMARY KEY," +
                     "nombre TEXT NOT NULL," +
                     "numero_acta TEXT," +
                     "representante_legal TEXT," +
                     "objeto_social TEXT," +
                     "vigencia TEXT" +
                     ");";
        try (Statement stmt = getConnection().createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }
}
