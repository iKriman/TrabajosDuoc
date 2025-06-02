package bankboston;

public class CuentaCredito extends Cuenta {

    private int cupoCredito = 100000;

    public CuentaCredito(int cupoCredito, int saldoInicial, String   numeroCuenta) {
        super(saldoInicial, numeroCuenta);
        this.cupoCredito = cupoCredito;
    }

    public CuentaCredito() {
    }

    public int getCupoCredito() {
        return cupoCredito;
    }

    public void setCupoCredito(int cupoCredito) {
        this.cupoCredito = cupoCredito;
    }
    
    @Override
    public void infoCuentaCliente() {
        super.infoCuentaCliente();

        System.out.println("Cupo tarjeta de credito: " + this.cupoCredito);

    }

    @Override
    public void depositar(int depositoInterno) {

        if (depositoInterno > 0) {

            this.cupoCredito = this.cupoCredito + depositoInterno;

        } else {
            System.out.println("Ingrese un monto mayor a 0.");
        }

    }

    @Override
    public void girar(int giroInterno) {

        if (giroInterno > 0 && giroInterno <= cupoCredito) {
            this.cupoCredito = (cupoCredito - giroInterno);
        } else {
            System.out.println("El monto no puede ser cero ni mayor a su saldo.");
        }

    }

}
