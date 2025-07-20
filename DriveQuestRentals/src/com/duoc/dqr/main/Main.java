package com.duoc.dqr.main;

import com.duoc.dqr.FleetManager.Manager;
import com.duoc.dqr.cars.CargoVehicle;
import com.duoc.dqr.cars.PassengersVehicle;
import com.duoc.dqr.cars.Vehicle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Map<String, Vehicle> fleetLicense = new HashMap<>();
        List<Vehicle> fleet = new ArrayList<>();

        Manager manager = new Manager(fleetLicense, fleet);
        //List<Vehicle> vehicles = manager.listVehicles();

        Scanner sc = new Scanner(System.in);
        boolean anotherOperation = true;
        int menuOption;

        do {
            while (true) {
                System.out.println("=== DRIVE QUEST RENTALS ===");
                System.out.println("1. Arrendar Vehiculo");
                System.out.println("2. Devolver Vehiculo Arrendado.");
                System.out.println("3. Agregar Vehiculo a la Flota.");
                System.out.println("4. Lista de Arriendos Mayores a 7 Dias.");
                System.out.println("5. Lista de Vehiculos.");
                System.out.println("6. Salir.");
                System.out.print("Eliga una opcion: ");
                System.out.println(" ");

                try {
                    menuOption = sc.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Ingrese una opcion valida del menu.");
                    sc.nextLine();
                }
            }

            sc.nextLine();

            switch (menuOption) {
                case 1: // Arrendar Vehiculo
                    System.out.println("=== Arrendar Vehiculo ===");
                    System.out.println(" ");
                    int chosenVehicle = 0;
                    String vehicleTypes = "";
                    do {
                        System.out.println("Eliga un vehiculo para arrendar: ");
                        System.out.println("1. Cargo.");
                        System.out.println("2. Pasajeros.");
                        System.out.println(" ");
                        try {
                            chosenVehicle = sc.nextInt();
                            sc.nextLine();
                            if (chosenVehicle == 1) {
                                vehicleTypes = "cargo";
                            } else if (chosenVehicle == 2) {
                                vehicleTypes = "pasajero";
                            } else {
                                System.out.println("ERROR opcion invalida por favor elija 1 o 2.");
                                System.out.println(" ");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("ERROR ingrese un numero valido");
                            sc.nextLine();
                            chosenVehicle = 0;
                        }
                    } while (chosenVehicle < 1 || chosenVehicle > 2);

                    List<String> platesTypes = manager.licensePlatesByType(vehicleTypes);
                    List<Vehicle> availableVehicle = new ArrayList<>();

                    for (String plate : platesTypes) {
                        Vehicle v = manager.findLicensePlate(plate);
                        if (v != null && v.getDaysRented() == 0) {
                            availableVehicle.add(v);
                        }
                    }

                    if (availableVehicle.isEmpty()) {
                        System.out.println("No hay vehiculos de " + availableVehicle + " disponibles.");
                    } else {
                        System.out.println("Vehiculos disponibles: ");
                        for (Vehicle v : availableVehicle) {
                            System.out.println("Patente: " + v.getLicensePlate()
                                    + ", Marca: " + v.getBrand()
                                    + ", Modelo: " + v.getModel()
                                    + ", Anio: " + v.getYear());
                        }
                    }

                    System.out.print("Ingrese la patente del vehiculo que desee arrendar: ");
                    String rentVehiclePlate = sc.nextLine().toUpperCase();;
                    int rentDays = 0;
                    try {
                        System.out.print("Ingrese la cantidad de dias a arrendar: ");
                        rentDays = sc.nextInt();
                        sc.nextLine();
                        if (rentDays <= 0) {
                            System.out.println("ERROR debe arrendar a lo menos un dia.");
                        } else {
                            manager.rentVehicle(rentVehiclePlate, rentDays);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("ERROR ingrese un numero valido de dias.");
                        sc.nextLine();
                    }

                    break;

                case 2:  // Devolver Vehiculo Arrendado
                    System.out.print("Ingrese la patente del vehiculo a devolver : ");
                    String returnVehiclePlate = sc.nextLine().toUpperCase();;
                    manager.returnVehicle(returnVehiclePlate);
                    break;

                case 3: // Agregar Vehiculo a la Flota
                    System.out.println("Ingrese los datos para ser agregados: ");
                    System.out.print("Tipo de Vehiculo: Cargo o Pasajero: ");
                    String type = sc.nextLine();
                    System.out.print("Patente: ");
                    String licensePlate = sc.nextLine();
                    System.out.print("Marca: ");
                    String brand = sc.nextLine();
                    System.out.print("Modelo: ");
                    String model = sc.nextLine();
                    int year = 0;

                    try {
                        System.out.print("anio: ");
                        year = sc.nextInt();
                        sc.nextLine();

                        if (type.equalsIgnoreCase("cargo")) {
                            CargoVehicle newCV = new CargoVehicle(licensePlate, brand, model, year, 0);
                            manager.addVehicle(newCV);
                        } else if (type.equalsIgnoreCase("pasajero")) {
                            PassengersVehicle newPV = new PassengersVehicle(licensePlate, brand, model, year, 0);
                            manager.addVehicle(newPV);
                        } else {
                            System.out.println("ERROR tipo de vehiculo invalido, agregar Cargo o Pasajero.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("ERROR ingrese un valor numerico.");
                        sc.nextLine();
                    }
                    break;

                case 4: // Lista de Arriendos Mayores a 7 Dias
                    System.out.println("Cantidad de arriendos mayores a 7 dias: ");
                    List<Vehicle> longRentals = manager.longRentals();
                    if (longRentals.isEmpty()) {
                        System.out.println("No hay vehiculos arrendados por 7 o mas dias.");
                    } else {
                        for (Vehicle v : longRentals) {
                            v.VehicleInfo();

                        }
                    }
                    break;

                case 5: // Lista de Vehiculos
                    System.out.println("Flota de Drive Quest Rentals: ");
                    List<Vehicle> allVehicles = manager.listVehicles();
                    if (allVehicles.isEmpty()) {
                        System.out.println("La flota esta vacia.");
                    } else {
                        for (Vehicle v : allVehicles) {
                            v.VehicleInfo();
                        }
                    }

                    break;

                case 6: // Salir
                    System.out.println("Gracias Por Preferirnos!");
                    anotherOperation = false;
                    break;

                default:
                    System.out.println("Opcion invalida porfavor vuelva a intentarlo.");
                    break;
            }

            if (menuOption != 4) {
                int continueOption;
                do {
                    System.out.println("\nLe gustaria hacer otra operacion?");
                    System.out.println("1. Si.");
                    System.out.println("2. No.");

                    try {
                        continueOption = sc.nextInt();
                        if (continueOption == 1) {
                            anotherOperation = true;
                        } else if (continueOption == 2) {
                            System.out.println("Gracias Por Preferirnos!");
                            anotherOperation = false;
                        } else {
                            System.out.println("Ingrese una opcion valida.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error, ingrese un numero valido.");
                        sc.nextLine();
                        continueOption = 0;
                    }
                } while (continueOption != 1 && continueOption != 2);
            }
        } while (anotherOperation);

        sc.close();
    }

}
//ik

