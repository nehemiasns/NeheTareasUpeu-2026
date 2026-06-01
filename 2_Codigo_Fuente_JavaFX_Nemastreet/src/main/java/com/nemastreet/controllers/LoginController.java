package com.nemastreet.controllers;

import com.nemastreet.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;

public class LoginController {
    @FXML private TextField txtUser;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblError;

    @FXML
    private void login() throws IOException {
        String user = txtUser.getText().trim().toLowerCase();
        String pass = txtPassword.getText().trim();

        String query = "SELECT rol FROM usuarios WHERE LOWER(nombre) = ? AND password = ?";
        
        try (java.sql.Connection conn = com.nemastreet.models.Database.connect();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            java.sql.ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String rol = rs.getString("rol");
                if ("Admin".equals(rol)) {
                    App.setRoot("admin");
                } else {
                    App.setRoot("client");
                }
            } else {
                lblError.setText("Credenciales incorrectas");
            }
        } catch (java.sql.SQLException e) {
            lblError.setText("Error de base de datos: " + e.getMessage());
        }
    }
}
