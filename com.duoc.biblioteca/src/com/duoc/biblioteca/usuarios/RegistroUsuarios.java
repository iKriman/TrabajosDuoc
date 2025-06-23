package com.duoc.biblioteca.usuarios;

import com.duoc.biblioteca.usuarios.Usuario;
import java.util.HashMap; // para almacenar a los usuarios
import java.util.Set; // 

public class RegistroUsuarios {

    private HashMap<String, Usuario> usuariosRegistrados;

    public RegistroUsuarios() {
        this.usuariosRegistrados = new HashMap<>();
    }

    // METODOS
    // creamos nuestro metodo para almacenar los datos del usuaio en el HashMap
    // utilizaremos el nombre como Key y el objeto Usuario como Value
    public boolean registrarUsuario(Usuario nuevoUsuario) {
        if (nuevoUsuario == null || nuevoUsuario.getNombre() == null || nuevoUsuario.getNombre().trim().isEmpty()) {
            System.out.println("Por favor ingrese un nombre de usuario.");
            return false;
        }

        String nombreKey = nuevoUsuario.getNombre().toLowerCase();

        // verificacion si la key ya existe en el sistema
        if (usuariosRegistrados.containsKey(nombreKey)) {
            System.out.println("Este usuario ya esta registrado.");
            return false;
        }

        // anadimos el nombre/key al hashmap
        usuariosRegistrados.put(nombreKey, nuevoUsuario);
        nuevoUsuario.setRegistrado(true);
        System.out.println("Usuario registrado con exito!");
        return true;
    }

    // con este metodo lograremos encontrar a un usuario registrado
    public Usuario buscar(String nombreUsuario) {
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            System.out.println("Por favor ingrese un nombre de usuario para buscarlo.");
            return null;
        }
        return usuariosRegistrados.get(nombreUsuario.toLowerCase());
    }

    // metodo para eliminar a un usuario
    public boolean eliminar(String nombreUsuario) {
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            System.out.println("Por favor ingrese un nombre de usuario para buscarlo.");
            return false;
        }

        String nombreKey = nombreUsuario.toLowerCase();
        if (usuariosRegistrados.containsKey(nombreKey)) {
            Usuario eliminado = usuariosRegistrados.remove(nombreKey);
            eliminado.setRegistrado(false);
            System.out.println("Usuario eliminado con exito.");
            return true;
        } else {
            System.out.println("Error el usuario no se encuentra en los registros.");
            return false;
        }

    }

}
