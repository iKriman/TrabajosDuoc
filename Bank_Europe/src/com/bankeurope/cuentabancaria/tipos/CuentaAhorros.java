
package com.bankeurope.cuentabancaria.tipos;

import com.bankeurope.cuentabancaria.principal.CuentaBancaria;

public class CuentaAhorros extends CuentaBancaria {
    
     double interes; // creacion de variable de instancia para alamacenar el interes ganado


    public CuentaAhorros(int saldoInicial, String numeroCuenta) {
        super(saldoInicial, numeroCuenta);
    }

    public CuentaAhorros() {
    }
    
    @Override
    public void infoCuentaCliente() {
        super.infoCuentaCliente();

        System.out.println("Saldo Cuenta de Ahorro: " + (getSaldo() + interes));
        System.out.println("Intereses Ganados: " + interes);

    }

    @Override
    public void depositar(int depositoInterno) {

        if (depositoInterno > 0) {

            setSaldo(getSaldo() + depositoInterno);

        } else {
            System.out.println("Ingrese un monto mayor a 0.");
        }

    }

    @Override
    public void girar(int giroInterno) {

        if (giroInterno > 0 && giroInterno <= getSaldo()) {
            setSaldo(getSaldo() - giroInterno);
        } else {
            System.out.println("El monto no puede ser cero ni mayor a su saldo.");
        }

    }
    
    @Override
    public void calcularInteres() { // metodo abstracto heredado y polimorfico para calcular interes

        interes = (getSaldo() * 0.1);

    }
    
}