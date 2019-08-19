package unam.ciencias.computoconcurrente;

public class PrimeNumberCalculator {
    private int threads;

    public PrimeNumberCalculator() {
        this.threads = 1;
    }

    public PrimeNumberCalculator(int threads) {
        this.threads = threads > 1 ? threads : 1;
    }

    public boolean isPrime(int n) {
        return true;
    }
}
