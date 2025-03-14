package org.email.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

public class Filter {
    public static void restringirCampo(TextField campo) {
        campo.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*")) {
                campo.setText(oldValue);
            }
        });
    }
    public static void restringirCampoNoespacios(TextField campo) {
    campo.textProperty().addListener((obs, oldValue, newValue) -> {
        if (!newValue.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]*")) {
            campo.setText(oldValue);
        }
    });
}
    public static void alerta(String titulo, String mensaje){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.initStyle(StageStyle.UNDECORATED);
            DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Filter.class.getResource("/org/email/alertstyles.css").toExternalForm());
        alert.show();
    }
     public static void alertaExito(String titulo, String mensaje){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.initStyle(StageStyle.UNDECORATED);
            DialogPane dialogPane = alert.getDialogPane();

        dialogPane.getStylesheets().add(Filter.class.getResource("/org/email/alertstyles.css").toExternalForm());
        alert.show();
    }
}
