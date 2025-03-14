package org.email.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    public static void cambiarEscena(Stage stage, String fxml) throws IOException {
        Parent root = FXMLLoader.load(SceneManager.class.getResource(fxml));
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
