// Ignacio Kriman Bravo Trabajo Sumativo 3
// Creacion de sistema de ventas de entradas para teatro con opcion de reserva y eliminacion de asientos e impresion de boleta con identificadores de venta y cliente.
package com.mycompany.sumativa3;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Sumativa3 {

    // Variables estaticas
    static int entradasVendidas = 0;
    static int totalVendido = 0;
    static int tipoCliente;
    static String[] entradas = new String[100]; // Para simular asientos vendidos, el teatro contara con asientos del 1 al 100
    static ArrayList<Compra> comprasRealizadas = new ArrayList<>(); // importacion de lista para utilizar guardar compras
    static ArrayList<Reservas> reservas = new ArrayList<>();
    Scanner teclado = new Scanner(System.in);

    // variable de instancia
    int entradasDisponibles = 100;
    int precioFinal;
    int asientoComprado = -1; // variable para definir el asiento en la boleta
    int tipoClienteBoleta = 0; // variable para definir el tipo de cliente en la boleta


    static class Reservas { // creacion de clase para generar identificadores de reserva

        static Random random = new Random(); // importacion del util random 

        String idCliente;
        int idReserva;

        public Reservas (String idCliente) {

            this.idCliente = idCliente; // aca almacenamos el usuario
            this.idReserva = random.nextInt(500) + 1; // aca generamos un numero al azar entre 1 y 500 para dar un id a la reserva 
        }                                                  // (+ 1 ya que si no lanzaria un numero entre 0 y 499)
        
        public String getIdCliente() {
            return idCliente;
        }
        
        public int getIdReserva() {
            return idReserva;
        }
    }

    static class Compra {

        int asiento;
        int precioFinalC;
        int tipoCliente;
        int agregarReserva;

        public Compra(int asiento, int precioFinalC, int tipoCliente, int agregarReserva) {

            this.asiento = asiento + 1;
            this.precioFinalC = precioFinalC;
            this.tipoCliente = tipoCliente;
            this.agregarReserva = agregarReserva;
        }
        
        public int getAsiento(){return asiento;}
        public int getPrecioFinalC() {return precioFinalC;}
        public int getTipoCliente() {return tipoCliente;}
        public int getAgregarReserva() {return agregarReserva;}
    }

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

        if (comprasRealizadas.isEmpty()) { // uso de iteracion if para reforzar y evitar errores al ingresar mal la opcion
            System.out.println("Aun no hay ninguna compra.");
            return;
        }

        Compra ultimaCompra = comprasRealizadas.get(comprasRealizadas.size() - 1);
        
        Reservas ultimaReserva = reservas.get(reservas.size() - 1);

        String tarifaCliente = " "; // creacion de variable string para ser utilizada en la impresion de la boleta
        switch (ultimaCompra.tipoCliente) { // breakpoint nos ayuda a verificar que el Switch arroje el Case correcto

            case 1:
                tarifaCliente = "Estudiante";
                break;
            case 2:
                tarifaCliente = "Tercera Edad";
                break;
            case 3:
                tarifaCliente = "General";
                break;
            default:
                tarifaCliente = "Extraterrestre";
                break;
        }

        System.out.println(" ");
        System.out.println("=== BOLETA DE VENTA ===");
        System.out.println("ID Reserva:         " + ultimaReserva.getIdReserva());
        System.out.println("Cliente:            " + ultimaReserva.getIdCliente());
        System.out.println("Asiento:            " + (asientoComprado + 1));
        System.out.println("Tarifa:             " + tarifaCliente);
        System.out.println("Monto a pagar:      " + precioFinal);
        System.out.println("=== GRACIAS POR SU COMPRA ===");
        System.out.println(" ");

    }

    // main
    public static void main(String[] args) {
        Sumativa3 teatro = new Sumativa3(); // instanciación de los metodos teatro.()
        Scanner teclado = new Scanner(System.in);

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

            while (true) { // aplicacion de ciclo while con try-catch para evitar errores de tipeo en el codigo

                System.out.println("                     Bienvenido a Teatro Moro                              "); // saludo de bienvenida y despliegue de menu de opciones
                System.out.println("===SI ERES ESTUDIANTE O TERCERA EDAD TENDRAS UN DESCUENTO EN TU ENTRADA=== ");
                System.out.println(" ");
                System.out.println("Eliga una opcion:");
                System.out.println("1. Comprar Entrada.");
                System.out.println("2. Imprimir Boleta.");
                System.out.println("3. Entradas disponibles.");
                System.out.println("4. Busca tu entrada.");
                System.out.println("5. Eliminar una entrada.");
                System.out.println("6. Ver Compras.");
                System.out.println("7. Salir.");

                try {
                    opcionMenu = teclado.nextInt();
                    break;
                } catch (InputMismatchException error) {
                    System.out.println("Error, ingrese una opcion valida.");
                    System.out.println(" ");
                    teclado.nextLine();
                }
            }

            switch (opcionMenu) { // utilizacion de switch para ejecutar las sentencias dependiendo de la opcion elegida por el usuario
                case 1:
                    while (true) {
                        System.out.println("Eliga un tipo de entrada:");
                        System.out.println("""
                                       1. General.
                                       2. Platea.
                                       3. VIP.
                                       """);
                        try {
                            opcionFila = teclado.nextInt();
                            break;
                        } catch (InputMismatchException error) {
                            System.out.println("Error, opcion invalida vuelva a intentarlo.");
                            System.out.println(" ");
                            teclado.nextLine();
                        }
                    }

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
                    int asiento = teclado.nextInt();
                    asiento--; // resta 1 a la variable "asiento" para adaptar el numero al indice del array

                    if (asiento >= 0 && asiento < 100 && teatro.entradas[asiento] == null) { // breakpoint para asegurarnos de que la asignacion del asiento es correcta
                        teatro.entradas[asiento] = "Comprado"; // asignacion de valor al array para que no quede en null
                        entradasVendidas++;
                        teatro.entradasDisponibles--;

                        while (true) {
                            System.out.println("Ingrese su tipo de tarifa:");
                            System.out.println("1. Estudiante");
                            System.out.println("2. Tercera Edad");
                            System.out.println("3. General(Sin descuento)");

                            try {
                                tipoCliente = teclado.nextInt();
                                break;

                            } catch (InputMismatchException error) {
                                System.out.println("Error, ingrese una opcion valida.");
                                System.out.println(" ");
                                teclado.nextLine();
                            }

                        }
                        teclado.nextLine();
                        
                        String idCliente;
                        while (true) {

                            System.out.println("Por favor ingrese su nombre:");
                            System.out.println(" ");
                            idCliente = teclado.nextLine();

                            if (idCliente.isEmpty()) {
                                System.out.println("Por favor ingrese un nombre para seguir con la reserva.");
                                System.out.println(" ");
                                System.out.println(" ");
                            } else if (idCliente.matches(".*\\d.*")) {
                                System.out.println("Error, el nombre no puede contener números. Vuelva a intentarlo.");
                                System.out.println(" ");
                            } else {
                                System.out.println("Nombre ingresado correctamente!");
                                System.out.println(" ");
                                break;
                            }

                        }


                        teatro.darMontoFinal(precioEntrada, tipoCliente); // utilizacion de metodo para mostrar el valor final de la entrada dependiendo de la tarifa elegida
                        System.out.println(" ");
                        System.out.println("Gracias por comprar en teatro moro!");

                        teatro.asientoComprado = asiento; // breakpoint para asegurar que la variable del metodo de la boleta (asiento) es asignado correctamente
                        Reservas nuevaReserva = new Reservas(idCliente);
                        reservas.add(nuevaReserva);
                        Compra compra = new Compra(asiento, teatro.precioFinal, tipoCliente, nuevaReserva.getIdReserva());
                        teatro.comprasRealizadas.add(compra); // utilizando este metodo agregamos a la lista con .add cada compra nueva que se haga



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

                    while (true) {
                        System.out.println("Por favor ingrese su numero de asiento.");

                        try {
                            asiento = teclado.nextInt();
                            break;
                        } catch (InputMismatchException error) {
                            System.out.println("Error, ingrese un numero de asiento valido.");
                            System.out.println(" ");
                            teclado.nextLine();
                        }

                    }
                    asiento--; // resta 1 a la variable "asiento" para adaptar el numero al indice del array
                    teatro.buscarEntradas(asiento);
                    break;

                case 5:

                    while (true) {

                        System.out.println("Por favor ingrese su numero de asiento.");

                        try {
                            asiento = teclado.nextInt();
                            break;

                        } catch (InputMismatchException error) {
                            System.out.println("Error, ingrese un numero de asiento valido.");
                            System.out.println(" ");
                            teclado.nextLine();
                        }
                    }
                    asiento--; // resta 1 a la variable "asiento" para adaptar el numero al indice del array
                    teatro.eliminarEntrada(asiento);
                    break;

                case 6: 
                    if (comprasRealizadas.isEmpty()) {
                        System.out.println("No se ha realizado ninguna compra.");
                    } else {

                        for (Reservas r : reservas) {
                            //Reservas ultimaReserva = reservas.get(reservas.size() - 1);
                            System.out.println("==================");
                            System.out.println("ID Reserva: " + r.getIdReserva());
                            System.out.println("Cliente: " + r.getIdCliente());

                            for (Compra c : comprasRealizadas) {

                                if (c.getAgregarReserva() == r.getIdReserva()) {
                                   boolean compraEncontrada = true;

                                    System.out.println("Asiento: " + c.asiento);

                                    switch (c.tipoCliente) {
                                        case 1:
                                            System.out.println("Tarifa: Estudiante");
                                            break;
                                        case 2:
                                            System.out.println("Tarifa: Tercera Edad");
                                            break;
                                        case 3:
                                            System.out.println("Tarifa: General");
                                            break;
                                        default:
                                            System.out.println("Tarifa: Extraterrestre");

                                    }
                                    System.out.println("Precio: " + c.precioFinalC);
                                    System.out.println("==================");
                                    System.out.println(" ");
                                }
                            }
                        }
                    }

                    break;

                case 7:
                    break;

                default:
                    System.out.println("Opcion invalida. Vuelva a intentarlo por favor.");
                    break;
            }

            int continuar; //Creacion de variable para consultar si el usuario desea seguir comprando

            do { // uso de otro ciclo do-while para preguntarle al usuario si desea comprar otra entrada o finalizar el programa

                while (true) { // creacion de una iteracion try-catch para control de errores

                    System.out.println(" ");
                    System.out.println("Desea realizar otra operacion?");
                    System.out.println("1. Si.");
                    System.out.println("2. No.");
                    System.out.println(" ");

                    try {
                        continuar = teclado.nextInt();
                        break;

                    } catch (InputMismatchException error) {
                        System.out.println("Error, ingrese un numero valido");
                        teclado.nextLine();
                    }

                }

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

        teclado.close(); // Cierre del Scanner para liberar recursos

    }

}
//ik


/* Para verificar la optimizacion y estabilidad del sistema se realizaron pruebas 
manuales en distintos escenarios para asegurar estabilidad del programa. esto 
se manejo utilizando validaciones try-catch y por ejemplo en el Case 6 utilice 
un par de iteraciones y la utilizacion de un filtro (c.getAgregarReserva == r.getIdReserva)
para asegurar que el id de las reservas corresponda al de la compra hecha.

Tambien se implementaron puntos de Debugging para verficiar el ingreso correcto 
de valores en variables e iteraciones.

Se uso una lista para manejar los identificadores de reserva los cuales luego de
cada compra utiliza el add. para agregar un nuevo elemento/compra a la lista dando
flexibilidad. El uso de listas y arreglos en el codigo logran que el programa sea 
mas eficiente en especial para el tamaño del teatro.

Se implemento el uso de Getters en algunos metodos para mejor manejo de valores 
y mejorar la robustez como a su ves el mantenimiento del codigo, haciendo que sea
mas facil de modificar y depurar en futuras versiones del codigo. */