package semana7;

import java.util.ArrayList;

public class PrimesList extends ArrayList<Integer> {

    // representacion de una lista de numeros primos
    
    
    // METODOS
    
    // logica para verificar si un numero es primo
    public boolean isPrime(int numero) {
        if (numero <= 1) { return false;}
        if (numero == 2) { return true;}
        if (numero % 2 == 0) {return false;}
        
        int raiz = (int) Math.sqrt(numero);
        for (int i = 3; i <= raiz; i+=2) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    
    // sobrescritura de los metodos add y remove
    @Override
    public boolean add (Integer element) {
        if (!isPrime(element)) {
            throw new IllegalArgumentException("Por favor ingrese un numero primo. " + element + " no es primo.");
        }
        return super.add(element);
    }
    
    @Override
    public boolean remove (Object o) {
        return super.remove(o);
    }
    
    
    // metodo para ver la cantidad de numeros dentro de la lista
    public int getPrimesCount() {
        return size();
    }
}
