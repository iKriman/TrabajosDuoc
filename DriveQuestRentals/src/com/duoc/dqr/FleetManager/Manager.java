package com.duoc.dqr.FleetManager;

import com.duoc.dqr.cars.CargoVehicle;
import com.duoc.dqr.cars.PassengersVehicle;
import com.duoc.dqr.cars.Vehicle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Manager {

    private static final String FLEET_CSV = "fleet.csv";
    private Map<String, Vehicle> fleetLicense;
    private List<Vehicle> fleet;

    private final Object fleetLock = new Object();

    public Manager(Map<String, Vehicle> fleetLicense, List<Vehicle> fleet) {
        this.fleetLicense = fleetLicense;
        this.fleet = fleet;

        fleetFileLoader();
    }

    // inicializacion de hilo
    private void threadStart() {
        Thread loaderThread = new Thread(() -> {
            fleetFileLoader();
        });
        loaderThread.setName("VehicleLoaderThread"); // 
        loaderThread.start();
    }

    // METODOS 
    public void fleetFileLoader() {

        ExecutorService executor = Executors.newFixedThreadPool(4);

        try (BufferedReader br = new BufferedReader(new FileReader("fleet.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String currentLine = line;
                if (!currentLine.trim().isEmpty()) {
                    executor.submit(() -> {
                        Vehicle vehicle = parseLine(currentLine);
                        if (vehicle != null) {
                            synchronized (fleetLock) {
                                fleet.add(vehicle);
                                fleetLicense.put(vehicle.getLicensePlate(), vehicle);
                            }
                        }
                    });
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR al cargar el archivo de flota: " + e.getMessage());
        } finally {
            executor.shutdown();
            try {

                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("La carga de vehículos excedió el tiempo de espera.");
                }
            } catch (InterruptedException e) {
                System.err.println("La carga de vehículos fue interrumpida: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    private void loadVehicleLine(String line) {
        Vehicle loadedVehicle = parseLine(line);
        if (loadedVehicle != null) {
            synchronized (fleetLock) {
                if (!fleetLicense.containsKey(loadedVehicle.getLicensePlate())) {
                    fleet.add(loadedVehicle);
                    fleetLicense.put(loadedVehicle.getLicensePlate(), loadedVehicle);

                } else {
                    System.out.println("Patente duplicada: " + loadedVehicle.getLicensePlate());
                }
            }
        } else {
            System.out.println("ERROR al parsear la linea: " + line);
        }
    }

    private Vehicle parseLine(String line) {
        String[] info = line.split(","); // parseamos por coma
        if (info.length < 6) {
            System.out.println("Linea del archivo CSV incompleta: " + line);
            return null;
        }
        try {
            String licensePlate = info[0].trim();
            String brand = info[1].trim();
            String model = info[2].trim();
            int year = Integer.parseInt(info[3].trim());
            int daysRented = Integer.parseInt(info[4].trim());
            String vehicleType = info[5].trim().toLowerCase();

            if (vehicleType.equals("cargo")) {
                return new CargoVehicle(licensePlate, brand, model, year, daysRented);
            } else if (vehicleType.equals("passenger")) {
                return new PassengersVehicle(licensePlate, brand, model, year, daysRented);
            } else {
                System.out.println("ERROR al parsear vehiculos: " + line);
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROR de formato: " + line + " - " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("ERROR al parsear el archivo CSV: " + line + " - " + e.getMessage());
            return null;
        }
    }

    private void saveVehicleToCSV() {
        synchronized (fleetLock) {
            try (FileWriter fw = new FileWriter(FLEET_CSV)) {

                for (Vehicle v : fleet) {
                    String vehicleFileLine = v.toFileString();
                    fw.write(vehicleFileLine);
                    fw.write("\n");
                }
                System.out.println("Flota guardada exitosamente.");
            } catch (IOException e) {
                System.out.println("ERROR al tratar de guardar el archivo: " + e.getMessage());
            }
        }
    }

    public boolean addVehicle(Vehicle newVehicle) {
        if (newVehicle == null) {
            System.out.println("ERROR no se puede agregar este vehiculo");
            return false;
        }
        try {
            synchronized (fleetLock) {
                if (fleetLicense.containsKey(newVehicle.getLicensePlate())) {
                    System.out.println("ERROR la patente: " + newVehicle.getLicensePlate() + " ya se encuentra registrada en el sistema");
                    return false;
                } else {
                    fleet.add(newVehicle);
                    fleetLicense.put(newVehicle.getLicensePlate(), newVehicle);
                    saveVehicleToCSV();
                    System.out.println("Vehiculo agregado exitosamente!");
                    return true;

                }
            }
        } catch (Exception e) {
            System.out.println("ERROR al tratar de agregar el vehiculo: " + e.getMessage());
            return false;
        }
    }

    public boolean rentVehicle(String licensePlate, int days) {
        synchronized (fleetLock) {
            Vehicle v = findLicensePlate(licensePlate);
            if (v == null) {
                System.out.println("ERROR vehiculo con la patente " + licensePlate + " no pudo ser encontrado");
                return false;
            }
            if (v.getDaysRented() > 0) {
                System.out.println("ERROR vehiculo con la patente " + licensePlate + " ya se encuentra arrendado.");
                return false;
            }
            v.setDaysRented(days);
            saveVehicleToCSV();
            System.out.println("Vehiculo " + licensePlate + " arrendado por " + days + " dias.");
            
            v.printInvoice(); 
            
            return true;
        }
    }

    public boolean returnVehicle(String licensePlate) {
        synchronized (fleetLock) {
            Vehicle v = findLicensePlate(licensePlate);
            if (v == null) {
                System.out.println("ERROR vehiculo con la patente " + licensePlate + " no pudo ser encontrado");
                return false;
            }
            if (v.getDaysRented() == 0) {
                System.out.println("ERROR vehiculo con la patente " + licensePlate + " no se encuentra arrendado");
                return false;
            }
            v.setDaysRented(0);
            saveVehicleToCSV();
            System.out.println("Vehiculo devuelto exitosamente!");
            return true;
        }
    }

    public Vehicle findLicensePlate(String licensePlate) {
        synchronized (fleetLock) {
            return fleetLicense.get(licensePlate);
        }
    }

    public List<String> licensePlatesByType(String vehicleType) {
        List<String> plates = new ArrayList<>();
        synchronized (fleetLock) {
            for (Vehicle v : fleet) {
                if (vehicleType.equalsIgnoreCase("cargo") && v instanceof CargoVehicle) {
                    plates.add(v.getLicensePlate());
                } else if (vehicleType.equalsIgnoreCase("passenger") && v instanceof PassengersVehicle) {
                    plates.add(v.getLicensePlate());
                }
            }
        }
        return plates;
    }

    public List<Vehicle> longRentals() {
        synchronized (fleetLock) {
            List<Vehicle> longRental = new ArrayList<>();
            for (Vehicle v : fleet) {
                if (v.getDaysRented() >= 7) {
                    longRental.add(v);
                }
            }
            return longRental;
        }
    }

    public List<Vehicle> listVehicles() {
        synchronized (fleetLock) {
            return new ArrayList<>(fleet);
        }
    }

}
