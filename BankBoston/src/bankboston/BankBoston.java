package bankboston;

import java.util.InputMismatchException; // importacion inputmismatch para control de errores
import java.util.Scanner; // importacion de scanner para inputs del usuario

public class BankBoston {
    // MAIN
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in); // Objeto scanner

        Cliente cliente1 = null;


        
        boolean otraOperacion = true; // boolean de iteracion while final para realizar otra operacion
        int opcionMenu; // variable local para el menu 
        
        
        do { // ciclo do-while para realizar la cantidad de operaciones que el usario desee
            
            while (true) { // uso de trycatch para manejo de errores
                System.out.println("===Bienvenido a Boston Bank===");
                System.out.println(" ");
                System.out.println("1. Registrar Cliente.");
                System.out.println("2. Datos Cliente.");
                System.out.println("3. Depositar/Pagar Tarjeta de Credito.");
                System.out.println("4. Girar.");
                System.out.println("5. Consultar Saldo.");
                System.out.println("6. Salir.");
                System.out.println(" ");
                try {
                    opcionMenu = teclado.nextInt(); 
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Ingrese una opcion valida.");
                    teclado.nextLine();
                }

            }
            teclado.nextLine();
            switch (opcionMenu) { 
                case 1: // REGISTRAR CLIENTE 
                    System.out.println("Ingrese sus datos: ");
                    String nuevoRut;
                    while (true) {
                        System.out.print("RUT (con puntos y guion): ");
                        nuevoRut = teclado.nextLine();
                        String rutRegex = "^(\\d{1,2}\\.\\d{3}\\.\\d{3}-[0-9Kk])$"; // verificacion para que el RUT tenga un min de 11 y un max de 12 incluyendo . y -
                        if (nuevoRut.matches(rutRegex)) { // validacion con iteracion if-else
                            break;
                        } else {
                            System.out.println("Formato de RUT invalido. Use el formato X.XXX.XXX-X o XX.XXX.XXX-X.");
                        }
                    }
                    
                    System.out.print("Nombre: ");
                    String nuevoNombre = teclado.nextLine();

                    
                    System.out.print("Apellido Paterno: ");
                    String nuevoPaterno = teclado.nextLine();

                    
                    System.out.print("Apellido Materno: ");
                    String nuevoMaterno = teclado.nextLine();

                    
                    System.out.print("Domicilio: ");
                    String nuevoDomicilio = teclado.nextLine();

                    
                    System.out.print("Comuna: ");
                    String nuevaComuna = teclado.nextLine();
                    
                    
                    System.out.print("Telefono: ");
                    int nuevoTelefono;
                    while (true) {
                        try {
                            nuevoTelefono = teclado.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Ingrese un numero telefonico valido.");
                            teclado.nextLine();
                        }
                    }
                    teclado.nextLine();

                    
                    String nuevaCuenta;

                    while (true) {
                        System.out.print("Numero de Cuenta (9 digitos): ");
                        nuevaCuenta = teclado.nextLine();
                        if (nuevaCuenta != null && nuevaCuenta.matches("\\d{9}")) {
                            System.out.println("Cuenta registrada correctamente");
                            break;
                        } else {
                            System.out.println("Error, ingrese un numero de cuenta de 9 digitos");
                            
                        }
                    }



                    Cuenta cuentaCliente = null; 
 
                    int tipoCuenta = 0;
                    while (true) {
                        System.out.println(" ");
                        System.out.println("Seleccione el tipo de cuenta que desea: ");
                        System.out.println("1. Cuenta Corriente.");
                        System.out.println("2. Cuenta de Credito.");
                        System.out.println("3. Cuenta de Ahorro.");
                        System.out.println(" ");
                        try {
                            tipoCuenta = teclado.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Ingrese una opcion valida.");
                            teclado.nextLine();
                        }

                    }

                    int saldoIncial = 0;
                    int cupoCredito = 100000;
                    teclado.nextLine();
                    
                    switch (tipoCuenta) {
                        case 1 -> cuentaCliente = new CuentaCorriente(saldoIncial, nuevaCuenta);
                        case 2 -> cuentaCliente = new CuentaCredito (cupoCredito, saldoIncial, nuevaCuenta);
                        case 3 -> cuentaCliente = new CuentaAhorro (saldoIncial, nuevaCuenta);
                        default -> System.out.println("Eliga una opcion valida.");
                    }
                    
                    System.out.println(" ");
                    System.out.println("Cliente Registrado con Exito.");
                    
                    cliente1 = new Cliente (nuevoRut, nuevoNombre, nuevoPaterno, nuevoMaterno, nuevoDomicilio, nuevaComuna, nuevoTelefono, cuentaCliente);

                    break;

                case 2: // DATOS CLIENTE
                    if (cliente1 != null) {
                        cliente1.infoCliente(); // metodo de la instancia para consultar la info del cliente
                        cliente1.getCuenta().infoCuentaCliente(); // metodo de la instancia para consultar numero de cuenta y dinero disponibble        
                    } else {
                        System.out.println("Aun no hay ningun cliente registrado.");
                    }

                    break;
                case 3: // DEPOSITAR
                    
                    if (cliente1 != null && cliente1.getCuenta() != null) {                  
                    int nuevoDeposito = 0; // variable interina para asignarle un valor al objeto
                    while (true) { // ciclo while con validacion de errores trycatch
                        System.out.print("Ingrese un Monto para Depositar: ");
                        try {
                            nuevoDeposito = teclado.nextInt();
                            teclado.nextLine();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Ingrese un monto valido.");
                            teclado.nextLine();
                        }
                    }
                    cliente1.getCuenta().depositar(nuevoDeposito);
                    } else {
                        System.out.println("Aun no hay ningun cliente registrado.");
                    }
                    System.out.println(" ");
                    System.out.println("Deposito realizado con exito!");

                    break;
                case 4: // GIRAR
                    
                    if (cliente1 != null && cliente1.getCuenta() != null){
                    int nuevoGiro = 0; // variable intertina para asignarle un valor al objeto
                    while (true) { // ciclo while con validacion de errores trycatch
                        System.out.print("Ingrese un monto para girar: ");
                        try {
                            nuevoGiro = teclado.nextInt();
                            teclado.nextLine();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Ingrese un monto valido.");
                            teclado.nextLine();
                        }
                    }
                    
                        System.out.println("Transaccion exitosa!");

                    cliente1.getCuenta().girar(nuevoGiro);
                    } else {
                        System.out.println("Aun no hay ningun cliente registrado.");
                    }
                    break;
                case 5: // CONSULTAR SALDO
                    if (cliente1 != null) {
                        cliente1.getCuenta().infoCuentaCliente();// metodo de la instancia de cuenta para consultar el saldo
                    } else {
                        System.out.println("Aun no hay ningun cliente registrado.");
                    }
                    
                    break;
                case 6: // SALIR
                    break;
                default:
                    System.out.println("Opcion invalida, vuelva a intentarlo.");
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
                        continuar = teclado.nextInt();
                        break;

                    } catch (InputMismatchException error) {
                        System.out.println("Error, ingrese un numero valido");
                        teclado.nextLine();
                    }

                }

                if (continuar == 1) {
                    otraOperacion = true;
                } else if (continuar == 2) {
                    System.out.println("Gracias por preferir Boston Bank.");
                    otraOperacion = false;
                } else {
                    System.out.println("Ingrese una opción valida.");
                }

            } while (continuar != 1 && continuar != 2);


        } while (otraOperacion);
        
        teclado.close();
        

    }

}
