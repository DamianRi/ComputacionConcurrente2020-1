package unam.ciencias.computoconcurrente;

public class App implements Runnable
{
    //Matrices resultantes
    public static int[][] Matrix1;
    public static int[][] Matrix2;
    //Dimensiones de la matriz uno
    public static int dimM1;
    public static int dimN1;
    //Dimensiones de la matriz dos
    public static int dimM2;
    public static int dimN2;
    //Dimensiones de la matriz tres
    public static int dimM3;
    public static int dimN3;
    
    /* MatrixOne    MatrixTwo   MatrixThree
        1 2 3       10 11 12    19 20 21 22
        4 5 6       13 14 15    23 24 25 26
        7 8 9       16 17 18    27 28 29 30
    */
    public static int[][] matrixOne = {{1,2,3},{4,5,6},{7,8,9}};
    public static int[][] matrixTwo = {{10,11,12},{13,14,15},{16,17,18}};
    public static int[][] matrixThree = {{19, 20, 21, 22}, {23, 24, 25, 26}, {27, 28, 29, 30}};
    
    //Indices sobre los elementos de la matriz
    public static int i = 0;
    public static int j = 0;
    //indices para la matriz de multiplicación
    static int x;
    static int y;
    //banderas para que el hilo sepa que operación llevar acabo
    static boolean add = false;
    static boolean times = false;

    
    @Override
    public void run(){
        //Cuando suamamos con el método
        if (add && !times) {
            Matrix1[i][j] = matrixOne[i][j] + matrixTwo[i][j];
        }
        //Cuando multiplicamos con el método
        else if(times && !add)
        {
            Matrix2[i][j] += matrixOne[i][x] * matrixThree[x][j];
        }
    }
    
    /**
     * Suma los elementos de dos matrices llenando una matriz estática
     * como la suma entrada a entrada de ambas matrices, las dimensiones de ambas
     * deben ser iguales
     * @param m1 - matriz uno
     * @param m2 - matriz dos
     * @return una matriz estática.
     * @throws InterruptedException
     */
    public static int[][] addMatrix(int[][] m1, int[][] m2) throws InterruptedException{
        add = true;times = false;//Acomodamos las banderas para el hilo
        
        //Para cada entrada de la matriz
        for (i = 0; i < m1.length; i++) {
            for (j = 0; j < m1.length; j++) {
                Thread t = new Thread(new App());
                t.start();
                t.join();            
            }
        }
        return Matrix1;
    }

    /**
     * Multiplica dos matrices. Estás deben ser de dimensiones M1xN1 y M2xN2,
     * donde N1 = M2. 
     * @param m1 - matriz izquierda 
     * @param m2 - matriz derecha
     * @return una matriz estática
     * @throws InterruptedException
     */
    public static int[][] timesMatrix(int[][] m1, int[][] m2) throws InterruptedException{
        add = false;times = true;
        
        //Para cada entrada de la matriz
        for (i = 0; i < m1.length; i++) {
            for (j = 0; j < m2[0].length; j++) {
                
                //Cada C(i,j) = SUMA( A(i,x)*B(x,j) )
                for (x = 0; x < m2.length; x++) {
                    
                    Thread times = new Thread(new App());
                    times.start();
                    times.join();
                }

            }
        }
        return Matrix2;
    }

    /**
     * Imprime los elementos de una matriz
     * @param m - Matriz de enteros
     */
    public static void printMatrix(int[][] m){
        for (int x = 0; x < m.length; x++) {
            for (int y = 0; y < m[0].length; y++) {
                System.out.print(m[x][y]+"\t");
            }
            System.out.println("");
        }
    }

    public static void main(String [] args) throws InterruptedException{
        //Obtenemos las dimesiones de las matrices
        dimM1 = matrixOne.length;
        dimN1 = matrixOne[0].length;
        dimM2 = matrixTwo.length;
        dimN2 = matrixTwo[0].length;
        dimM3 = matrixThree.length;
        dimN3 = matrixThree[0].length;


        Matrix1 = new int[dimM1][dimN1];//Matriz principal para la suma
        System.out.println("Dimension "+ dimM1 + " X "+ dimN1);
        printMatrix( addMatrix(matrixOne , matrixTwo) );


        Matrix2 = new int[dimM3][dimN3];//Matriz principal para la multiplicación
        System.out.println("Dimension "+ dimM3 + " X "+ dimN3);
        printMatrix( timesMatrix(matrixOne, matrixThree) );

    }


}
