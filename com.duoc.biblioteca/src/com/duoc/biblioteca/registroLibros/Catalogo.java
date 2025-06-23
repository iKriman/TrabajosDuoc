package com.duoc.biblioteca.registroLibros;

import com.duoc.biblioteca.excepciones.LibroException;
import com.duoc.biblioteca.libro.Libros;
import java.util.ArrayList;

public class Catalogo {

    private ArrayList<Libros> catalogo;

    public Catalogo() {

        catalogo = new ArrayList<>(); // inicializacion del arraylist

        // anadimos los libros predenidos a nuestro array
        catalogo.add(new Libros(1965, "Dune", "Frank Herbert", true));
        catalogo.add(new Libros(1949, "1984", "George Orwell", true));
        catalogo.add(new Libros(1986, "It", "Stephen King", true));
    }

    public ArrayList<Libros> getCatalogo() {
        return catalogo;
    }

    public int getTotalCatalogo() {
        return catalogo.size();
    }

    public Libros buscarLibro(String titulo) throws LibroException {

        for (Libros libro : catalogo) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        throw new LibroException("El libro no se encuentra disponible en nuestro catalogo.");
    }

    public void mostrarLibros() {
        System.out.println("Catalogo de Libros");
        if (catalogo.isEmpty()) {
            System.out.println("El catalogo esta vacio.");
            return;
        }
        for (Libros libro : catalogo) {
            System.out.println(libro.getTitulo() + " - " + libro.getAutor());
        }
    }

}
