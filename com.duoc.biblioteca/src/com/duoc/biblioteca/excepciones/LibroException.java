package com.duoc.biblioteca.excepciones;

public class LibroException extends Exception {
    
    /** creamos una excepcion personalizada para manejar ingreso de libros que no
    estan en el sistema **/
    public LibroException(String mensaje) {
        super(mensaje);
        
    }

}
