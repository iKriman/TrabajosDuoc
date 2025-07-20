package com.duoc.dqr.cars;

public abstract class Vehicle {

    private String licensePlate;
    private String brand;
    private String model;
    private int year;
    private int daysRented;

    public Vehicle(String licensePlate, String brand, String model, int year, int daysRented) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.daysRented = daysRented;
    }

    public Vehicle() {
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }

    // METODOS    
    public abstract void VehicleInfo();

    public abstract void printInvoice();

    public abstract String toFileString();

    @Override
    public String toString() {
        return "Patente: " + licensePlate + "\n"
                + "Marca: " + brand + "\n"
                + "Dias Arrendado: " + daysRented + "\n"
                + "-------------------------";
    }

}
