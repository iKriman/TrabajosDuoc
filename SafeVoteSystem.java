// Actividad Sumativa 8:  Creacion de un sistema de incriptacion usando hilos y numeros primos
package SafeVoteSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SafeVoteSystem {
    
    // llamamos a los archivos para cargar los primos y para guardar mensajes
    private static final String PRIMOS_INCIALES = "numeros_primos.csv"; 
    private static final String LOG_MENSAJES = "log_mensajes_encriptados.txt";

    public static void main(String[] args) {
        
        
        
        Scanner teclado = new Scanner(System.in);
        FileWriter writer = null;
        
        try {
            writer = new FileWriter(LOG_MENSAJES, true); // dejamos como true para que no se borre lo que ya esta escrito
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
       
        
        /** de manera conceptual no solo utilizaremos el BlockingQueue como Queue
         si no que tambien como Topic, canal central de comunicacion de la clase 
         PrimesThread, de esta forma se mejora la distribucion de tareas de la clase **/
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(100);
        
        PrimesList primes = new PrimesList(); // creamos un objeto de la clase primeslist
        
        // usamos una iteracion for para tener varios hilos de nuestra clase PrimesThread
        List<Thread> threads = new ArrayList<>();
        int hilos = 3;
        for (int i = 0; i < hilos; i++) {
        PrimesThread pt = new PrimesThread(primes, queue);
        Thread thread = new Thread(pt);
        threads.add(thread);
        thread.start();
        }
        
        boolean otraOperacion = true;
        int opcionMenu;

        // inicializamos el archivo csv con los numeros primos pre definidos
        try {
            System.out.println("Cargando archivo CSV con numeros primos...");
            try (BufferedReader reader = new BufferedReader(new FileReader(PRIMOS_INCIALES))) {
                String line;
                while ((line = reader.readLine()) != null) { 
                    String[] primosString = line.split(","); // parseamos los datos del csv
                    for (String s : primosString) {
                        try {
                            int prime = Integer.parseInt(s.trim());
                            if (primes.isPrime(prime)) {
                                primes.add(prime);
                            }
                        } catch (NumberFormatException e) {
                            System.err.println("Advertencia: No se pudo parsear");
                        } catch (IllegalArgumentException e) {
                            System.err.println("Advertencia: El numero " + s + " no es primo.");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de primos iniciales: " + e.getMessage());
        }

        do { // menu iterativo para crear un mensaje encriptado
            while (true) {
                System.out.println("=== Sistema de Incriptacion de Mensajes ===");
                System.out.println("1. Test de Incriptacion.");
                System.out.println("2. Ver lista.");
                System.out.println("3. Salir.");

                try {
                    opcionMenu = teclado.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Ingrese una opción válida.");
                    teclado.nextLine();
                }
            }

            teclado.nextLine();

            switch (opcionMenu) {
                case 1: // TEST DE INCRIPTACION

                    System.out.println("Ingrese su nombre: ");
                    String nombreUsuario = teclado.nextLine();
                    List<Integer> numeroPrimoIncriptado = Collections.synchronizedList(new ArrayList<>());

                    int primoIncriptador = 3;

                    synchronized (primes) { // sincornizamos el metodo para asegurarnos de que se generen los numeros primos
                        while (primes.getPrimesCount() < primoIncriptador) {
                            System.out.println("Esperando a que se generen numeros primos.");
                            try {
                                primes.wait(); // hacemos que los hilos agreguen numeros primos
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                System.out.println("Sistema interrumpido.");
                                break;
                            }
                        }
                        Random rand = new Random(); // generamos un numero random
                        for (int i = 0; i < primoIncriptador; i++) {
                            int indexRand = rand.nextInt(primes.size());
                            numeroPrimoIncriptado.add(primes.get(indexRand));
                        }

                        try {
                            synchronized (writer) { // writer sincronizado listo para loggear los datos al txt
                                writer.write("Nombre a encriptar: " + nombreUsuario);
                                writer.write(". Numeros primos asociados: ");
                                if (!numeroPrimoIncriptado.isEmpty()) {
                                    for (Integer prime : numeroPrimoIncriptado) {
                                        writer.write(prime + " ");
                                    }
                                } else {
                                    System.out.println("No hay numeros primos");
                                }
                                writer.write("\n"); // usamos un salto de linea para mantener el orden
                            }
                            System.out.println("MENSAJE ENCRIPTADO CON EXITO!");
                        } catch (IOException e) {
                            System.out.println("El mensaje no pudo encriptarse.");
                        }
                    }
                    break;
                    

                case 2: // ver lista de numeros primos

                    System.out.println("=== Lista numeros primos ===");
                    System.out.println(" ");
                    if (primes.isEmpty()) {
                        System.out.println("Aun no hay numeros primos en la lista.");
                    } else {
                        System.out.print("Numeros primos actualmente en la lista: ");
                        System.out.println(primes); // con esto nos devuelve los numeros de la lista
                        System.out.println("Cantidad total: " + primes.getPrimesCount()); // llamamos al metodo para ver la cantidad actual

                    }
                    break;

                case 3: // salir
                    System.out.println("Gracias!");
                    otraOperacion = false;
                    break;


                default:
                    System.out.println("Opcion invalida, vuelva a intentarlo.");
                    break;
            }
            
           
            if (opcionMenu != 3) {
                int continuar;
                do {
                    System.out.println("\nDesea realizar otra operacion?");
                    System.out.println("1. Si");
                    System.out.println("2. No");
                   

                    try {
                        continuar = teclado.nextInt();
                        if (continuar == 1) {
                            otraOperacion = true;
                        } else if (continuar == 2) {
                            System.out.println("Gracias por usar el sistema.");
                            otraOperacion = false;
                        } else {
                            System.out.println("Ingrese una opcion valida.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error, ingrese un numero valido.");
                        teclado.nextLine();
                        continuar = 0; 
                    }
                } while (continuar != 1 && continuar != 2);
            }
        } while (otraOperacion);
        
        // cerramos el teclado y el writer para mantener buenas practicas
        teclado.close();

        // utilizamos el metodo join() para finalizar los hilos al terminar de usar el programa
        System.out.println("Esperando a que los hilos terminen...");
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Interrupción al esperar hilos.");
            }
        }
        System.out.println("Todos los hilos han finalizado.");
        System.out.println("Cantidad de numeros primos en la lista: " + primes.getPrimesCount());

        // encapsulamos en un trycatch en caso de que no se logre cerrar el archivo
        try {
            writer.close();
        } catch (IOException e) {
            System.err.println("Error al cerrar el archivo: " + e.getMessage());
        }
    }

}
//ik
