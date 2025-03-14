package org.email.model;

import java.util.List;

public class Mensaje {
    private String asunto;
    private String cuerpo;
    private String direccionDestino;
    private String direccionInicial;
    private String cc;

    public Mensaje() {
    }

    public Mensaje(String asunto, String cuerpo, String direccionDestino, String direccionInicial, String cc) {
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.direccionDestino = direccionDestino;
        this.direccionInicial = direccionInicial;
        this.cc = cc;
    }

    public String getDireccionInicial() {
        return direccionInicial;
    }


    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public void setDireccionInicial(String direccionInicial) {
        this.direccionInicial = direccionInicial;
    }

    @Override
    public String toString() {
        return  getAsunto() +" enviado por: "+direccionInicial;
    }
}
