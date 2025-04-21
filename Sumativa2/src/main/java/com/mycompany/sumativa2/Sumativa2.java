// Ignacio Kriman Bravo Trabajo Sumativo 2
// Creacion de sistema de ventas de entradas para teatro con opcion de reserva y eliminacion de asientos e impresion de boleta.
package com.mycompany.sumativa2;

import java.util.Scanner;


public class Sumativa2 {

    // Variables estaticas
    static int entradasVendidas = 0;
    static int totalVendido = 0;
    static int tipoCliente;
    static String[] entradas = new String[100]; // Para simular asientos vendidos, el teatro contara con asientos del 1 al 100

    // variable de instancia
    int entradasDisponibles = 100;
    int precioFinal;
    int asientoComprado = -1; // variable para definir el asiento en la boleta
    int tipoClienteBoleta = 0; // variable para definir el tipo de cliente en la boleta
    

    public void mostrarEntradas() { // metodo para mostrar la cantidad de entradas disponibles.
        System.out.println("Entradas disponibles: " + entradasDisponibles);
    }

    public void darMontoFinal(int precioBase, int tipoCliente) {  // metodo para calcular precio final de entradas si aplican descuento
        switch (tipoCliente) {
            case 1: // estudiante
                precioFinal = (int) (precioBase * 0.90);
                break;
            case 2: // tercera edad
                precioFinal = (int) (precioBase * 0.85);
                break;
            case 3: // tarifa general
                precioFinal = precioBase;
                break;
            default:
                System.out.println("Ingrese solo el numero correspondiente a su tarifa.");
                return;

        }
        totalVendido += precioFinal;
        System.out.println("Monto por pagar: " + precioFinal);
    }
    

    

    public void buscarEntradas(int asiento) { // breakpoint para verificar que el asiento este disponible al reservarlo
        if (asiento >= 0 && asiento < entradas.length) {
            if (entradas[asiento] != null) {
                System.out.println("Asiento: " + (asiento + 1) + " RESERVADO.");
            } else { // se le suma 1 a la variabble "asiento" para que cuando se muestre en pantalla coincida con el numero elegido por el usuario
                System.out.println("Asiento: " + (asiento + 1) + " DISPONIBLE!");
            }
        } else {
            System.out.println("Numero invalido. Por favor ingrese un numero de asiento valido.");
        }
    }

    public void eliminarEntrada(int asiento) { // metodo para eliminacion de entradas ya vendidas // breakpoint que nos ayuda a verificar si efectivamente el asiento fue reservado
        if (asiento >= 0 && asiento < entradas.length) {
            if (entradas[asiento] != null) {
                System.out.println("Entrada del asiento " + (asiento + 1) + " eliminada."); // breakpoint que asegura la eliminacion del asiento seleccionado
                entradas[asiento] = null;
                entradasVendidas--;
                entradasDisponibles++;
            } else {
                System.out.println("Asiento " + (asiento + 1) + " no se encuentra ocupado.");
            }
        } else {
            System.out.println("Numero invalido. Por favor ingrese un numero de asiento valido.");
        }
    }
    
        public void imprimirBoleta() {

        switch (tipoClienteBoleta) { // breakpoint nos ayuda a verificar que el Switch arroje el Case correcto
            case 1:
                System.out.println("Estudiante");
                break;
            case 2:
                System.out.println("Tercera Edad");
                break;

            case 3:
                System.out.println("VIP");
                break;
        }
        
        System.out.println("=== BOLETA DE VENTA ===");
        System.out.println("Asiento:            " + (asientoComprado +1));
        System.out.println("Tarifa:             " + tipoCliente);
        System.out.println("Monto a pagar:      " + precioFinal);
        System.out.println("=== GRACIAS POR SU COMPRA ===");

    }

