package SafeVoteSystem;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class PrimesThread implements Runnable {

    private PrimesList primesList;
    private Random random = new Random();
    private BlockingQueue<Integer> queue;
    
    // declaramos la cantidad a procesar y el maximo a generar en el queue    
    private static final int NUMEROS_A_PROCESAR = 500;
    private static final int MAXIMO_A_GENERAR = 5000;

    // anadimos el blockingqueue al constructor
    public PrimesThread(PrimesList primesList, BlockingQueue<Integer> queue) {
        this.primesList = primesList;
        this.queue = queue;

    }

    
    @Override
    public void run() {
        // generamos un numero aleatorio y si es primo se agrega a la lista en PrimesList
        try {
            for (int i = 0; i < NUMEROS_A_PROCESAR; i++) { // iteramos 
                int numeroAleatorio = random.nextInt(MAXIMO_A_GENERAR) + 1; // generamos numeros al azar (+1 para ajustar el indice)
                queue.put(numeroAleatorio); // se agregan a la cola
                Integer chequeo = queue.poll(50, TimeUnit.MILLISECONDS);

                if (chequeo != null) { // chequeamos que sea primo, de ser asi, se agrega a la lista

                    if (primesList.isPrime(chequeo)) {
                        try {

                            primesList.add(chequeo);
                        } catch (IllegalArgumentException e) {

                        }

                    }

                } else {
                    System.out.println("Cola vacia.");
                }
                Thread.sleep(10);
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Error en hilo " + Thread.currentThread().getName() + ": " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Hilo " + Thread.currentThread().getName() + " interrumpido.");

        }
    }

}
