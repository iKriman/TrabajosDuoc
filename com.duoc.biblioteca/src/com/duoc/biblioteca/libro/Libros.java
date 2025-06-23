package com.duoc.biblioteca.libro;

import java.lang.Comparable;

public class Libros implements Comparable<Libros> {

    private int anio;
    private String titulo;
    private String autor;
    private boolean disponible; // indicacion si el libro se encuentra disponible
   
    
    public Libros (int anio, String titulo, String autor, boolean disponible) {
        this.anio = anio;
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = disponible;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean ocupado) {
        this.disponible = ocupado;
    }
    
  
    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String nombre) {
        this.titulo = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    //METODOS 
    public boolean prestarLibro() {
        if (disponible) {
            this.disponible = false;
            System.out.println("Libro prestado con exito!");
            return true;

        } else {
            disponible = false;
            System.out.println("El libro no se encuentra disponible.");
        }
        return true;

    }
    
    public boolean devolverLibro() {
        if (!disponible) {
            this.disponible = true;
            System.out.println("Libro devuelto con exito!");
            return true;
        } else {
            System.out.println("Este libro no esta prestado.");
            return false;
        }

    }


    public boolean libroPrestado() {
        if (!disponible) {
            System.out.println("Este libro esta disponible!");
            return true;
        } else {
            System.out.println("Este libro no esta disponible.");
        }
        return false;
    }
    
    // implementamos el metodo compareTo para ordenar los libros alfabeticamente
    @Override 
    public int compareTo(Libros otroLibro) {
        return this.titulo.compareToIgnoreCase(otroLibro.getTitulo());
    }
    
    /** implementamos equals para mejor manejo de la coleccion y de esta forma
        nos aseguramos de que se muestre solo un titulo por libro **/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Libros libros = (Libros) obj;
        return titulo.equalsIgnoreCase(libros.titulo);
    }



}
