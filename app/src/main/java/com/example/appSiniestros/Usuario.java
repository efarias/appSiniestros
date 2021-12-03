package com.example.appSiniestros;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String correo, nombreUsuario, genero, fechaNacimiento;
    private String contrasenna;

    public Usuario(String correo, String nombreUsuario, String genero, String fechaNacimiento, String contrasenna) {
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.contrasenna = contrasenna;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }


}