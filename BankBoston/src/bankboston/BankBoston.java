package bankboston;

import java.util.InputMismatchException; // importacion inputmismatch para control de errores
import java.util.Scanner; // importacion de scanner para inputs del usuario

public class BankBoston {
    // MAIN
    public static void main(String[] args) {
        
        Scanner teclado = new Scanner(System.in); // Objeto scanner
        
        Cuenta cuenta1 = new Cuenta(); // instancia de la clase Cuenta
        Cliente cliente1 = new Cliente(); // instancia de la clase Cliente
        
        
        boolean otraOperacion = true; // boolean de iteracion while final para realizar otra operacion
        int opcionMenu; // variable local para el menu 
        
        
        do { // ciclo do-while para realizar la cantidad de operaciones que el usario desee
            
            while (true) { // uso de trycatch para manejo de errores
                System.out.println("===Bienvenido a Boston Bank===");
                System.out.println(" ");
                System.out.println("1. Registrar Cliente.");
                System.out.println("2. Datos Cliente.");
                System.out.println("3. Depositar");
                System.out.println("4. Girar");
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
                case 1: // REGISTRAR CLIENTE // se usan los setters para asignar nuevos valores a las variables de instancia
                    System.out.println("Ingrese sus datos: ");
                    String nuevoRut;
                    while (true) {
                        System.out.print("RUT: ");
                        nuevoRut = teclado.nextLine();
                        String rutRegex = "^(\\d{1,2}\\.\\d{3}\\.\\d{3}-[0-9Kk])$"; // verificacion para que el RUT tenga un min de 11 y un max de 12 incluyendo . y -
                        if (nuevoRut.matches(rutRegex)) { // validacion con iteracion if-else
                            break;
                        } else {
                            System.out.println("Formato de RUT invalido. Use el formato X.XXX.XXX-X o XX.XXX.XXX-X.");

                        }

                    }
                    cliente1.setRut(nuevoRut);
                    
                    System.out.print("Nombre: ");
                    String nuevoNombre = teclado.nextLine();
                    cliente1.setNombre(nuevoNombre);
                    
                    System.out.print("Apellido Paterno: ");
                    String nuevoPaterno = teclado.nextLine();
                    cliente1.setApellidoPaterno(nuevoPaterno);
                    
                    System.out.print("Apellido Materno: ");
                    String nuevoMaterno = teclado.nextLine();
                    cliente1.setApellidoMaterno(nuevoMaterno);
                    
                    System.out.print("Domicilio: ");
                    String nuevoDomicilio = teclado.nextLine();
                    cliente1.setDomicilio(nuevoDomicilio);
                    
                    System.out.print("Comuna: ");
                    String nuevaComuna = teclado.nextLine();
                    cliente1.setComuna(nuevaComuna);
                    
                    
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
                    cliente1.setTelefono(nuevoTelefono);
                    teclado.nextLine();
                    
                    System.out.print("Numero de Cuenta: ");
                    int nuevaCuenta;
                    while(true) {
                        try {
                            nuevaCuenta = teclado.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Ingrese un numero de cuenta valido.");
                            teclado.nextLine();
                        }
                    }
                    cliente1.setCuentaCorriente(nuevaCuenta);
                    teclado.nextLine();
                    
                    System.out.println(" ");
                    System.out.println("Cliente Registrado con Exito.");

                    break;
                case 2: // DATOS CLIENTE
                    cliente1.infoCliente(); // metodo de la instancia para consultar la info del cliente
                    cuenta1.infoCuentaCliente(); // metodo de la instancia para consultar numero de cuenta y dinero disponibble 
                    break;
                case 3: // DEPOSITAR
                                      
                    int nuevoDeposito = 0; // variable intertina para asignarle un valor al objeto

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
                    cuenta1.depositar(nuevoDeposito);

                    break;
                case 4: // GIRAR

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

                    cuenta1.girar(nuevoGiro);
                    break;
                case 5: // CONSULTAR SALDO
                    System.out.print("Su saldo actual es de: ");
                    cuenta1.consultarSaldo(); // metodo de la instancia de cuenta para consultar el saldo
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
