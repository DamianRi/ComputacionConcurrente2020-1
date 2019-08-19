package unam.ciencias.computoconcurrente;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void testSequential() throws InterruptedException  {
        int[][] A = {
                {1,2,3},
                {5,6,7},
                {8,9,10},
        };
        int[][] B = {
                {11,12,13},
                {15,16,17},
                {18,19,20},
        };
        int[][] expectedResult = {
                {12,14,16},
                {20,22,24},
                {26,28,30},
        };

        assertArrayEquals(expectedResult, App.addMatrices(A, B, 1));
    }

    @Test
    public void testConcurrent1() throws InterruptedException {
        int[][] A = {
                {1,2,3},
                {5,6,7},
                {8,9,10},
        };
        int[][] B = {
                {11,12,13},
                {15,16,17},
                {18,19,20},
        };
        int[][] expectedResult = {
                {12,14,16},
                {20,22,24},
                {26,28,30},
        };

        assertArrayEquals(expectedResult, App.addMatrices(A, B, 3));
    }

    @Test
    public void testConcurrent2() throws InterruptedException  {
        int[][] A = {
                {1,2,3},
                {5,6,7},
                {8,9,10},
        };
        int[][] B = {
                {11,12,13},
                {15,16,17},
                {18,19,20},
        };
        int[][] expectedResult = {
                {12,14,16},
                {20,22,24},
                {26,28,30},
        };

        assertArrayEquals(expectedResult, App.addMatrices(A, B, 2));
    }

}
