package unam.ciencias.computoconcurrente;

public class App {

    public static void main(String[] a) {
    }

    public static int[][] addMatrices(final int[][] A, final int[][] B, final int threadCount)
        throws InterruptedException {
        // This fails if A is empty
        final int[][] result = new int[A.length][A[0].length];

        // Always assigns the ceiling of A.length / threadCount
        final int length = (A.length / threadCount) +
                (A.length % threadCount == 0 ? 0 : 1);

        Thread[] threads = new Thread[threadCount];

        // Init the threads
        for(int i=0, currentIndex = 0; i<threads.length; i++, currentIndex += length) {
            final int index = currentIndex;
            threads[i] = new Thread(() -> addMatrixChunk(result, A, B, index, length));
        }

        // Start the threads
        for(Thread t : threads) {
            t.start();
        }

        // Wait for them till completion
        for(Thread t : threads) {
            t.join();
        }

        return result;
    }

    public static void addMatrixChunk(int[][] result, int[][] A, int[][] B, int initIndex, int length) {
        for(int i = initIndex; length-->0 && i<A.length; i++) {
            for(int j = 0; j < A[i].length; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
    }
}
