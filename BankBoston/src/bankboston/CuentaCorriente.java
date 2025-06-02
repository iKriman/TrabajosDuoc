package bankboston;

public class CuentaCorriente extends Cuenta {

    public CuentaCorriente(int saldoInicial, String numeroCuenta) {
        super(saldoInicial, numeroCuenta);
    }

    public CuentaCorriente() {
    }
    
    
    @Override
    public void infoCuentaCliente() {
        super.infoCuentaCliente();

        System.out.println("Saldo Cuenta Corriente: " + this.getSaldo());

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
            setSaldo (getSaldo() - giroInterno);
        } else {
            System.out.println("El monto no puede ser cero ni mayor a su saldo.");
        }

    }

}
