
public class MatrixThreads implements Runnable{

    public static int[][] M; // Matriz principal 
    public static int[][] matrixOne = {{1,2},
                                       {3,4}};
    public static int[][] matrixTwo = {{5,6},
                                       {7,8}};

    public static int tam = 2;
    
    public static int i = 0;
    public static int j = 0;


    @Override
    public void run(){
        M[i][j] = matrixOne[i][j] + matrixTwo[i][j];
    }

    public static void main(String [] args) throws InterruptedException{
        M = new int[tam][tam];//Matriz principal

        for (i = 0; i < tam; i++) {
            for (j = 0; j < tam; j++) {
                Thread t = new Thread(new MatrixThreads());
                t.start();
                t.join();                
            }            
        }

        for (int x = 0; x < tam; x++) {
            for (int y = 0; y < tam; y++) {
                System.out.print(M[x][y]+"\t");
                if(y == tam-1)
                    System.out.println("\n");
            }
        }
    }

}