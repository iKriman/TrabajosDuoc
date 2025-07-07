// Actividad Formativa 7:  Creacion de un algoritmo para comprobar si un numero es primo
package semana7;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Semana7 {

    public static void main(String[] args) {
        
        Scanner teclado = new Scanner(System.in);
        PrimesList primes = new PrimesList(); // creamos un objeto de la clase primeslist
        
        
        boolean otraOperacion = true;
        int opcionMenu;

        do { // menu iterativo para agregar varios numeros a la lista
            while (true) {
                System.out.println("=== Numeros Primos ===");
                System.out.println("1. Agregar numero a la lista.");
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
                case 1: // agrega numero a la lista
                    System.out.println("Por favor ingrese un numero: ");
                    int numeroUsuario;
                    numeroUsuario = teclado.nextInt();
                    try {
                        primes.add(numeroUsuario); // usamos el metodo add para agregar el numero
                        System.out.println("Numero primo agreado exitosamente!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage()); // lanzamos el mensaje en caso de que no sea primo
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

        teclado.close();
    }

}
//ik
