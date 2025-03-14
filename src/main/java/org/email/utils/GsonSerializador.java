package org.email.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.email.model.Usuario;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonSerializador {
    private static Gson gson = new Gson();
    private static String ruta = "src/main/java/org/email/data/data.json";

    public static void escribirUsuario(Usuario usuario) {
        List<Usuario> usuarios = leerUsuarios();
        usuarios.add(usuario);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            writer.write(gson.toJson(usuarios));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Usuario> leerUsuarios() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            Type listType = new TypeToken<List<Usuario>>() {
            }.getType();
            List<Usuario> usuarios = gson.fromJson(reader, listType);
            return (usuarios != null) ? usuarios : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    public static void guardarUsuarios(List<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            writer.write(gson.toJson(usuarios));
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar usuarios", e);
        }
    }
}
