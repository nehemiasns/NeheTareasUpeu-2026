package com.nemastreet.controllers;

import com.nemastreet.App;
import com.nemastreet.models.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientController {

    @FXML
    private FlowPane catalogFlowPane;
    
    @FXML
    private Button btnCart;
    
    // Lista para guardar las prendas seleccionadas
    private List<Integer> cartProductIds = new ArrayList<>();
    private List<String> cartNames = new ArrayList<>();
    private List<Double> cartPrices = new ArrayList<>();
    private List<String> cartImages = new ArrayList<>();
    
    // Caché de imágenes para optimización extrema
    private static final java.util.Map<String, javafx.scene.image.Image> imageCache = new java.util.HashMap<>();

    @FXML private javafx.scene.control.TextField searchField;
    @FXML private javafx.scene.control.ComboBox<String> comboSort;
    
    @FXML private Label lblTodo;
    @FXML private Label lblSudaderas;
    @FXML private Label lblCamisetas;
    @FXML private Label lblChaquetas;
    @FXML private Label lblNovedades;
    
    @FXML private javafx.scene.control.ComboBox<String> sideComboTalla;
    @FXML private javafx.scene.control.ComboBox<String> sideComboColor;
    @FXML private javafx.scene.control.ComboBox<String> sideComboPrecio;
    @FXML private javafx.scene.control.ComboBox<String> topComboTalla;
    @FXML private javafx.scene.control.ComboBox<String> topComboColor;
    
    // Variables de Estado de Filtros
    private String currentCategory = "";
    private String currentSearch = "";
    private String currentSort = "";
    private String currentFilterColor = "";
    private String currentFilterPrecio = "";
    private String currentFilterTalla = "";

    @FXML
    public void initialize() {
        // Inicializar opciones de los selectores de Talla, Color y Precio
        if (sideComboTalla != null) {
            sideComboTalla.getItems().addAll("Cualquier Talla", "S", "M", "L", "XL", "XXL");
            sideComboTalla.setOnAction(e -> {
                currentFilterTalla = sideComboTalla.getValue();
                if (topComboTalla != null) topComboTalla.setValue(currentFilterTalla);
                loadCatalogWithFilters(); // Actualiza en tiempo real
            });
        }
        if (topComboTalla != null) {
            topComboTalla.getItems().addAll("Cualquier Talla", "S", "M", "L", "XL", "XXL");
            topComboTalla.setOnAction(e -> {
                currentFilterTalla = topComboTalla.getValue();
                if (sideComboTalla != null) sideComboTalla.setValue(currentFilterTalla);
                loadCatalogWithFilters(); // Actualiza en tiempo real
            });
        }

        if (sideComboColor != null) {
            sideComboColor.getItems().addAll("Cualquier Color", "Negro", "Blanco", "Gris", "Beige", "Rojo", "Azul", "Marrón");
            sideComboColor.setOnAction(e -> {
                currentFilterColor = sideComboColor.getValue();
                if (topComboColor != null) topComboColor.setValue(currentFilterColor);
                loadCatalogWithFilters(); // Actualiza en tiempo real
            });
        }
        if (topComboColor != null) {
            topComboColor.getItems().addAll("Cualquier Color", "Negro", "Blanco", "Gris", "Beige", "Rojo", "Azul", "Marrón");
            topComboColor.setOnAction(e -> {
                currentFilterColor = topComboColor.getValue();
                if (sideComboColor != null) sideComboColor.setValue(currentFilterColor);
                loadCatalogWithFilters(); // Actualiza en tiempo real
            });
        }

        if (sideComboPrecio != null) {
            sideComboPrecio.getItems().addAll("Cualquier Precio", "S/. 0 - 80", "S/. 80 - 120", "S/. 120 - 150", "S/. 150+");
            sideComboPrecio.setOnAction(e -> {
                currentFilterPrecio = sideComboPrecio.getValue();
                loadCatalogWithFilters(); // Actualiza en tiempo real
            });
        }

        // Configurar el ComboBox de orden
        if (comboSort != null) {
            comboSort.getItems().addAll("Precio: Menor a Mayor", "Precio: Mayor a Menor");
            comboSort.setOnAction(e -> {
                String sortVal = comboSort.getValue();
                if (sortVal != null) {
                    if (sortVal.equals("Precio: Menor a Mayor")) currentSort = "ASC";
                    else if (sortVal.equals("Precio: Mayor a Menor")) currentSort = "DESC";
                    loadCatalogWithFilters();
                }
            });
        }
        
        // Configurar el buscador en tiempo real
        if (searchField != null) {
            searchField.textProperty().addListener((obs, oldText, newText) -> {
                currentSearch = newText;
                loadCatalogWithFilters();
            });
        }
        
        loadCatalogWithFilters();
    }
    
    private void updateActiveLabel(Label activeLabel) {
        if (lblTodo != null) lblTodo.getStyleClass().remove("sidebar-item-active");
        if (lblSudaderas != null) lblSudaderas.getStyleClass().remove("sidebar-item-active");
        if (lblCamisetas != null) lblCamisetas.getStyleClass().remove("sidebar-item-active");
        if (lblChaquetas != null) lblChaquetas.getStyleClass().remove("sidebar-item-active");
        if (lblNovedades != null) lblNovedades.getStyleClass().remove("sidebar-item-active");
        
        if (activeLabel != null) {
            activeLabel.getStyleClass().add("sidebar-item-active");
        }
    }

    @FXML private void filterTodo() { currentCategory = ""; updateActiveLabel(lblTodo); loadCatalogWithFilters(); }
    @FXML private void filterSudaderas() { currentCategory = "Sudadera"; updateActiveLabel(lblSudaderas); loadCatalogWithFilters(); }
    @FXML private void filterCamisetas() { currentCategory = "Camiseta"; updateActiveLabel(lblCamisetas); loadCatalogWithFilters(); }
    @FXML private void filterChaquetas() { currentCategory = "Chaqueta"; updateActiveLabel(lblChaquetas); loadCatalogWithFilters(); }
    @FXML private void filterNovedades() { currentCategory = "Nuevo"; updateActiveLabel(lblNovedades); loadCatalogWithFilters(); }

    private void loadCatalogWithFilters() {
        if (catalogFlowPane == null) return;
        catalogFlowPane.getChildren().clear();
        
        // PANTALLA DE CARGA MODERNA
        VBox loadingBox = new VBox(15);
        loadingBox.setAlignment(Pos.CENTER);
        loadingBox.setPrefWidth(800);
        loadingBox.setPrefHeight(400);
        
        javafx.scene.control.ProgressIndicator spinner = new javafx.scene.control.ProgressIndicator();
        spinner.setStyle("-fx-progress-color: #ff2a2a;");
        spinner.setMaxSize(50, 50);
        
        Label lblLoading = new Label("S I N C R O N I Z A N D O . . .");
        lblLoading.setStyle("-fx-text-fill: #8b92a5; -fx-font-size: 16px; -fx-font-weight: bold;");
        
        loadingBox.getChildren().addAll(spinner, lblLoading);
        catalogFlowPane.getChildren().add(loadingBox);

        // Capturar los valores actuales de los filtros
        final String cat = currentCategory;
        final String search = currentSearch;
        final String color = currentFilterColor;
        final String precioFilter = currentFilterPrecio;
        final String sort = currentSort;
        final String talla = currentFilterTalla;

        new Thread(() -> {
            try {
                // Pequeño retraso visual de medio segundo para dar un efecto fluido y premium
                Thread.sleep(500);
                
                List<Object[]> dataList = new ArrayList<>();
                
                // Construcción dinámica de la consulta SQL
                String sql = "SELECT * FROM productos WHERE 1=1";
                
                if (!cat.isEmpty()) sql += " AND nombre LIKE ?";
                if (!search.isEmpty()) sql += " AND nombre LIKE ?";
                if (color != null && !color.isEmpty() && !color.equals("Cualquier Color")) sql += " AND nombre LIKE ?";
                if (precioFilter != null && !precioFilter.isEmpty() && !precioFilter.equals("Cualquier Precio")) {
                    if (precioFilter.equals("S/. 0 - 80")) sql += " AND precio <= 80";
                    else if (precioFilter.equals("S/. 80 - 120")) sql += " AND precio > 80 AND precio <= 120";
                    else if (precioFilter.equals("S/. 120 - 150")) sql += " AND precio > 120 AND precio <= 150";
                    else if (precioFilter.equals("S/. 150+")) sql += " AND precio > 150";
                }
                if (!sort.isEmpty()) sql += " ORDER BY precio " + sort;
                
                try (Connection conn = Database.connect();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    
                    int paramIndex = 1;
                    if (!cat.isEmpty()) pstmt.setString(paramIndex++, "%" + cat + "%");
                    if (!search.isEmpty()) pstmt.setString(paramIndex++, "%" + search + "%");
                    if (color != null && !color.isEmpty() && !color.equals("Cualquier Color")) {
                        String colorSearch = color;
                        if (colorSearch.endsWith("o") || colorSearch.endsWith("a")) {
                            colorSearch = colorSearch.substring(0, colorSearch.length() - 1);
                        }
                        pstmt.setString(paramIndex++, "%" + colorSearch + "%");
                    }
                    
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        dataList.add(new Object[]{
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getDouble("precio"),
                            rs.getString("imagen_url"),
                            rs.getString("tallas") != null ? rs.getString("tallas") : "S, M, L",
                            rs.getString("colores") != null ? rs.getString("colores") : "Negro"
                        });
                    }
                }
                
                // Volver al Hilo Principal de la Interfaz (UI Thread) para actualizar las tarjetas
                javafx.application.Platform.runLater(() -> {
                    catalogFlowPane.getChildren().clear();
                    
                    for (Object[] data : dataList) {
                        int id = (Integer) data[0];
                        String nombre = (String) data[1];
                        double precio = (Double) data[2];
                        String imgUrl = (String) data[3];
                        
                        VBox card = new VBox(10);
                        card.getStyleClass().add("product-card-premium");
                        card.setPrefWidth(220);
                        
                        VBox imgContainer = new VBox();
                        imgContainer.getStyleClass().add("image-container");
                        imgContainer.setPrefHeight(250);
                        imgContainer.setAlignment(Pos.CENTER);
                        
                        try {
                            Image image;
                            if (imageCache.containsKey(imgUrl)) {
                                image = imageCache.get(imgUrl);
                            } else {
                                image = new Image(imgUrl, 200, 250, true, true, true);
                                imageCache.put(imgUrl, image);
                            }
                            ImageView imageView = new ImageView(image);
                            imgContainer.getChildren().add(imageView);
                        } catch (Exception ex) {
                            Label lblNoImg = new Label("[ IMAGEN ]");
                            lblNoImg.setStyle("-fx-text-fill: #8b92a5;");
                            imgContainer.getChildren().add(lblNoImg);
                        }
                        
                        Label lblNombre = new Label(nombre);
                        lblNombre.getStyleClass().add("product-title-premium");
                        lblNombre.setWrapText(true);
                        
                        // Parsear Tallas
                        String tallasStr = (String) data[4];
                        String[] tallasArray = tallasStr.split(",");
                        javafx.scene.control.ComboBox<String> comboTalla = new javafx.scene.control.ComboBox<>();
                        for (String t : tallasArray) comboTalla.getItems().add(t.trim());
                        if (!comboTalla.getItems().isEmpty()) comboTalla.setValue(comboTalla.getItems().get(0));
                        comboTalla.setMaxWidth(Double.MAX_VALUE);
                        comboTalla.getStyleClass().add("combo-talla");

                        // Parsear Colores
                        String coloresStr = (String) data[5];
                        String[] coloresArray = coloresStr.split(",");
                        javafx.scene.control.ComboBox<String> comboColor = new javafx.scene.control.ComboBox<>();
                        for (String c : coloresArray) comboColor.getItems().add(c.trim());
                        if (!comboColor.getItems().isEmpty()) comboColor.setValue(comboColor.getItems().get(0));
                        comboColor.setMaxWidth(Double.MAX_VALUE);
                        comboColor.getStyleClass().add("combo-talla"); // Usamos el mismo estilo para consistencia
                        
                        Label lblPrecio = new Label(String.format("S/. %.2f", precio));
                        lblPrecio.getStyleClass().add("product-price-premium");
                        
                        Button btnComprar = new Button("🛒 AÑADIR AL CARRITO");
                        btnComprar.getStyleClass().add("btn-add-cart");
                        btnComprar.setMaxWidth(Double.MAX_VALUE);
                        
                        btnComprar.setOnAction(e -> {
                            String tallaElegida = comboTalla.getValue();
                            String colorElegido = comboColor.getValue();
                            cartProductIds.add(id);
                            cartNames.add(nombre + " (" + tallaElegida + " - " + colorElegido + ")");
                            cartPrices.add(precio);
                            cartImages.add(imgUrl);
                            btnCart.setText("🛒 CARRITO (" + cartNames.size() + ")");
                        });
                        
                        card.getChildren().addAll(imgContainer, lblNombre, comboColor, comboTalla, lblPrecio, btnComprar);
                        catalogFlowPane.getChildren().add(card);
                    }
                });
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    @FXML
    private void handleOpenCart() {
        javafx.scene.control.Dialog<javafx.scene.control.ButtonType> dialog = new javafx.scene.control.Dialog<>();
        dialog.setTitle("CARRITO DE MODA v1.2");
        dialog.setHeaderText(null);
        
        javafx.scene.layout.HBox mainLayout = new javafx.scene.layout.HBox(20);
        mainLayout.setPadding(new javafx.geometry.Insets(20));
        mainLayout.setStyle("-fx-background-color: #1a1c21;");
        
        javafx.scene.layout.VBox leftCol = new javafx.scene.layout.VBox(15);
        leftCol.setPrefWidth(450);
        
        javafx.scene.layout.HBox headerBox = new javafx.scene.layout.HBox(10);
        headerBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        Label lblTitle = new Label("Tu Carrito");
        lblTitle.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        Label lblItemsCount = new Label("(" + cartNames.size() + " artículos)");
        lblItemsCount.setStyle("-fx-text-fill: #8b92a5; -fx-font-size: 16px;");
        
        javafx.scene.layout.Region spacerHeader = new javafx.scene.layout.Region();
        javafx.scene.layout.HBox.setHgrow(spacerHeader, javafx.scene.layout.Priority.ALWAYS);
        Button btnClear = new Button("🗑 Vaciar");
        btnClear.setStyle("-fx-background-color: transparent; -fx-text-fill: #8b92a5; -fx-font-size: 14px; -fx-cursor: hand;");
        btnClear.setOnAction(e -> { 
            cartProductIds.clear(); cartNames.clear(); cartPrices.clear(); cartImages.clear(); 
            dialog.close(); btnCart.setText("🛒 CARRITO (0)"); handleOpenCart(); 
        });
        headerBox.getChildren().addAll(lblTitle, lblItemsCount, spacerHeader, btnClear);
        
        javafx.scene.control.ScrollPane scroll = new javafx.scene.control.ScrollPane();
        scroll.setFitToWidth(true);
        scroll.setPrefHeight(350);
        scroll.setStyle("-fx-background-color: transparent; -fx-background: #1a1c21;");
        
        javafx.scene.layout.VBox itemsContainer = new javafx.scene.layout.VBox(10);
        itemsContainer.setStyle("-fx-background-color: #1a1c21;");
        itemsContainer.setPadding(new javafx.geometry.Insets(0, 10, 0, 0));
        
        double subtotal = 0.0;
        
        if (cartNames.isEmpty()) {
            Label lblEmpty = new Label("Tu carrito está vacío.");
            lblEmpty.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
            itemsContainer.getChildren().add(lblEmpty);
        } else {
            for (int i = 0; i < cartNames.size(); i++) {
                final int index = i;
                subtotal += cartPrices.get(i);
                
                javafx.scene.layout.HBox itemBox = new javafx.scene.layout.HBox(15);
                itemBox.setStyle("-fx-background-color: #24272e; -fx-background-radius: 10; -fx-padding: 10;");
                itemBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
                
                javafx.scene.image.ImageView imgView = new javafx.scene.image.ImageView();
                try {
                    String imgUrl = cartImages.get(i);
                    javafx.scene.image.Image img = imageCache.containsKey(imgUrl) ? imageCache.get(imgUrl) : new javafx.scene.image.Image(imgUrl, 60, 60, true, true, true);
                    imgView.setImage(img);
                } catch(Exception ex) {}
                imgView.setFitWidth(60);
                imgView.setFitHeight(60);
                
                javafx.scene.layout.VBox detailsBox = new javafx.scene.layout.VBox(5);
                String fullName = cartNames.get(i);
                String nameOnly = fullName.contains("(") ? fullName.substring(0, fullName.indexOf("(")).trim() : fullName;
                String sizeColor = fullName.contains("(") ? fullName.substring(fullName.indexOf("(")+1, fullName.length()-1) : "";
                
                Label lblName = new Label(nameOnly);
                lblName.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
                Label lblSize = new Label(sizeColor);
                lblSize.setStyle("-fx-text-fill: #8b92a5; -fx-font-size: 12px;");
                Label lblQty = new Label("Cant: 1");
                lblQty.setStyle("-fx-text-fill: #8b92a5; -fx-font-size: 12px;");
                detailsBox.getChildren().addAll(lblName, lblSize, lblQty);
                
                javafx.scene.layout.Region spacerItem = new javafx.scene.layout.Region();
                javafx.scene.layout.HBox.setHgrow(spacerItem, javafx.scene.layout.Priority.ALWAYS);
                
                javafx.scene.layout.VBox rightItemBox = new javafx.scene.layout.VBox(10);
                rightItemBox.setAlignment(javafx.geometry.Pos.TOP_RIGHT);
                Button btnRemove = new Button("🗑");
                btnRemove.setStyle("-fx-background-color: transparent; -fx-text-fill: #8b92a5; -fx-cursor: hand;");
                btnRemove.setOnAction(e -> {
                    cartProductIds.remove(index);
                    cartNames.remove(index);
                    cartPrices.remove(index);
                    cartImages.remove(index);
                    dialog.close();
                    btnCart.setText("🛒 CARRITO (" + cartNames.size() + ")");
                    handleOpenCart();
                });
                Label lblItemPrice = new Label(String.format("S/. %.2f", cartPrices.get(i)));
                lblItemPrice.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
                rightItemBox.getChildren().addAll(btnRemove, lblItemPrice);
                
                itemBox.getChildren().addAll(imgView, detailsBox, spacerItem, rightItemBox);
                itemsContainer.getChildren().add(itemBox);
            }
        }
        scroll.setContent(itemsContainer);
        
        javafx.scene.layout.VBox leftFooter = new javafx.scene.layout.VBox(5);
        leftFooter.setPadding(new javafx.geometry.Insets(10, 0, 0, 0));
        javafx.scene.layout.HBox subBox = new javafx.scene.layout.HBox(); Label ls = new Label("Subtotal:"); ls.setStyle("-fx-text-fill: #8b92a5;"); javafx.scene.layout.Region s1 = new javafx.scene.layout.Region(); javafx.scene.layout.HBox.setHgrow(s1, javafx.scene.layout.Priority.ALWAYS); Label vs = new Label(String.format("S/. %.2f", subtotal)); vs.setStyle("-fx-text-fill: #8b92a5;"); subBox.getChildren().addAll(ls, s1, vs);
        double envio = cartNames.isEmpty() ? 0 : 9.99;
        javafx.scene.layout.HBox envBox = new javafx.scene.layout.HBox(); Label le = new Label("Envío:"); le.setStyle("-fx-text-fill: #8b92a5;"); javafx.scene.layout.Region s2 = new javafx.scene.layout.Region(); javafx.scene.layout.HBox.setHgrow(s2, javafx.scene.layout.Priority.ALWAYS); Label ve = new Label(String.format("S/. %.2f", envio)); ve.setStyle("-fx-text-fill: #8b92a5;"); envBox.getChildren().addAll(le, s2, ve);
        double igv = subtotal * 0.18;
        javafx.scene.layout.HBox igvBox = new javafx.scene.layout.HBox(); Label li = new Label("Impuestos (18%):"); li.setStyle("-fx-text-fill: #8b92a5;"); javafx.scene.layout.Region s3 = new javafx.scene.layout.Region(); javafx.scene.layout.HBox.setHgrow(s3, javafx.scene.layout.Priority.ALWAYS); Label vi = new Label(String.format("S/. %.2f", igv)); vi.setStyle("-fx-text-fill: #8b92a5;"); igvBox.getChildren().addAll(li, s3, vi);
        javafx.scene.layout.HBox totBox = new javafx.scene.layout.HBox(); Label lt = new Label("Total:"); lt.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px;"); javafx.scene.layout.Region s4 = new javafx.scene.layout.Region(); javafx.scene.layout.HBox.setHgrow(s4, javafx.scene.layout.Priority.ALWAYS); double finalTotal = subtotal + envio + igv; Label vt = new Label(String.format("S/. %.2f", finalTotal)); vt.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px;"); totBox.getChildren().addAll(lt, s4, vt);
        
        leftFooter.getChildren().addAll(subBox, envBox, igvBox, totBox);
        leftCol.getChildren().addAll(headerBox, scroll, leftFooter);
        
        javafx.scene.layout.VBox rightCol = new javafx.scene.layout.VBox(20);
        rightCol.setPrefWidth(280);
        rightCol.setStyle("-fx-background-color: #24272e; -fx-background-radius: 10; -fx-padding: 20;");
        
        Label lblResumen = new Label("Resumen del Pedido");
        lblResumen.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
        
        javafx.scene.layout.VBox resDetails = new javafx.scene.layout.VBox(10);
        javafx.scene.layout.HBox r1 = new javafx.scene.layout.HBox(); Label l1 = new Label("Subtotal:"); l1.setStyle("-fx-text-fill: #8b92a5;"); javafx.scene.layout.Region ss1 = new javafx.scene.layout.Region(); javafx.scene.layout.HBox.setHgrow(ss1, javafx.scene.layout.Priority.ALWAYS); Label v1 = new Label(String.format("S/. %.2f", subtotal)); v1.setStyle("-fx-text-fill: #8b92a5;"); r1.getChildren().addAll(l1, ss1, v1);
        javafx.scene.layout.HBox r2 = new javafx.scene.layout.HBox(); Label l2 = new Label("Envío:"); l2.setStyle("-fx-text-fill: #8b92a5;"); javafx.scene.layout.Region ss2 = new javafx.scene.layout.Region(); javafx.scene.layout.HBox.setHgrow(ss2, javafx.scene.layout.Priority.ALWAYS); Label v2 = new Label(String.format("S/. %.2f", envio)); v2.setStyle("-fx-text-fill: #8b92a5;"); r2.getChildren().addAll(l2, ss2, v2);
        javafx.scene.layout.HBox r3 = new javafx.scene.layout.HBox(); Label l3 = new Label("Impuestos (18%):"); l3.setStyle("-fx-text-fill: #8b92a5;"); javafx.scene.layout.Region ss3 = new javafx.scene.layout.Region(); javafx.scene.layout.HBox.setHgrow(ss3, javafx.scene.layout.Priority.ALWAYS); Label v3 = new Label(String.format("S/. %.2f", igv)); v3.setStyle("-fx-text-fill: #8b92a5;"); r3.getChildren().addAll(l3, ss3, v3);
        javafx.scene.control.Separator sep = new javafx.scene.control.Separator();
        javafx.scene.layout.HBox r4 = new javafx.scene.layout.HBox(); Label l4 = new Label("Total:"); l4.setStyle("-fx-text-fill: white; -fx-font-weight: bold;"); javafx.scene.layout.Region ss4 = new javafx.scene.layout.Region(); javafx.scene.layout.HBox.setHgrow(ss4, javafx.scene.layout.Priority.ALWAYS); Label v4 = new Label(String.format("S/. %.2f", finalTotal)); v4.setStyle("-fx-text-fill: white; -fx-font-weight: bold;"); r4.getChildren().addAll(l4, ss4, v4);
        resDetails.getChildren().addAll(r1, r2, r3, sep, r4);
        
        javafx.scene.layout.VBox promoBox = new javafx.scene.layout.VBox(5);
        Label lblPromo = new Label("Código promocional");
        lblPromo.setStyle("-fx-text-fill: white;");
        javafx.scene.control.TextField txtPromo = new javafx.scene.control.TextField();
        txtPromo.setPromptText("Ingresa código...");
        txtPromo.setStyle("-fx-background-color: #1a1c21; -fx-text-fill: white; -fx-border-color: #3b3f4a; -fx-border-radius: 5;");
        promoBox.getChildren().addAll(lblPromo, txtPromo);
        
        javafx.scene.layout.Region midSpacer = new javafx.scene.layout.Region();
        javafx.scene.layout.VBox.setVgrow(midSpacer, javafx.scene.layout.Priority.ALWAYS);
        
        Button btnPagarFinal = new Button("🛒 PAGAR | S/. " + String.format("%.2f", finalTotal));
        btnPagarFinal.setStyle("-fx-background-color: #ff4757; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px; -fx-background-radius: 8; -fx-cursor: hand;");
        btnPagarFinal.setMaxWidth(Double.MAX_VALUE);
        btnPagarFinal.setPrefHeight(50);
        
        javafx.scene.control.ButtonType hiddenClose = new javafx.scene.control.ButtonType("", javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(hiddenClose);
        dialog.getDialogPane().lookupButton(hiddenClose).setVisible(false);
        dialog.getDialogPane().getScene().getWindow().setOnCloseRequest(e -> dialog.close());
        
        btnPagarFinal.setOnAction(e -> {
            if (cartNames.isEmpty()) return;
            String sql = "UPDATE productos SET stock = stock - 1 WHERE id = ?";
            try (java.sql.Connection conn = com.nemastreet.models.Database.connect();
                 java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (Integer pId : cartProductIds) {
                    pstmt.setInt(1, pId);
                    pstmt.executeUpdate();
                }
            } catch (Exception ex) {}

            cartProductIds.clear(); cartNames.clear(); cartPrices.clear(); cartImages.clear();
            btnCart.setText("🛒 CARRITO (0)");
            dialog.close();
            
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
            alert.setTitle("Compra Exitosa");
            alert.setHeaderText(null);
            alert.setContentText("¡Tu pago ha sido procesado! Total cobrado: S/. " + String.format("%.2f", finalTotal));
            alert.showAndWait();
            loadCatalogWithFilters();
        });
        
        Button btnClose = new Button("Cerrar Carrito");
        btnClose.setStyle("-fx-background-color: transparent; -fx-text-fill: #8b92a5; -fx-cursor: hand;");
        btnClose.setMaxWidth(Double.MAX_VALUE);
        btnClose.setOnAction(e -> dialog.close());
        
        rightCol.getChildren().addAll(lblResumen, resDetails, promoBox, midSpacer, btnPagarFinal, btnClose);
        rightCol.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        
        mainLayout.getChildren().addAll(leftCol, rightCol);
        dialog.getDialogPane().setContent(mainLayout);
        dialog.getDialogPane().setPadding(new javafx.geometry.Insets(0));
        dialog.getDialogPane().setStyle("-fx-background-color: #1a1c21;");
        
        dialog.showAndWait();
    }

    @FXML
    private void logout() throws IOException {
        App.setRoot("login");
    }
}
