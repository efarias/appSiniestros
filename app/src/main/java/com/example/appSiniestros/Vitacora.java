package com.example.appSiniestros;

import java.io.Serializable;

public class Vitacora implements Serializable {
    private Siniestro siniestro;
    private Usuario usuario;

    public Vitacora(Siniestro siniestro, Usuario usuario) {
        this.siniestro = siniestro;
        this.usuario = usuario;
    }

    public Siniestro getNoticia() {
        return siniestro;
    }

    public void setNoticia(Siniestro siniestro) {
        this.siniestro = siniestro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}