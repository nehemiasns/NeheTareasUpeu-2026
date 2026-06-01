package com.nemastreet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        com.nemastreet.models.Database.init();
        scene = new Scene(loadFXML("login"), 900, 650);
        try {
            scene.getStylesheets().add(App.class.getResource("style.css").toExternalForm());
        } catch(Exception e) {
            System.out.println("Error loading css: " + e.getMessage());
        }
        stage.setTitle("Nemastreet - Oversize Fashion");
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
