package bankboston;

public class Cliente implements IinfoCliente{
    
    //Cuenta cuentaInterna = new Cuenta();

    // variables de instancia encapsuladas de la clase
    private String rut, nombre, apellidoPaterno, apellidoMaterno, domicilio, comuna;
    private int telefono;
    
    private Cuenta cuentaInterna; // cracion de una instancia de la clase Cuenta

    // constructor con sus respectivas variables
    public Cliente(String rut, String nombre, String apellidoPaterno, String apellidoMaterno, String domicilio, String comuna,
            int telefono, Cuenta cuentaInterna) {

        this.rut = rut;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.domicilio = domicilio;
        this.comuna = comuna;
        this.telefono = telefono;
        this.cuentaInterna = cuentaInterna;
        

    }

    // constructor vacio 
    public Cliente() {

    }
    
    
    // getters y setters de cada variable
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Cuenta getCuenta() {
        return cuentaInterna;
    }

    
    
    // metodos
    @Override
    public void infoCliente() {

        if (this.nombre != null) {

            System.out.println("Nombre: " + nombre + " " + apellidoPaterno + " " + apellidoMaterno);
            System.out.println("RUT: " + rut);
            System.out.println("Domicilio: " + domicilio);
            System.out.println("Comuna: " + comuna);
            System.out.println("Telefono: " + telefono);

        } else {

            System.out.println("Aun no se ha registrado.");
            System.out.println("Por favor registrese y vuelva a intentarlo.");

        }

    }

   
}
