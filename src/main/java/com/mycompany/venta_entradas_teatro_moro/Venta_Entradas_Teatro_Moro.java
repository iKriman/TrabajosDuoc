//Actividad Formativa 4 Ignacio Kriman Bravo
//Creacion de un sistema de ventas de entrada con descuentos y eleccion de asientos utilizando Estrucutras de Control y de Condicionales

package com.mycompany.venta_entradas_teatro_moro;
import java.util.Scanner; //Importacion del objeto Scanner para ser utilizado en el programa

public class Venta_Entradas_Teatro_Moro {

 public static void main (String[] args){
        //Inicializacion del Scanner y de las variables
        Scanner keyBoard = new Scanner (System.in); 
        int opcionMenu = 0;
        int opcionFila = 0;
        int asiento = 0;
        int edad = 0;
        int precioEntrada = 5000; //Descuentos aplicables del 10% para estudiantes y 15% a tercera edad
        boolean seguirComprando = true; //Creacion de variable Booleana para verificar al final del codigo si el usuario desea comprar otra entrada
        
        do { //Utilizacion de ciclo Do-While para la iteracion continua del bloque de codigo en caso de que se desee comprar mas de una entrada
            
            for (int alternativa = 0; alternativa == 0; alternativa++){ //Uso de la estructura de control For para desplegar el menu de opciones 
            
            System.out.println("                     Bienvenido a Teatro Moro                              ");
            System.out.println("===SI ERES ESTUDIANTE O TERCERA EDAD TENDRAS UN DESCUENTO EN TU ENTRADA=== ");
            System.out.println("Eliga una opcion");
            System.out.println("1. Comprar Entrada.");
            System.out.println("2. Salir.");
            opcionMenu = keyBoard.nextInt();
            
        if (opcionMenu == 1) { //Uso de If-Else para eleccion de fila antes de la compra de entradas
            System.out.println(" ");
            System.out.println("Las entradas tiene un valor de $5.000");
            System.out.println(" ");
            System.out.println("Eliga una fila por favor: ");
            System.out.println(""" 
                               1. Fila a: 
                               2. Fila b: 
                               3. Fila c: 
                               4. Salir."""); //Utilizacion de """ para mejorar la visibilidad del menu en el codigo 
            opcionFila = keyBoard.nextInt(); 
            
            switch (opcionFila){ //Uso de Switch para ejecutar las sentencias según la fila elegida
                case 1:
                    System.out.println("Eliga el numero de asiento: ");
                    System.out.println("a1--a2--a3--a4--a5"); //Despliegue del plano de asientos para la elección del usuario
                    asiento = keyBoard.nextInt();
                    
                    if ((asiento == 1) || (asiento == 2) || (asiento == 3) || (asiento == 4) || (asiento == 5)){ //Uso de If en conjunto del Operador Logico || para seguir con el codigo
                        System.out.println("Por favor ingrese su edad: ");
                        edad = keyBoard.nextInt();
                        
                        if (edad < 18){ //Utilizacion de If y Else-If para mostrar descuentos y todo la informacion de la compra
                            System.out.println("Eres estudiante! tienes un 10% de descuento en tu compra");
                            System.out.println("Precio con descuento: $" + (precioEntrada * 0.90)); //Aplicación del porcentaje de descuento al precio de la entrada
                            System.out.println(" ");
                            System.out.println("Resumen: ");
                            System.out.println("Ubicacion: a" + asiento);
                            System.out.println("Precio General: $" + precioEntrada);
                            System.out.println("Se aplico un descuento del 10%");
                            System.out.println("Precio final a pagar: $" + (precioEntrada * 0.90));
                            System.out.println(" ");
                            System.out.println("Gracias por su compra!");
                            System.out.println(" ");
                        }else if (edad >= 60){
                            System.out.println("Eres tercera edad! tienes un 15% de descuento en tu compra");                       
                            System.out.println("Precio con descuento: $" + (precioEntrada * 0.85)); //Aplicación del porcentaje de descuento al precio de la entrada
                            System.out.println(" ");
                            System.out.println("Resumen: ");
                            System.out.println("Ubicacion: a" + asiento);
                            System.out.println("Precio General: $" + precioEntrada);
                            System.out.println("Se aplico un descuento del 15%");
                            System.out.println("Precio final a pagar: $" + (precioEntrada * 0.85));
                            System.out.println(" ");
                            System.out.println("Gracias por su compra!");
                            System.out.println(" ");
                    }else {
                            System.out.println("No aplicas para ningun descuento por rango etario.");
                            System.out.println(" ");
                            System.out.println("Resumen: ");
                            System.out.println("Ubicacion: a" + asiento);
                            System.out.println("Precio General: $" + precioEntrada);
                            System.out.println("No se aplico ningun descuento.");
                            System.out.println(" ");
                            System.out.println("Gracias por su compra!");
                            System.out.println(" ");
                            }
                       
                        
                    }else { //Uso de Else por si el usuario ingresa un numero incorrecto
                        System.out.println("Eliga un número de asiento disponible.");
                    }    
                    break;
                    
                case 2: 
                    System.out.println("Eliga el numero de asiento: ");
                    System.out.println("b1--b2--b3--b4--b5");
                    asiento = keyBoard.nextInt();
                    
                    if ((asiento == 1) || (asiento == 2) || (asiento == 3) || (asiento == 4) || (asiento == 5)){
                        System.out.println("Por favor ingrese su edad: ");
                        edad = keyBoard.nextInt();
                        
                        if (edad < 18){
                            System.out.println("Eres estudiante! tienes un 10% de descuento en tu compra");
                            System.out.println("Precio con descuento: $" + (precioEntrada * 0.90));
                            System.out.println(" ");
                            System.out.println("Resumen: ");
                            System.out.println("Ubicacion: b" + asiento);
                            System.out.println("Precio General: $" + precioEntrada);
                            System.out.println("Se aplico un descuento del 10%");
                            System.out.println("Precio final a pagar: $" + (precioEntrada * 0.90));
                            System.out.println(" ");
                            System.out.println("Gracias por su compra!");
                            System.out.println(" ");
                        }else if (edad >= 60){
                            System.out.println("Eres tercera edad! tienes un 15% de descuento en tu compra");                       
                            System.out.println("Precio con descuento: $" + (precioEntrada * 0.85));
                            System.out.println(" ");
                            System.out.println("Resumen: ");
                            System.out.println("Ubicacion: b" + asiento);
                            System.out.println("Precio General: $" + precioEntrada);
                            System.out.println("Se aplico un descuento del 15%");
                            System.out.println("Precio final a pagar: $" + (precioEntrada * 0.85));
                            System.out.println(" ");
                            System.out.println("Gracias por su compra!");
                            System.out.println(" ");
                    }else {
                            System.out.println("No aplicas para ningun descuento por rango etario.");
                            System.out.println(" ");
                            System.out.println("Resumen: ");
                            System.out.println("Ubicacion: b" + asiento);
                            System.out.println("Precio General: $" + precioEntrada);
                            System.out.println("No se aplico ningun descuento.");
                            System.out.println(" ");
                            System.out.println("Gracias por su compra!");
                            System.out.println(" ");
                            }
                       
                        
                    }else {
                        System.out.println("Eliga un numero de asiento disponible.");
                    }    
                    break;
                    
                case 3:
                    System.out.println("Eliga el numero de asiento: ");
                    System.out.println("c1--c2--c3--c4--c5");
                    asiento = keyBoard.nextInt();
                    
                    if ((asiento == 1) || (asiento == 2) || (asiento == 3) || (asiento == 4) || (asiento == 5)){
                        System.out.println("Por favor ingrese su edad: ");
                        edad = keyBoard.nextInt();
                        
                        if (edad < 18){
                            System.out.println("Eres estudiante! tienes un 10% de descuento en tu compra");
                            System.out.println("Precio con descuento: $" + (precioEntrada * 0.90));
                            System.out.println(" ");
                            System.out.println("Resumen: ");
                            System.out.println("Ubicacion: c" + asiento);
                            System.out.println("Precio General: $" + precioEntrada);
                            System.out.println("Se aplico un descuento del 10%");
                            System.out.println("Precio final a pagar: $" + (precioEntrada * 0.90));
                            System.out.println(" ");
                            System.out.println("Gracias por su compra!");
                            System.out.println(" ");
                        }else if (edad >= 60){
                            System.out.println("Eres tercera edad! tienes un 15% de descuento en tu compra");                       
                            System.out.println("Precio con descuento: $" + (precioEntrada * 0.85));
                            System.out.println(" ");
                            System.out.println("Resumen: ");
                            System.out.println("Ubicacion: c" + asiento);
                            System.out.println("Precio General: $" + precioEntrada);
                            System.out.println("Se aplico un descuento del 15%");
                            System.out.println("Precio final a pagar: $" + (precioEntrada * 0.85));
                            System.out.println(" ");
                            System.out.println("Gracias por su compra!");
                            System.out.println(" ");
                    }else {
                            System.out.println("No aplicas para ningun descuento por rango etario.");
                            System.out.println(" ");
                            System.out.println("Resumen: ");
                            System.out.println("Ubicacion: c" + asiento);
                            System.out.println("Precio General: $" + precioEntrada);
                            System.out.println("No se aplico ningun descuento.");
                            System.out.println(" ");
                            System.out.println("Gracias por su compra!");
                            System.out.println(" ");
                            }
                       
                        
                    }else {
                        System.out.println("Eliga un numero de asiento disponible.");
                    }    
                    break;
                    
                case 4: // En caso de elegir la opcion de salir se da un mensaje de despedida
                    System.out.println("Gracias por visitarnos.");
                    
                    break;
                    
                default: //En caso de que el usario ingrese una opcion invalida
                    System.out.println("Opcion no valida.");
                    break;
            }
            
                //Despliegue de menu para comprar otra entrada          
                System.out.println(" ");  
                System.out.println("Desea realizar otra compra?");
                System.out.println("1. Si.");
                System.out.println("2. No.");
                System.out.println(" ");
                
                int continuar = keyBoard.nextInt(); //Creacion de Variable para consultar si el usuario desea seguir comprando
                if (continuar != 1){ //Uso de Operador Logico != por si el usuario no desea continuar con otra compra
                    System.out.println("Gracias por preferir Teatro Moro.");
                    seguirComprando = false;
                }
        
        }else if (opcionMenu == 2){ //En caso de que el usuario desee salir del programa antes de la eleccion de fila/asiento
                System.out.println("Gracias por preferirnos!");
                seguirComprando = false; //Cambio del valor Booleano de la variable 
                } else {
            System.out.println("Opcion no valida."); //En caso de que el usario agregue una opcion invalida
        }
        break;


        
    }
        }while (seguirComprando); // Iteracion del ciclo Do-While
             
        keyBoard.close(); // Cierre del Scanner para liberar recursos
        
    }
}
//ik
