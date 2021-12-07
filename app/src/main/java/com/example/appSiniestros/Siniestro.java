package com.example.appSiniestros;

import android.location.Location;

import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;

public class Siniestro implements Serializable {

    private String titulo;
    private String nota;
    private String foto;
    private String fecha;
    private double latitud;
    private double longitud;
    private FirebaseUser usuario;
    private long id;

    public Siniestro(){}

    public Siniestro(String titulo, String nota, String foto, String fecha, double latitud, double longitud, FirebaseUser usuario, long id) {
        this.titulo = titulo;
        this.nota = nota;
        this.foto = foto;
        this.fecha = fecha;
        this.latitud = latitud;
        this.longitud = longitud;
        this.usuario = usuario;
        this.id = id;
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

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public FirebaseUser getUsuario() {
        return usuario;
    }

    public void setUsuario(FirebaseUser usuario) {
        this.usuario = usuario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
