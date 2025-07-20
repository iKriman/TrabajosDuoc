package com.duoc.dqr.cars;

import com.duoc.dqr.interfaces.IRental;

public class CargoVehicle extends Vehicle implements IRental {

    private static final int FIXED_RATE = 50000;
    private static final int CARGO_CAPACITY = 1000;

    public CargoVehicle(String licensePlate, String brand, String model, int year, int daysRented) {
        super(licensePlate, brand, model, year, daysRented);

    }

    public CargoVehicle() {
    }

    //METODOS
    public void BasicInfo(String brand, int year, String licensePlate) {
        System.out.println("Patente: " + licensePlate);
        System.out.println("Marca: " + brand);
        System.out.println("Anio: " + year);
    }

    @Override
    public void VehicleInfo() {
        System.out.println("===Informacion del Vehiculo===");
        System.out.println("Patente: " + getLicensePlate());
        System.out.println("Marca: " + getBrand());
        System.out.println("Modelo: " + getModel());
        System.out.println("Valor Diario: " + "$" + FIXED_RATE);
        System.out.println("Capacidad de Carga: " + CARGO_CAPACITY + "Kg");
    }

    @Override
    public void printInvoice() {
        System.out.println("-===DRRIVE QUEST RENTALS===-");
        System.out.println("-      === BOLETA ===      -");
        System.out.println("- " + "Dias Arrendados: " + getDaysRented());
        System.out.println("- " + "Costo Total: " + totalCost());
        System.out.println("- " + "Marca: " + getBrand());
        System.out.println("- " + "anio: " + getYear());
        System.out.println("- " + "Patente: " + getLicensePlate());
    }

    @Override
    public double totalCost() {
        double base = (getDaysRented() * FIXED_RATE);
        double descuento = (base * DESCUENTO_CARGA);
        double iva = ((base - descuento) * IVA);
        return ((base - descuento) + iva);

    }

    @Override
    public String toFileString() {
        return getLicensePlate() + "," + getBrand() + "," + getModel() + "," + getYear() + "," + getDaysRented() + "," + "cargo";
    }

}
