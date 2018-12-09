package ex9_fibonacci_matrix;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FiboMatrixOptimizedTest {

    @Test
    public void toPower() {
        FiboMatrixOptimized baseMatrix = new FiboMatrixOptimized();

        FiboMatrixOptimized toPower0 = baseMatrix.toPower(0);
        Assert.assertEquals(toPower0.getFibo(), 0);

        FiboMatrixOptimized toPower2 = baseMatrix.toPower(2);
        Assert.assertEquals(toPower2.getFibo(), 1);

        FiboMatrixOptimized toPower3 = baseMatrix.toPower(3);
        Assert.assertEquals(toPower3.getFibo(), 2);
        System.out.println("base: \n" + baseMatrix);
        System.out.println("base^0: \n" + baseMatrix.toPower(0));
        System.out.println("base^2: \n" + toPower2);
        System.out.println("base^3: \n" + toPower3);
    }
}