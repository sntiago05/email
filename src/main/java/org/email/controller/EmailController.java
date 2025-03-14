package org.email.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.email.model.Mensaje;
import org.email.model.Usuario;
import org.email.utils.Filter;
import org.email.utils.GsonSerializador;
import org.email.utils.SceneManager;
import org.email.utils.SessionManager;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.email.utils.Filter.restringirCampoNoespacios;


public class EmailController {
    @FXML
    Accordion mensajes;
    @FXML
    private TextField direccionTxt;

    @FXML
    private TextField ccTxt;

    @FXML
    private TextField asuntoTxt;

    @FXML
    private TextArea mensajeArea;

    @FXML
    private Button limpiarButton;

    @FXML
    private Button buttonEnviar;
    @FXML
    private Button buttonSesion;

    @FXML
    public void initialize() {
        restringirCampoNoespacios(direccionTxt);
        restringirCampoNoespacios(ccTxt);
        List<Usuario> users = GsonSerializador.leerUsuarios();
        List<Mensaje> correos = users.stream()
                .filter(u -> u.getNombre().equals(SessionManager.getInstance().getUser().getNombre()))
                .findFirst()
                .map(Usuario::getRecibidos)
                .orElse(Collections.emptyList());

        mensajes.getPanes().clear();
        for (Mensaje mensaje : correos) {
            TextArea textArea = new TextArea(mensaje.getCuerpo());
            textArea.setWrapText(true);
            textArea.setEditable(false);
            String titulo = mensaje.getAsunto() + " - " + mensaje.getDireccionInicial();
            TitledPane pane = new TitledPane(titulo, textArea);
            pane.getStylesheets().add(EmailController.class.getResource("/org/email/titledpane.css").toExternalForm());
            mensajes.getPanes().add(pane);
        }
    }

    @FXML
    private void limpiar() {
        direccionTxt.clear();
        ccTxt.clear();
        asuntoTxt.clear();
        mensajeArea.clear();
    }

    @FXML
    private void enviar() {
        List<Usuario> users = GsonSerializador.leerUsuarios();
        Mensaje mensaje = new Mensaje();
        String asunto = asuntoTxt.getText();
        String para = direccionTxt.getText();
        String cc = ccTxt.getText();
        String cuerpo = mensajeArea.getText();
        boolean esta = users.stream().anyMatch(u -> u.getNombre().equals(para));
        boolean estaCc = users.stream().anyMatch(u -> u.getNombre().equals(cc));
        if (asunto.isBlank() || para.isBlank() || cc.isBlank() || cuerpo.isBlank()) {
            Filter.alerta("Campos vacios", "Debes llenar todos los campos");
        } else if (!esta || !estaCc) {
            Filter.alerta("usuarios no encontrados", "Los usuarios no se encuentran");
        } else {
            mensaje.setCuerpo(cuerpo);
            mensaje.setAsunto(asunto);
            mensaje.setCc(cc);
            mensaje.setDireccionDestino(para);
            mensaje.setDireccionInicial(SessionManager.getInstance().getUser().getNombre());
            users.stream()
                    .filter(u -> u.getNombre().equals(para))
                    .findFirst()
                    .ifPresent(usuario -> usuario.getRecibidos().add(mensaje));

            users.stream()
                    .filter(u -> u.getNombre().equals(cc))
                    .findFirst()
                    .ifPresent(usuario -> usuario.getRecibidos().add(mensaje));
            GsonSerializador.guardarUsuarios(users);
            Filter.alertaExito("Mensaje enviado","El correo fue enviado con exito");
        }
    }

    @FXML
    private void salir() {
        Platform.exit();
    }

    @FXML
    private void cerrarSesion() throws IOException {
        SessionManager.getInstance().clearSession();
        SceneManager.cambiarEscena((Stage) buttonSesion.getScene().getWindow(), "/org/email/login.fxml");
    }
    @FXML
    private void limpiarBandeja(){
        System.out.println("entra");
        List<Usuario>users = GsonSerializador.leerUsuarios();
        users.stream()
                .filter(u -> u.getNombre().equals(SessionManager.getInstance().getUser().getNombre()))
                .findFirst().get().getRecibidos().clear();
        GsonSerializador.guardarUsuarios(users);
        mensajes.getPanes().clear();
    }
}