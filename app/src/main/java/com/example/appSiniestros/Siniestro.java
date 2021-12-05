package com.example.appSiniestros;

import android.location.Location;

import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;

public class Siniestro implements Serializable {

    private String titulo;
    private String nota;
    private String foto;
    private String fecha;
    private Location ubicacion;
    private FirebaseUser usuario;

    public Siniestro(String titulo, String nota, String foto, String fecha, Location ubicacion, FirebaseUser usuario) {
        this.titulo = titulo;
        this.nota = nota;
        this.foto = foto;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Location getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Location ubicacion) {
        this.ubicacion = ubicacion;
    }

    public FirebaseUser getUsuario() {
        return usuario;
    }

    public void setUsuario(FirebaseUser usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Noticia{" +
                "titulo='" + titulo + '\'' +
                ", fecha=" + fecha +
                ", usuario=" + usuario +
                '}';
    }
}
