package bankboston;

import java.util.InputMismatchException;

public class Cuenta {
    
    // variables de instancia de la clase encapsuladas
    private int saldo, numeroCuenta;
   

    // constructor con variables
    public Cuenta(int saldoInicial, int numeroCuenta) {

        this.saldo = saldoInicial;
        this.numeroCuenta = numeroCuenta;
    }

    // constructor vacio
    public Cuenta() {

    }
    
    // getters y setters de las variables
    public int getSaldo() {
        return saldo;
    }
    
    public void setSaldo (int saldo) {
        this.saldo = saldo;
    }
    
    public int numeroCuenta() {
        return numeroCuenta;
    }
    
    public void setNumeroCuenta (int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    
    public void infoCuentaCliente(){
        System.out.println("Saldo: " + this.getSaldo());
    }
    
    
    public void depositar(int depositoInterno) {

        if (depositoInterno > 0) {

            this.saldo = this.saldo + depositoInterno;

        } else {
            System.out.println("Ingrese un monto mayor a 0.");
        }

    }




    // METODOS    
    public void girar(int giroInterno) {
        
        if (giroInterno > 0 && giroInterno <= saldo) {
            this.saldo = (saldo - giroInterno);
        } else {
            System.out.println("El monto no puede ser cero ni mayor a su saldo.");
        }
        
    }
    
    public void consultarSaldo() {
        System.out.println(this.getSaldo());  
    }
    

}
