package SafeVoteSystem;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrimesList extends ArrayList<Integer> {

    
    // llamamos a lock y condition y los hacemos finales para mantener una concurrencia segura
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    
    
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
    public synchronized boolean add(Integer element) {
        if (!isPrime(element)) {
            throw new IllegalArgumentException("Por favor ingrese un numero primo. " + element + " no es primo.");
        }
        boolean resultado = super.add(element);
        notifyAll();
        return resultado;

    }

    @Override
    public synchronized boolean remove(Object o) {
        return super.remove(o);
    }

    // creamos metodo para sincronizacion de numeros primos 
    public synchronized Integer waitForPrimos(int index) throws InterruptedException { 
        while (this.size() <= index) {
            wait();
        }
        return this.get(index);
    }

    public synchronized int getPrimesCount() {
        return size();
    }


    // metodos demostrativos avanzados de control concurrente
    //===
    public Integer primosLock(int index) throws InterruptedException {
        lock.lock();
        try {
            while (this.size() <= index) {
                condition.await(); // esperamos al lock
            }
            return this.get(index);
        } finally {
            lock.unlock();
        }
    }
    
    public ArrayList<Integer> getLock() {
        lock.lock();
        try {
            return new ArrayList<>(this);
        } finally {
            lock.unlock();
        }
    }
    //===

}
