package org.email.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    String nombre;
    List<Mensaje>recibidos;

    public Usuario() {
        this.recibidos = new ArrayList<>();
    }

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.recibidos=new ArrayList<>();
    }

    public Usuario(String nombre, List<Mensaje> recibidos) {
        this.nombre = nombre;
        this.recibidos = recibidos;
        this.recibidos=new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Mensaje> getRecibidos() {
        return recibidos;
    }

    public void setRecibidos(List<Mensaje> recibidos) {
        this.recibidos = recibidos;
    }
}
