// Ignacio Kriman Bravo Trabajo Transversal
// Creacion de sistema de ventas de entradas para teatro con opcion de reserva y eliminacion de asientos e impresion de boleta con identificadores de venta y cliente.
package com.mycompany.transversal;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Transversal {

    // Variables estaticas
    static int entradasVendidas = 0;
    static int totalVendido = 0;
    static int tipoCliente;
    static int tipoClienteBoleta;
    static String[] entradas = new String[100]; // Para simular asientos vendidos, el teatro contara con asientos del 1 al 100
    static ArrayList<Compra> comprasRealizadas = new ArrayList<>(); 
    static ArrayList<Reservas> reservas = new ArrayList<>();
    static Scanner teclado = new Scanner(System.in);

    // variable de instancia
    int entradasDisponibles = 100;
    int precioFinal;
    int asientoComprado = -1; // variable para definir el asiento en la boleta



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
        int edadCliente;
        int generoCliente;
        int esEstudiante;

        public Compra(int asiento, int precioFinalC, int tipoCliente, int agregarReserva, int edadCliente, int generoCliente, int esEstudiante) {

            this.asiento = asiento + 1;
            this.precioFinalC = precioFinalC;
            this.tipoCliente = tipoCliente;
            this.agregarReserva = agregarReserva;
            this.edadCliente = edadCliente;
            this.generoCliente = generoCliente;
            this.esEstudiante = esEstudiante;
        }
        
        public int getAsiento(){return asiento;}
        public int getPrecioFinalC() {return precioFinalC;}
        public int getTipoCliente() {return tipoCliente;}
        public int getAgregarReserva() {return agregarReserva;}
        public int getEdadCliente() {return edadCliente;}
        public int getGeneroCliente (){return generoCliente;}
        public int getEsEstudiante () {return esEstudiante;}
    }

    public void mostrarEntradas() { // metodo para mostrar la cantidad de entradas disponibles.
        System.out.println("Entradas disponibles: " + entradasDisponibles);
    }

    public int darMontoFinal(int precioBase, int tipoCliente, int edadCliente, int generoCliente, int esEstudiante) {  // metodo para calcular precio final de entradas si aplican descuento
        // debbug point para verificar precioBase y parámetros de cliente
        this.precioFinal = precioBase;
        
        if (edadCliente <= 12) {
            double tarifaNino = (precioBase * 0.90);
            this.precioFinal = (int) Math.min(precioFinal, tarifaNino); // verificacion precio calculado
        }
        
        if (esEstudiante == 1) {
            double tarifaEstudiante = (precioBase * 0.85);
            this.precioFinal = (int) Math.min(precioFinal, tarifaEstudiante); // verificacion precio calculado
        }
        
        if (generoCliente == 1) {
            double tarifaMujer = (precioBase * 0.80);
            this.precioFinal = (int) Math.min(precioFinal, tarifaMujer); // verificacion precio calculado
        }
        
        if (edadCliente >= 65) {
            double tarifaTerceraEdad = (precioBase * 0.75);
            this.precioFinal = (int) Math.min(precioFinal, tarifaTerceraEdad); // verificacion precio calculado
        }
        
        
        totalVendido += precioFinal;
        System.out.println("Monto por pagar: " + precioFinal);
        return precioFinal;
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
    
    public static String tarifaCorrespondiente(int edadCliente, int generoCliente, int esEstudiante) {

        String tarifaCliente = "General"; // creacion de variable string para ser utilizada en la impresion de la boleta

        if (edadCliente >= 65) {
            tarifaCliente = "Tercera Edad";
        } else if (generoCliente == 1) {
            tarifaCliente = "Mujer";
        } else if (esEstudiante == 1) {
            tarifaCliente = "Estudiante";
        } else if (edadCliente <= 12) {
            tarifaCliente = "Niño";
        }

        return tarifaCliente; // verificamos retorno correcto de tarifaCliente

    }

    public void imprimirBoleta() {

        if (comprasRealizadas.isEmpty()) { 
            System.out.println("Aun no hay ninguna compra.");
            return;
        }
        // uso de iteracion tanto en las compras como en las reservas para verificar que no esten vacias 
        if (reservas.isEmpty()) {
            System.out.println("Aun no hay ninguna reserva.");
            return;
        }
        
        // Obtencion de las ultimas transacciones
        Compra ultimaCompra = comprasRealizadas.get(comprasRealizadas.size() - 1); // verificacion de ultima compra y ultima reserva
        Reservas ultimaReserva = reservas.get(reservas.size() - 1);

        // obtencion de datos de la venta
        int ultimoAsiento = ultimaCompra.getAsiento();
        int montoAPagar = ultimaCompra.getPrecioFinalC();

        // obtencion de datos de la reserva
        int idReserva = ultimaReserva.getIdReserva();
        String idCliente = ultimaReserva.getIdCliente();

        // obtencion de datos del cliente
        int edadCliente = ultimaCompra.getEdadCliente();
        int generoCliente = ultimaCompra.getGeneroCliente();
        int esEstudiante = ultimaCompra.getEsEstudiante();

        // obtencion tarifa correspondiente 
        String tarifaBoleta = tarifaCorrespondiente(edadCliente, generoCliente, esEstudiante);
        

        // impresion de boleta (opcion 2. en el menu interactivo)
        System.out.println(" ");
        System.out.println("=== BOLETA DE VENTA ===");
        System.out.println("ID Reserva:         " + ultimaReserva.getIdReserva());
        System.out.println("Cliente:            " + ultimaReserva.getIdCliente());
        System.out.println("Asiento:            " + ultimaCompra.getAsiento());
        System.out.println("Tarifa:             " + tarifaBoleta);
        System.out.println("Monto a pagar:      " + precioFinal);
        System.out.println("=== GRACIAS POR SU COMPRA ===");
        System.out.println(" ");

    }

    // main
    public static void main(String[] args) {
        Transversal teatro = new Transversal(); // instanciación de los metodos teatro.()
        Scanner teclado = new Scanner(System.in);

        // Variablel Locales
        int galeria = 5000;
        int plateaBaja= 15000;
        int plateaAlta = 20000;
        int palco = 18000;
        int vip = 30000;
        int opcionMenu = 0;
        int opcionFila = 0;
        int precioEntrada = 0;
        boolean seguirComprando = true;

        do { // utilizacion de ciclo do-while para manejar el menu y la iteracion de este

            while (true) { // aplicacion de ciclo while con try-catch para evitar errores de tipeo en el codigo
                
                System.out.println(" ");
                System.out.println("                     Bienvenido a Teatro Moro       "); // saludo de bienvenida y despliegue de menu de opciones
                System.out.println("              ===DESCUENTOS ESPECIALES PARA TODOS===");
                System.out.println(" ");
                System.out.println("========================");
                System.out.println("===-------MENÚ-------===");
                System.out.println("========================");
                System.out.println("1. Comprar Entrada.");
                System.out.println("2. Imprimir Boleta.");
                System.out.println("3. Entradas Disponibles.");
                System.out.println("4. Busca Tu Entrada.");
                System.out.println("5. Eliminar Una Entrada.");
                System.out.println("6. Ver Compras.");
                System.out.println("7. Salir.");
                System.out.println("========================");

                try {
                    opcionMenu = teclado.nextInt(); // verificacion correcta de la opcion del menÚ
                    break;
                } catch (InputMismatchException error) {
                    System.out.println("Error, ingrese una opcion valida.");
                    System.out.println(" ");
                    teclado.nextLine();
                }
            }

            OUTER:
            switch (opcionMenu) {
                case 1:
                    while (true) {
                        System.out.println("Elija un tipo de entrada:");
                        System.out.println("""
                                                                   1. Galeria.
                                                                   2. Platea Baja.
                                                                   3. Platea Alta.
                                                                   4. Palco.
                                                                   5. VIP.
                                                                   """);
                        try {
                            opcionFila = teclado.nextInt();
                            break; // chequeamos que la opcionFila se maneje de manera correcta
                        } catch (InputMismatchException error) {
                            System.out.println("Error, opción invalida vuelva a intentarlo.");
                            System.out.println(" ");
                            teclado.nextLine();
                        }
                    }
                    switch (opcionFila) {
                        case 1 -> precioEntrada = galeria;
                        case 2 -> precioEntrada = plateaBaja;
                        case 3 -> precioEntrada = plateaAlta;
                        case 4 -> precioEntrada = palco;
                        case 5 -> precioEntrada = vip;
                        default -> {
                            System.out.println("Ingrese una opción valida.");
                            break OUTER;
                    }
                    }
                    teatro.tipoClienteBoleta = tipoCliente;
                    System.out.println("Selecciones un asiento (del 1 al 100)");
                    int asiento = teclado.nextInt();
                    asiento--;
                    if (asiento >= 0 && asiento < 100 && teatro.entradas[asiento] == null) { // breakpoint para asegurarnos de que la asignacion del asiento es correcta
                        teatro.entradas[asiento] = "Comprado"; // asignacion de valor al array para que no quede en null
                        entradasVendidas++;
                        teatro.entradasDisponibles--;

                        int generoCliente = 0;
                        while (true) {
                            System.out.println("Eliga su género");
                            System.out.println("1. Mujer");
                            System.out.println("2. Hombre");
                            System.out.println("");

                            try {
                                generoCliente = teclado.nextInt();
                                
                                if(generoCliente == 1 || generoCliente == 2) {
                                    teclado.nextLine(); // verificamos ingreso correcto de la alternativa
                                    break;
                                } else {
                                    System.out.println("Opción invalida. Por favor ingrese 1 o 2.");
                                }

                            } catch (InputMismatchException error) {
                                System.out.println("Error, ingrese una opción valida.");
                                System.out.println(" ");
                                teclado.nextLine();
                            }

                        }
                        
                        int edadCliente = 0;
                        while (true) {
                            System.out.println("Ingrese su edad, por favor:");
                            
                            try { 
                                edadCliente = teclado.nextInt(); // chequeamos ingreso correcto de la edad
                                
                                teclado.nextLine();
                                
                                if(edadCliente >= 1 && edadCliente <= 120) {
                                    break;
                                } else {
                                    System.out.println("Ingrese una edad entre 1 y 120.");
                                }
                            } catch (InputMismatchException error) {
                                System.out.println("Error, Ingrese un valor valido.");
                                System.out.println(" ");
                                teclado.nextLine();
                            }
                        }
                        
                        int esEstudiante = 0;
                        String institucion = "";
                        int rut = 0;
                        String rutVerificacion = "";

                        while (true) {
                            System.out.println("Es estudiante?");
                            System.out.println("1. Si");
                            System.out.println("2. No");
                            System.out.println("");

                            try {
                                esEstudiante = teclado.nextInt();
                                teclado.nextLine();

                                if (esEstudiante == 1 || esEstudiante == 2) {
                                    break;
                                } else {
                                    System.out.println("Por favor, ingrese opción 1 o 2.");
                                }

                            } catch (InputMismatchException error) {
                                System.out.println("Error, ingrese una opción valida.");
                                System.out.println(" ");
                                teclado.nextLine();
                            }
                        }

                            
                        if (esEstudiante == 1) {

                            while (true) {
                                System.out.println("Ingrese el nombre de su institución: ");
                                System.out.println(" ");
                                institucion = teclado.nextLine();
                                if (!institucion.trim().isEmpty()) {
                                System.out.println("~~Comprobando Informacion~~");
                                System.out.println(" ");
                                 try { Thread.sleep(1000);} catch (InterruptedException e) {}
                                System.out.println("Buscando institución en base de datos...");
                                System.out.println(" ");
                                 try { Thread.sleep(2000);} catch (InterruptedException e) {}
                                System.out.println("~~Institucion Verificada!~~");
                                break;
                                } else {
                                    System.out.println("Por favor ingrese nombre de la institucion.");
                                }
                            }
                            
                            
                            
                            while (true) {
                                System.out.println(" ");
                                System.out.println("Ingrese su RUT (sin puntos ni guión).");
                                try {
                                    rutVerificacion = teclado.nextLine();
                                    
                 
                                    if (rutVerificacion.length() == 9 && rutVerificacion.matches("\\d{9}")) { // verifica que el rut sea solo de 9 digitos
                                        System.out.println("RUT ingresado correctamente!");
                                        break;
                                    } else {
                                        System.out.println("RUT invalido. Por favor, ingrese un RUT valido.");  
                                    }
                                    
                                } catch (NumberFormatException e) {
                                    System.out.println("RUT invalido. Por favor, ingrese un RUT valido.");
                                    teclado.nextLine();
                                }

                            }
                        }


                        String idCliente;
                        while (true) {

                            System.out.println("Ingrese su nombre:");
                            System.out.println(" ");
                            idCliente = teclado.nextLine();
                            

                            if (idCliente.isEmpty()) {
                                System.out.println("Por favor, ingrese un nombre para seguir con la reserva.");
                                System.out.println(" ");
                                System.out.println(" ");
                            } else if (idCliente.matches(".*\\d.*")) { // verificacion que permite al programa ver si el nombre fue ingresado con números
                                System.out.println("Error, el nombre no puede contener números. Vuelva a intentarlo.");
                                System.out.println(" ");
                            } else {
                                System.out.println("Nombre ingresado correctamente!");
                                System.out.println(" ");
                                break;
                            }

                        }
                        
                       // teclado.nextLine();
               
                        teatro.darMontoFinal(precioEntrada, tipoCliente, edadCliente, generoCliente, esEstudiante); // utilizacion de metodo para mostrar el valor final de la entrada dependiendo de la tarifa elegida
                        System.out.println(" ");
                        System.out.println("Gracias por comprar en teatro moro!");

                        teatro.asientoComprado = asiento; // breakpoint para asegurar que la variable del metodo de la boleta (asiento) es asignado correctamente
                        Reservas nuevaReserva = new Reservas(idCliente);
                        reservas.add(nuevaReserva);
                        Compra compra = new Compra(asiento, teatro.precioFinal, tipoCliente, nuevaReserva.getIdReserva(), edadCliente, generoCliente, esEstudiante); // se agregan todas las variables correspondiente a la clase "compra" 
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
                        System.out.println("Por favor, ingrese su número de asiento.");

                        try {
                            asiento = teclado.nextInt();
                            break;
                        } catch (InputMismatchException error) {
                            System.out.println("Error, ingrese un número de asiento valido.");
                            System.out.println(" ");
                            teclado.nextLine();
                        }

                    }
                    asiento--; // resta 1 a la variable "asiento" para adaptar el numero al indice del array
                    teatro.buscarEntradas(asiento); 
                    break;
                case 5:

                    while (true) {

                        System.out.println("Por favor, ingrese su número de asiento.");

                        try {
                            asiento = teclado.nextInt();
                            break;

                        } catch (InputMismatchException error) {
                            System.out.println("Error, ingrese un número de asiento valido.");
                            System.out.println(" ");
                            teclado.nextLine();
                        }
                    }
                    asiento--; // resta 1 a la variable "asiento" para adaptar el numero al indice del array
                    teatro.eliminarEntrada(asiento); // chequeo de eliminacion del asiento
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

                                    System.out.println("Asiento: " + c.asiento);
                                    
                                    // obtencion de datos del cliente
                                    int edadCliente = c.getEdadCliente();
                                    int generoCliente = c.getGeneroCliente();
                                    int esEstudiante = c.getEsEstudiante();

                                    // obtencion tarifa correspondiente 
                                    String tarifaBoleta = tarifaCorrespondiente(
                                            edadCliente, 
                                            generoCliente, 
                                            esEstudiante
                                    );
   
                                    System.out.println("Tarifa: " + tarifaBoleta); // verificación de la tarifa correspondiente a la venta actual
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
                    System.out.println("Opción invalida. Vuelva a intentarlo.");
                    break;
            }

            int continuar; //Creacion de variable para consultar si el usuario desea seguir comprando

            do { // uso de otro ciclo do-while para preguntarle al usuario si desea comprar otra entrada o finalizar el programa

                while (true) { // creacion de una iteracion try-catch para control de errores

                    System.out.println(" ");
                    System.out.println("Desea realizar otra operación?");
                    System.out.println("1. Si.");
                    System.out.println("2. No.");
                    System.out.println(" ");

                    try {
                        continuar = teclado.nextInt();
                        break;

                    } catch (InputMismatchException error) {
                        System.out.println("Error, ingrese un número valido");
                        teclado.nextLine();
                    }

                }

                if (continuar == 1) {
                    seguirComprando = true;
                } else if (continuar == 2) {
                    System.out.println("Gracias por preferir Teatro Moro.");
                    seguirComprando = false;
                } else {
                    System.out.println("Ingrese una opción valida.");
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