
package com.bankeurope.cuentabancaria.principal;

public abstract class CuentaBancaria {

    // variables de instancia de la clase encapsuladas
    private double saldo;
    private String numeroCuenta;

    // constructor con variables
    public CuentaBancaria(double saldoInicial, String numeroCuenta) {

        this.saldo = saldoInicial;
        this.numeroCuenta = numeroCuenta;
    }

    // constructor vacio
    public CuentaBancaria() {

    }

    // getters y setters de las variables
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    // METODOS  

    public void infoCuentaCliente() {

        if (this.numeroCuenta != null && !this.numeroCuenta.isEmpty()) {
            System.out.println("Numero de cuenta: " + this.getNumeroCuenta());
        } else {
            System.out.println("Aun no se a registrado un cliente.");
        }

    }

    public void depositar(int depositoInterno) {

        if (depositoInterno > 0) {

            this.saldo = this.saldo + depositoInterno;

        } else {
            System.out.println("Ingrese un monto mayor a 0.");
        }

    }

    public abstract void girar(int giroInterno);
    
    public abstract void calcularInteres();
    


}