    // main
    public static void main(String[] args) {
        Sumativa2 teatro = new Sumativa2(); // instanciación de los metodos teatro.()
        Scanner keyBoard = new Scanner(System.in);

        // Variablel Locales
        int general = 8000;
        int platea = 15000;
        int vip = 30000;
        int totalSala = 100;
        int opcionMenu = 0;
        int opcionFila = 0;
        int precioEntrada = 0;
        boolean seguirComprando = true;

        do { // utilizacion de ciclo do-while para manejar el menu y la iteracion de este

            System.out.println("                     Bienvenido a Teatro Moro                              "); // saludo de bienvenida y despliegue de menu de opciones
            System.out.println("===SI ERES ESTUDIANTE O TERCERA EDAD TENDRAS UN DESCUENTO EN TU ENTRADA=== ");
            System.out.println(" ");
            System.out.println("Eliga una opcion:");
            System.out.println("1. Comprar Entrada.");
            System.out.println("2. Imprimir Boleta.");
            System.out.println("3. Entradas disponibles.");
            System.out.println("4. Busca tu entrada.");
            System.out.println("5. Eliminar una entrada.");
            System.out.println("6. Venta total.");
            System.out.println("7. Salir.");

            opcionMenu = keyBoard.nextInt();

            switch (opcionMenu) { // utilizacion de switch para ejecutar las sentencias dependiendo de la opcion elegida por el usuario
                case 1:
                    System.out.println("Eliga un tipo de entrada:");
                    System.out.println("""
                                       1. General.
                                       2. Platea.
                                       3. VIP.
                                       """);
                    opcionFila = keyBoard.nextInt();
                    if (opcionFila == 1) { //utilizacion de else-if para validar el tipo de entrada que desea el usuario
                        precioEntrada = general;
                    } else if (opcionFila == 2) {
                        precioEntrada = platea;
                    } else if (opcionFila == 3) {
                        precioEntrada = vip;
                    } else {
                        System.out.println("Ingrese una opcion valida.");
                        break;
                    }
                    teatro.tipoClienteBoleta = tipoCliente; // breakpoint para asegurar que la variable del metodo de la boleta (cliente) es asignado correctamente

                    System.out.println("Selecciones un asiento (del 1 al 100)");
                    int asiento = keyBoard.nextInt();
                    asiento--; // resta 1 a la variable "asiento" para adaptar el numero al indice del array

                    if (asiento >= 0 && asiento < 100 && teatro.entradas[asiento] == null) { // breakpoint para asegurarnos de que la asignacion del asiento es correcta
                        teatro.entradas[asiento] = "Comprado"; // asignacion de valor al array para que no quede en null
                        entradasVendidas++;
                        teatro.entradasDisponibles--;

                        System.out.println("Ingrese su tipo de tarifa:");
                        System.out.println("1. Estudiante");
                        System.out.println("2. Tercera Edad");
                        System.out.println("3. General(Sin descuento)");
                        tipoCliente = keyBoard.nextInt();

                        teatro.darMontoFinal(precioEntrada, tipoCliente); // utilizacion de metodo para mostrar el valor final de la entrada dependiendo de la tarifa elegida
                        System.out.println(" ");
                        System.out.println("Gracias por comprar en teatro moro!");
                        
                        teatro.asientoComprado = asiento; // breakpoint para asegurar que la variable del metodo de la boleta (asiento) es asignado correctamente

                    } else {
                        System.out.println("Asiento no disponible o no valido.");
                    }
                    break;

                case 2:
                    System.out.println(" ");
                    teatro.imprimirBoleta();
                    break;

                case 3:
                    teatro.mostrarEntradas();
                    break;

                case 4:
                    System.out.println("Por favor ingrese su numero de asiento.");
                    asiento = keyBoard.nextInt();
                    asiento--; // resta 1 a la variable "asiento" para adaptar el numero al indice del array
                    teatro.buscarEntradas(asiento);
                    break;

                case 5:
                    System.out.println("Por favor ingrese su numero de asiento.");
                    asiento = keyBoard.nextInt();
                    asiento--; // resta 1 a la variable "asiento" para adaptar el numero al indice del array
                    teatro.eliminarEntrada(asiento);
                    break;

                case 6:
                    System.out.println("Venta total de la sesion: " + totalVendido);
                    break;

                case 7:
                    break;

                default:
                    System.out.println("Opcion invalida. Vuelva a intentarlo por favor.");
                    break;
            }

            int continuar; //Creacion de variable para consultar si el usuario desea seguir comprando

            do { // uso de otro ciclo do-while para preguntarle al usuario si desea comprar otra entrada o finalizar el programa

                System.out.println(" ");
                System.out.println("Desea realizar otra compra?");
                System.out.println("1. Si.");
                System.out.println("2. No.");
                System.out.println(" ");
                continuar = keyBoard.nextInt();

                if (continuar == 1) {
                    seguirComprando = true;
                } else if (continuar == 2) {
                    System.out.println("Gracias por preferir Teatro Moro.");
                    seguirComprando = false;
                } else {
                    System.out.println("Ingrese una opcion valida.");
                }

            } while (continuar != 1 && continuar != 2);

        } while (seguirComprando); // Iteracion del ciclo Do-While

        keyBoard.close(); // Cierre del Scanner para liberar recursos

    }

}
//ik