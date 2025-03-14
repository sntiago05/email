package org.email.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.email.model.Usuario;
import org.email.utils.Filter;
import org.email.utils.GsonSerializador;
import org.email.utils.SceneManager;
import org.email.utils.SessionManager;

import java.io.IOException;
import java.util.List;

public class LoginController {
    @FXML
    private TextField usuariotxt;

    @FXML
    private Button loginButton;

    @FXML
    public void initialize() {
        Filter.restringirCampo(usuariotxt);
    }

    @FXML
    public void login() throws IOException {
        String nombre = usuariotxt.getText();
        if (!nombre.isBlank()) {
            Usuario usuario = new Usuario(nombre);
            SessionManager.getInstance().setUser(usuario);
            List<Usuario> users = GsonSerializador.leerUsuarios();
          boolean creado =  users.stream().anyMatch(e-> e.getNombre().equals(nombre));
          if (!creado){
              GsonSerializador.escribirUsuario(usuario);
          }
            SceneManager.cambiarEscena((Stage) loginButton.getScene().getWindow(), "/org/email/email_view.fxml");
        }else{
            Filter.alerta("Ingresa un usuario","Debes ingresar un valor en el campo usuario");
        }

    }


}
