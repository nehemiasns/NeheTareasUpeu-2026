package com.nemastreet.controllers;

import com.nemastreet.App;
import com.nemastreet.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import java.io.File;
import java.util.Optional;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminController {
    // Tablas
    @FXML private TableView<Persona> tablePersonas;
    @FXML private TableView<Producto> tableProductos;
    @FXML private TableView<Proveedor> tableProveedores;
    @FXML private TableColumn<Producto, String> colImagen;
    @FXML private TableColumn<Producto, Double> colPrecio;
    @FXML private TableColumn<Persona, String> colRol;
    @FXML private TableColumn<Persona, Void> colAccionesUsu;
    @FXML private TableColumn<Producto, Void> colAccionesProd;
    @FXML private TableColumn<Proveedor, Void> colAccionesProv;

    // Listas observables
    private ObservableList<Persona> personasList = FXCollections.observableArrayList();
    private ObservableList<Producto> productosList = FXCollections.observableArrayList();
    private ObservableList<Proveedor> proveedoresList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        if (colImagen != null) {
            colImagen.setCellFactory(param -> new TableCell<Producto, String>() {
                private final ImageView imageView = new ImageView();
                @Override
                protected void updateItem(String url, boolean empty) {
                    super.updateItem(url, empty);
                    if (empty || url == null || url.isEmpty()) {
                        setGraphic(null);
                    } else {
                        Image image = new Image(url, 60, 60, true, true, true);
                        imageView.setImage(image);
                        setGraphic(imageView);
                    }
                }
            });
        }

        if (colRol != null) {
            colRol.setCellFactory(param -> new TableCell<Persona, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setGraphic(null);
                    } else {
                        Label lbl = new Label(item.toUpperCase());
                        if (item.equalsIgnoreCase("admin")) {
                            lbl.getStyleClass().add("pill-admin");
                        } else {
                            lbl.getStyleClass().add("pill-client");
                        }
                        setGraphic(lbl);
                    }
                }
            });
        }

        if (colAccionesUsu != null) {
            colAccionesUsu.setCellFactory(param -> new TableCell<Persona, Void>() {
                private final javafx.scene.control.Button btnDel = new javafx.scene.control.Button("🗑 Eliminar");
                {
                    btnDel.getStyleClass().add("btn-action-delete");
                    btnDel.setOnAction(e -> {
                        tablePersonas.getSelectionModel().select(getTableRow().getItem());
                        handleDeleteUsuario();
                    });
                }
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) setGraphic(null);
                    else {
                        javafx.scene.layout.HBox box = new javafx.scene.layout.HBox(10, btnDel);
                        box.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
                        setGraphic(box);
                    }
                }
            });
        }

        if (colAccionesProd != null) {
            colAccionesProd.setCellFactory(param -> new TableCell<Producto, Void>() {
                private final javafx.scene.control.Button btnEdit = new javafx.scene.control.Button("✏ Editar");
                private final javafx.scene.control.Button btnDel = new javafx.scene.control.Button("🗑 Eliminar");
                {
                    btnEdit.getStyleClass().add("btn-action-edit");
                    btnDel.getStyleClass().add("btn-action-delete");
                    btnEdit.setOnAction(e -> {
                        tableProductos.getSelectionModel().select(getTableRow().getItem());
                        handleEditProducto();
                    });
                    btnDel.setOnAction(e -> {
                        tableProductos.getSelectionModel().select(getTableRow().getItem());
                        handleDeleteProducto();
                    });
                }
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) setGraphic(null);
                    else {
                        javafx.scene.layout.HBox box = new javafx.scene.layout.HBox(10, btnEdit, btnDel);
                        box.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
                        setGraphic(box);
                    }
                }
            });
        }

        if (colAccionesProv != null) {
            colAccionesProv.setCellFactory(param -> new TableCell<Proveedor, Void>() {
                private final javafx.scene.control.Button btnDel = new javafx.scene.control.Button("🗑 Eliminar");
                {
                    btnDel.getStyleClass().add("btn-action-delete");
                    btnDel.setOnAction(e -> {
                        tableProveedores.getSelectionModel().select(getTableRow().getItem());
                        handleDeleteProveedor();
                    });
                }
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) setGraphic(null);
                    else {
                        javafx.scene.layout.HBox box = new javafx.scene.layout.HBox(10, btnDel);
                        box.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
                        setGraphic(box);
                    }
                }
            });
        }
        
        loadUsuarios();
        loadProductos();
        loadProveedores();
    }

    // --- MÉTODOS DE USUARIOS ---
    private void loadUsuarios() {
        personasList.clear();
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = Database.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String rol = rs.getString("rol");
                if ("Admin".equals(rol)) {
                    personasList.add(new Administrador(String.valueOf(rs.getInt("id")), rs.getString("nombre"), rs.getString("email"), rs.getString("password"), "SuperAdmin"));
                } else {
                    personasList.add(new Cliente(String.valueOf(rs.getInt("id")), rs.getString("nombre"), rs.getString("email"), rs.getString("password"), "Sin direccion"));
                }
            }
            tablePersonas.setItems(personasList);
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @FXML private void handleCreateUsuario() {
        TextInputDialog dialogNombre = new TextInputDialog();
        dialogNombre.getDialogPane().getStylesheets().add(getClass().getResource("/com/nemastreet/style.css").toExternalForm());
        dialogNombre.getDialogPane().getStyleClass().add("dialog-pane");
        dialogNombre.setTitle("Añadir Usuario");
        dialogNombre.setHeaderText("Introduzca el Nombre del Usuario:");
        Optional<String> resultNombre = dialogNombre.showAndWait();
        
        if (resultNombre.isPresent() && !resultNombre.get().trim().isEmpty()) {
            TextInputDialog dialogEmail = new TextInputDialog();
            dialogEmail.getDialogPane().getStylesheets().add(getClass().getResource("/com/nemastreet/style.css").toExternalForm());
            dialogEmail.getDialogPane().getStyleClass().add("dialog-pane");
            dialogEmail.setHeaderText("Correo Electrónico:");
            Optional<String> resultEmail = dialogEmail.showAndWait();
            
            if (resultEmail.isPresent() && !resultEmail.get().trim().isEmpty()) {
                TextInputDialog dialogPass = new TextInputDialog();
                dialogPass.getDialogPane().getStylesheets().add(getClass().getResource("/com/nemastreet/style.css").toExternalForm());
                dialogPass.getDialogPane().getStyleClass().add("dialog-pane");
                dialogPass.setHeaderText("Contraseña:");
                Optional<String> resultPass = dialogPass.showAndWait();

                if (resultPass.isPresent() && !resultPass.get().trim().isEmpty()) {
                    javafx.scene.control.ChoiceDialog<String> dialogRol = new javafx.scene.control.ChoiceDialog<>("Cliente", "Cliente", "Admin");
                    dialogRol.getDialogPane().getStylesheets().add(getClass().getResource("/com/nemastreet/style.css").toExternalForm());
                    dialogRol.getDialogPane().getStyleClass().add("dialog-pane");
                    dialogRol.setHeaderText("Seleccione el Rol del Usuario:");
                    Optional<String> resultRol = dialogRol.showAndWait();

                    if (resultRol.isPresent()) {
                        String sql = "INSERT INTO usuarios(nombre, email, password, rol) VALUES(?,?,?,?)";
                        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                            pstmt.setString(1, resultNombre.get());
                            pstmt.setString(2, resultEmail.get());
                            pstmt.setString(3, resultPass.get());
                            pstmt.setString(4, resultRol.get());
                            pstmt.executeUpdate();
                            loadUsuarios();
                        } catch (SQLException e) { e.printStackTrace(); }
                    }
                }
            }
        }
    }

    @FXML private void handleDeleteUsuario() {
        Persona p = tablePersonas.getSelectionModel().getSelectedItem();
        if (p != null) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Eliminación");
            alert.setHeaderText(null);
            alert.setContentText("¿Estás seguro de que quieres eliminar al usuario: " + p.getNombre() + "?");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/com/nemastreet/style.css").toExternalForm());
            alert.getDialogPane().getStyleClass().add("dialog-pane");
            
            Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == javafx.scene.control.ButtonType.OK) {
                String sql = "DELETE FROM usuarios WHERE id = ?";
                try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, Integer.parseInt(p.getId()));
                    pstmt.executeUpdate();
                    loadUsuarios();
                } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }

    // --- MÉTODOS DE INVENTARIO (PRODUCTOS) ---
    private void loadProductos() {
        productosList.clear();
        String sql = "SELECT * FROM productos";
        try (Connection conn = Database.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                productosList.add(new Producto(
                    String.valueOf(rs.getInt("id")),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"),
                    rs.getString("imagen_url"),
                    rs.getString("tallas") != null ? rs.getString("tallas") : "S, M, L",
                    rs.getString("colores") != null ? rs.getString("colores") : "Negro"
                ));
            }
            tableProductos.setItems(productosList);
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @FXML private void handleCreateProducto() {
        javafx.scene.control.Dialog<Producto> dialog = new javafx.scene.control.Dialog<>();
        dialog.setTitle("Añadir Prenda");
        dialog.setHeaderText("Introduzca los datos del nuevo producto");
        dialog.getDialogPane().getStylesheets().add(getClass().getResource("/com/nemastreet/style.css").toExternalForm());
        dialog.getDialogPane().getStyleClass().add("dialog-pane");

        javafx.scene.control.ButtonType createButtonType = new javafx.scene.control.ButtonType("Guardar", javafx.scene.control.ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(createButtonType, javafx.scene.control.ButtonType.CANCEL);

        javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        javafx.scene.control.TextField nombre = new javafx.scene.control.TextField();
        nombre.setPromptText("Ej. Sudadera Oversize");
        javafx.scene.control.TextField precio = new javafx.scene.control.TextField();
        precio.setPromptText("Ej. 149.99");
        javafx.scene.control.TextField stock = new javafx.scene.control.TextField("10");
        javafx.scene.control.TextField tallas = new javafx.scene.control.TextField("S, M, L, XL");
        javafx.scene.control.TextField colores = new javafx.scene.control.TextField("Negro, Blanco");
        javafx.scene.control.Button btnImagen = new javafx.scene.control.Button("Seleccionar Imagen");
        final String[] imagenSeleccionada = new String[]{""};

        btnImagen.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar Imagen");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                imagenSeleccionada[0] = file.toURI().toString();
                btnImagen.setText("Imagen Cargada");
            }
        });

        grid.add(new Label("Nombre:"), 0, 0); grid.add(nombre, 1, 0);
        grid.add(new Label("Precio (S/.):"), 0, 1); grid.add(precio, 1, 1);
        grid.add(new Label("Stock:"), 0, 2); grid.add(stock, 1, 2);
        grid.add(new Label("Tallas (coma):"), 0, 3); grid.add(tallas, 1, 3);
        grid.add(new Label("Colores (coma):"), 0, 4); grid.add(colores, 1, 4);
        grid.add(new Label("Imagen:"), 0, 5); grid.add(btnImagen, 1, 5);

        dialog.getDialogPane().setContent(grid);
        
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == createButtonType) {
                return new Producto("0", nombre.getText(), Double.parseDouble(precio.getText()), Integer.parseInt(stock.getText()), imagenSeleccionada[0], tallas.getText(), colores.getText());
            }
            return null;
        });

        Optional<Producto> result = dialog.showAndWait();
        result.ifPresent(p -> {
            String sql = "INSERT INTO productos(nombre, precio, stock, imagen_url, tallas, colores) VALUES(?,?,?,?,?,?)";
            try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, p.getNombre());
                pstmt.setDouble(2, p.getPrecio());
                pstmt.setInt(3, p.getStock());
                pstmt.setString(4, p.getImagenUrl());
                pstmt.setString(5, p.getTallas());
                pstmt.setString(6, p.getColores());
                pstmt.executeUpdate();
                loadProductos();
            } catch (SQLException e) { e.printStackTrace(); }
        });
    }

    @FXML private void handleEditProducto() {
        Producto pSelected = tableProductos.getSelectionModel().getSelectedItem();
        if (pSelected != null) {
            javafx.scene.control.Dialog<Producto> dialog = new javafx.scene.control.Dialog<>();
            dialog.setTitle("Editar Prenda");
            dialog.setHeaderText("Modifique los datos del producto");
            dialog.getDialogPane().getStylesheets().add(getClass().getResource("/com/nemastreet/style.css").toExternalForm());
            dialog.getDialogPane().getStyleClass().add("dialog-pane");

            javafx.scene.control.ButtonType saveButtonType = new javafx.scene.control.ButtonType("Actualizar", javafx.scene.control.ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, javafx.scene.control.ButtonType.CANCEL);

            javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

            javafx.scene.control.TextField nombre = new javafx.scene.control.TextField(pSelected.getNombre());
            javafx.scene.control.TextField precio = new javafx.scene.control.TextField(String.valueOf(pSelected.getPrecio()));
            javafx.scene.control.TextField stock = new javafx.scene.control.TextField(String.valueOf(pSelected.getStock()));
            javafx.scene.control.TextField tallas = new javafx.scene.control.TextField(pSelected.getTallas());
            javafx.scene.control.TextField colores = new javafx.scene.control.TextField(pSelected.getColores());

            grid.add(new Label("Nombre:"), 0, 0); grid.add(nombre, 1, 0);
            grid.add(new Label("Precio (S/.):"), 0, 1); grid.add(precio, 1, 1);
            grid.add(new Label("Stock:"), 0, 2); grid.add(stock, 1, 2);
            grid.add(new Label("Tallas (coma):"), 0, 3); grid.add(tallas, 1, 3);
            grid.add(new Label("Colores (coma):"), 0, 4); grid.add(colores, 1, 4);

            dialog.getDialogPane().setContent(grid);
            
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == saveButtonType) {
                    return new Producto(pSelected.getId(), nombre.getText(), Double.parseDouble(precio.getText()), Integer.parseInt(stock.getText()), pSelected.getImagenUrl(), tallas.getText(), colores.getText());
                }
                return null;
            });

            Optional<Producto> result = dialog.showAndWait();
            result.ifPresent(p -> {
                String sql = "UPDATE productos SET nombre = ?, precio = ?, stock = ?, tallas = ?, colores = ? WHERE id = ?";
                try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, p.getNombre());
                    pstmt.setDouble(2, p.getPrecio());
                    pstmt.setInt(3, p.getStock());
                    pstmt.setString(4, p.getTallas());
                    pstmt.setString(5, p.getColores());
                    pstmt.setInt(6, Integer.parseInt(p.getId()));
                    pstmt.executeUpdate();
                    loadProductos();
                } catch (SQLException e) { e.printStackTrace(); }
            });
        }
    }

    @FXML private void handleDeleteProducto() {
        Producto p = tableProductos.getSelectionModel().getSelectedItem();
        if (p != null) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Eliminación");
            alert.setHeaderText(null);
            alert.setContentText("¿Estás seguro de que quieres eliminar la prenda: " + p.getNombre() + "?");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/com/nemastreet/style.css").toExternalForm());
            alert.getDialogPane().getStyleClass().add("dialog-pane");
            
            Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == javafx.scene.control.ButtonType.OK) {
                String sql = "DELETE FROM productos WHERE id = ?";
                try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, Integer.parseInt(p.getId()));
                    pstmt.executeUpdate();
                    loadProductos();
                } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }

    // --- MÉTODOS DE PROVEEDORES ---
    private void loadProveedores() {
        proveedoresList.clear();
        String sql = "SELECT * FROM proveedores";
        try (Connection conn = Database.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                proveedoresList.add(new Proveedor(
                    String.valueOf(rs.getInt("id")),
                    rs.getString("nombre_empresa"),
                    rs.getString("contacto"),
                    rs.getString("telefono")
                ));
            }
            tableProveedores.setItems(proveedoresList);
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @FXML private void handleCreateProveedor() {
        TextInputDialog dialogEmpresa = new TextInputDialog();
        dialogEmpresa.getDialogPane().getStylesheets().add(getClass().getResource("/com/nemastreet/style.css").toExternalForm());
        dialogEmpresa.getDialogPane().getStyleClass().add("dialog-pane");
        dialogEmpresa.setTitle("Añadir Proveedor");
        dialogEmpresa.setHeaderText("Nombre de la Empresa:");
        Optional<String> resultEmpresa = dialogEmpresa.showAndWait();
        
        if (resultEmpresa.isPresent() && !resultEmpresa.get().trim().isEmpty()) {
            TextInputDialog dialogContacto = new TextInputDialog();
            dialogContacto.getDialogPane().getStylesheets().add(getClass().getResource("/com/nemastreet/style.css").toExternalForm());
            dialogContacto.getDialogPane().getStyleClass().add("dialog-pane");
            dialogContacto.setHeaderText("Nombre del Contacto:");
            Optional<String> resultContacto = dialogContacto.showAndWait();

            if (resultContacto.isPresent() && !resultContacto.get().trim().isEmpty()) {
                TextInputDialog dialogTel = new TextInputDialog();
                dialogTel.getDialogPane().getStylesheets().add(getClass().getResource("/com/nemastreet/style.css").toExternalForm());
                dialogTel.getDialogPane().getStyleClass().add("dialog-pane");
                dialogTel.setHeaderText("Teléfono:");
                Optional<String> resultTel = dialogTel.showAndWait();

                if (resultTel.isPresent() && !resultTel.get().trim().isEmpty()) {
                    String sql = "INSERT INTO proveedores(nombre_empresa, contacto, telefono) VALUES(?,?,?)";
                    try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setString(1, resultEmpresa.get());
                        pstmt.setString(2, resultContacto.get());
                        pstmt.setString(3, resultTel.get());
                        pstmt.executeUpdate();
                        loadProveedores();
                    } catch (SQLException e) { e.printStackTrace(); }
                }
            }
        }
    }

    @FXML private void handleDeleteProveedor() {
        Proveedor p = tableProveedores.getSelectionModel().getSelectedItem();
        if (p != null) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Eliminación");
            alert.setHeaderText(null);
            alert.setContentText("¿Estás seguro de que quieres eliminar al proveedor: " + p.getNombreEmpresa() + "?");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/com/nemastreet/style.css").toExternalForm());
            alert.getDialogPane().getStyleClass().add("dialog-pane");
            
            Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == javafx.scene.control.ButtonType.OK) {
                String sql = "DELETE FROM proveedores WHERE id = ?";
                try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, Integer.parseInt(p.getId()));
                    pstmt.executeUpdate();
                    loadProveedores();
                } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }

    @FXML private void logout() throws IOException {
        App.setRoot("login");
    }
}
