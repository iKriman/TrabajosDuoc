package com.duoc.biblioteca;

import com.duoc.biblioteca.excepciones.LibroException;
import com.duoc.biblioteca.libro.Libros;
import com.duoc.biblioteca.registroLibros.Catalogo;
import com.duoc.biblioteca.usuarios.RegistroUsuarios;
import com.duoc.biblioteca.usuarios.Usuario;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;  // almacenamos libros
import java.util.HashMap;   // almacenamos usuarios
import java.util.HashSet;  // almacenamos listas de usuarios
import java.util.TreeSet; // para mantener el catalogo ordenado de libros 

public class ComDuocBiblioteca {

    public static void main(String[] args) {

        // instanciacion de objetos
        Scanner sc = new Scanner(System.in); 
        Catalogo catalogoLibros = new Catalogo();
        RegistroUsuarios registro = new RegistroUsuarios();
        
        
        
        
        /** creamos un HashSet el cual registrara los usuarios ingresados 
            en el sistema, lo cual nos permitira consultar dicha lista
            cuando queramos **/
        HashSet<String> listaUsuariosRegistrados = new HashSet();
        
        /** usaremos una coleccion TreeSet para ordenar los libros solicitados
            de manera alfabetica **/
        TreeSet<Libros> listaLibros = new TreeSet();

        
        
        
        boolean otraOperacion = true;
        int opcionMenu;

        do { // ciclo do-while para realizar la cantidad de operaciones que el usario desee

            while (true) {
                System.out.println("===BIBLIOTECA DUOC===");
                System.out.println(" ");
                System.out.println("1. Solicitar Prestamo.");
                System.out.println("2. Consultar Disponibilidad.");
                System.out.println("3. Ver Usuarios Registrados.");
                System.out.println("4. Eliminar Usuario.");
                System.out.println("5. Devolver Libro.");
                System.out.println("6. Consultar Usurios Registrados (HashSet).");
                System.out.println("7. Historial De Libros Solicitados (TreeSet).");
                System.out.println("8. Salir.");

                try {
                    opcionMenu = sc.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Ingrese una opcion valida.");
                    sc.nextLine();
                }

            }
            sc.nextLine();

            switch (opcionMenu) {
                case 1: // solicitar prestamo

                    catalogoLibros.mostrarLibros();
                    System.out.println("Escriba el nombre del libro que desee: ");
                    String libroSolicitado = sc.nextLine();
                    try { // aplicamos un bloque trycatch para usar nuestra execepcion LibroException
                        Libros libroEncontrado = catalogoLibros.buscarLibro(libroSolicitado);
                        libroEncontrado.prestarLibro();
                        listaLibros.add(libroEncontrado);
                        System.out.print("Por favor Ingrese su nombre: ");
                        String nombreUsuario = sc.nextLine();
                        Usuario nuevoUsuario = new Usuario(nombreUsuario);
                        registro.registrarUsuario(nuevoUsuario);
                        listaUsuariosRegistrados.add(nombreUsuario.toLowerCase());
                    } catch (LibroException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;


                case 2: // consultar disponibilidad
                    
                    System.out.print("Ingrese el nombre del libro que busca: ");
                    String buscarLibro;
                    buscarLibro = sc.nextLine();
                    try {
                    Libros libroBuscado = catalogoLibros.buscarLibro(buscarLibro);
                    libroBuscado.libroPrestado();
                    }catch (LibroException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                                         
                    break;
                    
                case 3:  // Ver Usuarios Registrados
                    
                    System.out.print("Ingrese el nombre de usuario que desea buscar: ");
                    String buscarUsuario = sc.nextLine();
                    Usuario clienteEncontrado = registro.buscar(buscarUsuario);
                     if (clienteEncontrado != null) {
                        System.out.println("Usuario encontrado: " + clienteEncontrado.getNombre() + 
                                           " (Registrado: " + clienteEncontrado.isRegistrado() + ")");
                     } else {
                         System.out.println("El usuario no fue encontrado.");
                     }
                     break;

                case 4: // Eliminar Usuario
                    System.out.print("Escriba el nombre del usuario que desea eliminar: ");
                    String eliminarUsuaio = sc.nextLine();
                    registro.eliminar(eliminarUsuaio);
                    break;
                    
                case 5: // Devolver Libro
                    System.out.println("Ingrese el nombre del libro que desee devolver: ");
                    String devolverLibro = sc.nextLine();
                    try {
                    Libros libroDevuelto = catalogoLibros.buscarLibro(devolverLibro);
                    libroDevuelto.devolverLibro();
                    } catch (LibroException e) {
                        System.out.println("Error: " + e.getMessage());
                    }                    
                    break;
                    
                case 6: // Consultar Usurios Registrados
                    System.out.println("Usuarios registrados en el sistema: ");
                    if (listaUsuariosRegistrados.isEmpty()) {
                        System.out.println("Aun no tenemos usuarios registrados.");
                    } else { // iteramos para consultar la lista
                        for (String nombre : listaUsuariosRegistrados) {
                            System.out.println("- " + nombre);
                        }
                    }  
                    break;
                    
                case 7: // Historial De Libros Solicitados
                    System.out.println("Registro de libros solicitados: ");

                    if (listaLibros.isEmpty()) {
                        System.out.println("Aun no hay libros registrados.");
                    } else { // iteramos para consultar la lista
                        for (Libros libro : listaLibros) { // con esta iteracion logramos ve la lista
                            System.out.println("- " + libro.getTitulo());
                        }
                    }
                    break;
                    
                case 8: // salir
                    System.out.println("Gracias por preferirnos.");
                    break;
                    
                default:
                    System.out.println("Eliga una opcion correcta.");
                    break;
      
            }

            int continuar;

            do { // uso de otro ciclo do-while para preguntarle al usuario si desea realizar otra operacion

                while (true) { // creacion de una iteracion try-catch para control de errores

                    System.out.println(" ");
                    System.out.println("Desea realizar otra operacion?");
                    System.out.println("1. Si.");
                    System.out.println("2. No.");
                    System.out.println(" ");

                    try {
                        continuar = sc.nextInt();
                        break;

                    } catch (InputMismatchException error) {
                        System.out.println("Error, ingrese un numero valido");
                        sc.nextLine();
                    }

                }

                if (continuar == 1) {
                    otraOperacion = true;
                } else if (continuar == 2) {
                    System.out.println("Gracias por preferirnos.");
                    otraOperacion = false;
                } else {
                    System.out.println("Ingrese una opción valida.");
                }

            } while (continuar != 1 && continuar != 2);

        } while (otraOperacion);

        sc.close();

    }

}


