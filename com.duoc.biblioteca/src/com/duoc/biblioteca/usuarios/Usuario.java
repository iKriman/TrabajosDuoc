package com.duoc.biblioteca.usuarios;

public class Usuario {

    private String nombre;
    private boolean registrado;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.registrado = false;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isRegistrado() {
        return registrado;
    }

    public void setRegistrado(boolean registrado) {
        this.registrado = registrado;
    }

}
